package me.ghosrec35.mpc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.command.ServerCommandManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.common.MinecraftForge;
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
    
    @EventHandler
    public void onInit(FMLInitializationEvent event)
    {
        for(Class<? extends Entity> entityClass : ((Map<String, Class<? extends Entity>>) EntityList.stringToClassMapping).values())
        {
            System.out.println(entityClass.getName().toLowerCase());
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
        
        MinecraftForge.EVENT_BUS.register(new EventManager());
    }
    
    private String genEntityCommandKey(String className)
    {
        String[] entityNameParts = className.toLowerCase().split("\\.");
        String entityName = entityNameParts[entityNameParts.length - 1].replaceAll("entity", "");
        System.out.println(entityName);
        return entityName;
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
    }
}