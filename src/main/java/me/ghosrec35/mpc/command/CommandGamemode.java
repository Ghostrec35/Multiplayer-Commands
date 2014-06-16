package me.ghosrec35.mpc.command;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandGamemode extends CommandMPCBase
{

    @Override
    public String getCommandName()
    {
        return "gm";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.gm.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        EntityPlayer player = getCommandSenderAsPlayer(sender);
        ICommand comm = (ICommand) FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager().getCommands().get("gamemode");
        comm.processCommand(sender, params);
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
