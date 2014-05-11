package me.ghosrec35.mpc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ghosrec35.mpc.command.CommandBroadcast;
import me.ghosrec35.mpc.command.CommandDamage;
import me.ghosrec35.mpc.command.CommandDrop;
import me.ghosrec35.mpc.command.CommandDropAll;
import me.ghosrec35.mpc.command.CommandEnchant;
import me.ghosrec35.mpc.command.CommandFly;
import me.ghosrec35.mpc.command.CommandGod;
import me.ghosrec35.mpc.command.CommandHeal;
import me.ghosrec35.mpc.command.CommandHome;
import me.ghosrec35.mpc.command.CommandHunger;
import me.ghosrec35.mpc.command.CommandItemName;
import me.ghosrec35.mpc.command.CommandKillAll;
import me.ghosrec35.mpc.command.CommandLift;
import me.ghosrec35.mpc.command.CommandLore;
import me.ghosrec35.mpc.command.CommandMaxHealth;
import me.ghosrec35.mpc.command.CommandRepair;
import me.ghosrec35.mpc.command.CommandSeeInventory;
import me.ghosrec35.mpc.command.CommandSetHome;
import me.ghosrec35.mpc.command.CommandSpawner;
import me.ghosrec35.mpc.command.CommandSpeed;
import me.ghosrec35.mpc.event.EventManager;
import me.ghosrec35.mpc.nbt.MPCWorldDataManager;
import me.ghosrec35.mpc.nbt.NBTBasedSaveFile;
import me.ghosrec35.mpc.nbt.SaveFileUtilities;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = MultiplayerCommands.MODID)
public class MultiplayerCommands
{
    @Instance(MultiplayerCommands.MODID)
    public static MultiplayerCommands instance;
    
    public static final String MODID = "MultiplayerCommands";
    
    public static HashMap<String, Class<? extends Entity>> entityMap = new HashMap<String, Class<? extends Entity>>();
    public static Map<String, Enchantment> enchantmentMap = new HashMap<String, Enchantment>();
    
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
    private MinecraftServer minecraft;
    
    @EventHandler
    public void onInit(FMLInitializationEvent event)
    {
        dataManager = new MPCWorldDataManager(FMLCommonHandler.instance().getMinecraftServerInstance().getWorldName(), "mpc_data.dat");
        
        MinecraftForge.EVENT_BUS.register(new EventManager());
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event)
    {        
        ServerCommandManager manager = (ServerCommandManager)event.getServer().getCommandManager();
        manager.registerCommand(new CommandKillAll());
        manager.registerCommand(new CommandDropAll());
        manager.registerCommand(new CommandEnchant());
        manager.registerCommand(new CommandItemName());
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
    }
    
    private static String genEntityCommandKey(String className)
    {
        String[] entityNameParts = className.toLowerCase().split("\\.");
        String entityName = entityNameParts[entityNameParts.length - 1].replaceAll("entity", "");
        System.out.println(entityName);
        return entityName;
    }
}
