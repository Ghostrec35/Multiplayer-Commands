package net.epoxide.mpc;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.epoxide.mpc.command.CommandAddItem;
import net.epoxide.mpc.command.CommandAddTab;
import net.epoxide.mpc.command.CommandBack;
import net.epoxide.mpc.command.CommandBroadcast;
import net.epoxide.mpc.command.CommandCraft;
import net.epoxide.mpc.command.CommandDamage;
import net.epoxide.mpc.command.CommandDelItem;
import net.epoxide.mpc.command.CommandDelTab;
import net.epoxide.mpc.command.CommandDrop;
import net.epoxide.mpc.command.CommandDropAll;
import net.epoxide.mpc.command.CommandEnchant;
import net.epoxide.mpc.command.CommandFly;
import net.epoxide.mpc.command.CommandGod;
import net.epoxide.mpc.command.CommandHat;
import net.epoxide.mpc.command.CommandHeal;
import net.epoxide.mpc.command.CommandHome;
import net.epoxide.mpc.command.CommandHunger;
import net.epoxide.mpc.command.CommandInstaKill;
import net.epoxide.mpc.command.CommandInstaMine;
import net.epoxide.mpc.command.CommandItem;
import net.epoxide.mpc.command.CommandItemAttribute;
import net.epoxide.mpc.command.CommandKillAll;
import net.epoxide.mpc.command.CommandLift;
import net.epoxide.mpc.command.CommandLore;
import net.epoxide.mpc.command.CommandMPC;
import net.epoxide.mpc.command.CommandMPCBase;
import net.epoxide.mpc.command.CommandMaxHealth;
import net.epoxide.mpc.command.CommandRename;
import net.epoxide.mpc.command.CommandRepair;
import net.epoxide.mpc.command.CommandSeeInventory;
import net.epoxide.mpc.command.CommandSetHome;
import net.epoxide.mpc.command.CommandSmelt;
import net.epoxide.mpc.command.CommandSpawner;
import net.epoxide.mpc.command.CommandSpeed;
import net.epoxide.mpc.command.CommandTransferToDimension;
import net.epoxide.mpc.event.EventManager;
import net.epoxide.mpc.nbt.MPCWorldDataManager;
import net.epoxide.mpc.ref.ConfigurationData;
import net.epoxide.mpc.ref.Reference;
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

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class MultiplayerCommands
{
    @Instance(Reference.MOD_ID)
    public static MultiplayerCommands instance;   
    
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
        manager.registerCommand(new CommandBack());
        manager.registerCommand(new CommandTransferToDimension());
        
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
