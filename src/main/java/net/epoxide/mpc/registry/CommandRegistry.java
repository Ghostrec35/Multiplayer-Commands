package net.epoxide.mpc.registry;

import java.util.HashMap;
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
import net.epoxide.mpc.command.CommandMaxHealth;
import net.epoxide.mpc.command.CommandRename;
import net.epoxide.mpc.command.CommandRepair;
import net.epoxide.mpc.command.CommandSeeInventory;
import net.epoxide.mpc.command.CommandSetHome;
import net.epoxide.mpc.command.CommandSmelt;
import net.epoxide.mpc.command.CommandSpawner;
import net.epoxide.mpc.command.CommandSpeed;
import net.epoxide.mpc.command.CommandTransferToDimension;
import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommandRegistry
{
    public static ServerCommandManager manager;
    public static Map<String, ICommand> commandMap = new HashMap<String, ICommand>();

    public static void initialize(FMLServerStartingEvent event)
    {        
        manager = (ServerCommandManager)event.getServer().getCommandManager();
        registerCommand(new CommandKillAll());
        registerCommand(new CommandDropAll());
        registerCommand(new CommandEnchant());
        registerCommand(new CommandRename());
        registerCommand(new CommandLore());
        registerCommand(new CommandRepair());
        registerCommand(new CommandDamage());
        registerCommand(new CommandLift());
        registerCommand(new CommandDrop());
        registerCommand(new CommandHeal());
        registerCommand(new CommandHunger());
        registerCommand(new CommandSetHome());
        registerCommand(new CommandHome());
        registerCommand(new CommandSeeInventory());
        registerCommand(new CommandSpawner());
        registerCommand(new CommandGod());
        registerCommand(new CommandFly());
        registerCommand(new CommandMaxHealth());
        registerCommand(new CommandSpeed());
        registerCommand(new CommandBroadcast());
        registerCommand(new CommandAddTab());
        registerCommand(new CommandDelTab());
        registerCommand(new CommandAddItem());
        registerCommand(new CommandDelItem());
        registerCommand(new CommandItem());
        registerCommand(new CommandSmelt());
        registerCommand(new CommandInstaKill());
        registerCommand(new CommandCraft());
        registerCommand(new CommandHat());
        registerCommand(new CommandInstaMine());
        registerCommand(new CommandItemAttribute());
        registerCommand(new CommandMPC());
        registerCommand(new CommandBack());
        registerCommand(new CommandTransferToDimension());
    }
    
    public static void registerCommand(ICommand cmd)
    {
        String commandKey = "mpc." + cmd.getCommandName();
        commandMap.put(commandKey, cmd);
        
        manager.registerCommand(cmd);
    }
}
