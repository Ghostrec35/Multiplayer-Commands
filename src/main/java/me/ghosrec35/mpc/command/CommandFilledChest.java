package me.ghosrec35.mpc.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandFilledChest extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "filledchest";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.filledchest.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            
        }
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
