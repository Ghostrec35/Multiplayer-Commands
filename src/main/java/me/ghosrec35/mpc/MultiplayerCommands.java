package me.ghosrec35.mpc;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ghosrec35.mpc.command.CommandAddItem;
import me.ghosrec35.mpc.command.CommandAddTab;
import me.ghosrec35.mpc.command.CommandBroadcast;
import me.ghosrec35.mpc.command.CommandCraft;
import me.ghosrec35.mpc.command.CommandDamage;
import me.ghosrec35.mpc.command.CommandDelItem;
import me.ghosrec35.mpc.command.CommandDelTab;
import me.ghosrec35.mpc.command.CommandDrop;
import me.ghosrec35.mpc.command.CommandDropAll;
import me.ghosrec35.mpc.command.CommandEnchant;
import me.ghosrec35.mpc.command.CommandFilledChest;
import me.ghosrec35.mpc.command.CommandFly;
import me.ghosrec35.mpc.command.CommandGod;
import me.ghosrec35.mpc.command.CommandHat;
import me.ghosrec35.mpc.command.CommandHeal;
import me.ghosrec35.mpc.command.CommandHome;
import me.ghosrec35.mpc.command.CommandHunger;
import me.ghosrec35.mpc.command.CommandInstaKill;
import me.ghosrec35.mpc.command.CommandInstaMine;
import me.ghosrec35.mpc.command.CommandItem;
import me.ghosrec35.mpc.command.CommandItemAttribute;
import me.ghosrec35.mpc.command.CommandKillAll;
import me.ghosrec35.mpc.command.CommandLift;
import me.ghosrec35.mpc.command.CommandLore;
import me.ghosrec35.mpc.command.CommandMPC;
import me.ghosrec35.mpc.command.CommandMPCBase;
import me.ghosrec35.mpc.command.CommandMaxHealth;
import me.ghosrec35.mpc.command.CommandRename;
import me.ghosrec35.mpc.command.CommandRepair;
import me.ghosrec35.mpc.command.CommandSeeInventory;
import me.ghosrec35.mpc.command.CommandSetHome;
import me.ghosrec35.mpc.command.CommandSmelt;
import me.ghosrec35.mpc.command.CommandSpawner;
import me.ghosrec35.mpc.command.CommandSpeed;
import me.ghosrec35.mpc.event.EventManager;
import me.ghosrec35.mpc.nbt.MPCWorldDataManager;
import me.ghosrec35.mpc.ref.ConfigurationData;
import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = MultiplayerCommands.MOD_ID, name = MultiplayerCommands.MOD_NAME, version = MultiplayerCommands.MOD_VERSION)
public class MultiplayerCommands
{
    @Instance(MultiplayerCommands.MOD_ID)
    public static MultiplayerCommands instance;
    
    public static final String MOD_ID = "MultiplayerCommands";
    public static final String MOD_NAME = "Multiplayer Commands";
    public static final String MOD_VERSION = "0.0.0.1-SNAPSHOT";
    
    public static HashMap<String, Class<? extends Entity>> entityMap = new HashMap<String, Class<? extends Entity>>();
    public static Map<String, Enchantment> enchantmentMap = new HashMap<String, Enchantment>();
    public static Map<String, ICommand> commandMap = new HashMap<String, ICommand>();
    
    static
    {
        for(Class<? extends Entity> entityClass : ((Map<String, Class<? extends Entity>>) EntityList.stringToClassMapping).values())
        {
            entityMap.put(genEntityCommandKey(entityClass.getName()), entityClass);
        }
        for(Enchantment enchant : Enchantment.enchantmentsList)
        {
            if(enchant != null)
                enchantmentMap.put(enchant.getName().toLowerCase(), enchant);
        }
        List<ModContainer> modList = Loader.instance().getActiveModList();
        for(ModContainer mod : modList)
        {
        }
    }
    
    public MPCWorldDataManager dataManager;
    public boolean usesGroupFile;
    public GroupManager manager;
    public Map<String, PlayerFile> customPlayerCommandFiles = new HashMap<String, PlayerFile>();
    private MinecraftServer minecraft;
    
    @EventHandler
    public void onPreInit(FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "MPC Configs/" + event.getModMetadata().name + ".cfg"));
        config.load();
        ConfigurationData.setConfigValues(config);
        config.save();
        
        manager = new GroupManager(new File(event.getModConfigurationDirectory(), "MPC Configs/UserGroups.cfg"));
        
        File dir = new File(event.getModConfigurationDirectory(), "MPC Configs/user-files");
        
        if(!dir.isDirectory())
            return;
        
        if(!dir.exists())
        {
            dir.mkdirs();
        }
        
        for(File file : dir.listFiles())
        {
            if(file.getName().endsWith(".cfg"))
                customPlayerCommandFiles.put(file.getName().replace(".cfg", ""), new PlayerFile(file));
            else
                file.delete();
        }
    }
    
    @EventHandler
    public void onInit(FMLInitializationEvent event)
    {   
        MinecraftForge.EVENT_BUS.register(new EventManager());
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event)
    {        
        dataManager = new MPCWorldDataManager(event.getServer().getWorldName(), "mpc_data.dat");   
        
        ServerCommandManager manager = (ServerCommandManager)event.getServer().getCommandManager();
        manager.registerCommand(new CommandKillAll());
        manager.registerCommand(new CommandDropAll());
        manager.registerCommand(new CommandEnchant());
        manager.registerCommand(new CommandRename());
        manager.registerCommand(new CommandLore());
        manager.registerCommand(new CommandRepair());
        manager.registerCommand(new CommandDamage());
        manager.registerCommand(new CommandLift());
        manager.registerCommand(new CommandDrop());
        manager.registerCommand(new CommandHeal());
        manager.registerCommand(new CommandHunger());
        manager.registerCommand(new CommandSetHome());
        manager.registerCommand(new CommandHome());
        manager.registerCommand(new CommandSeeInventory());
        manager.registerCommand(new CommandSpawner());
        manager.registerCommand(new CommandGod());
        manager.registerCommand(new CommandFly());
        manager.registerCommand(new CommandMaxHealth());
        manager.registerCommand(new CommandSpeed());
        manager.registerCommand(new CommandBroadcast());
        manager.registerCommand(new CommandAddTab());
        manager.registerCommand(new CommandDelTab());
        manager.registerCommand(new CommandAddItem());
        manager.registerCommand(new CommandDelItem());
        manager.registerCommand(new CommandItem());
        manager.registerCommand(new CommandSmelt());
        manager.registerCommand(new CommandInstaKill());
        manager.registerCommand(new CommandCraft());
        manager.registerCommand(new CommandHat());
        manager.registerCommand(new CommandInstaMine());
        manager.registerCommand(new CommandItemAttribute());
        manager.registerCommand(new CommandMPC());
        manager.registerCommand(new CommandFilledChest());
        
        for(ICommand command : ((Map<String, ICommand>)manager.getCommands()).values())
        {
            if(command instanceof CommandMPCBase)
            {
                String commandKey = "mpc." + command.getCommandName();
                commandMap.put(commandKey, command);
            }
        }
    }
    
    private static String genEntityCommandKey(String className)
    {
        String[] entityNameParts = className.toLowerCase().split("\\.");
        String entityName = entityNameParts[entityNameParts.length - 1].replaceAll("entity", "");
        System.out.println(entityName);
        return entityName;
    }
}
