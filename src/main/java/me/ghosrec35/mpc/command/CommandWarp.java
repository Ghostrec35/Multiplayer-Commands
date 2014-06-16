package me.ghosrec35.mpc.command;

import net.minecraft.command.ICommandSender;

public class CommandWarp extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "warp";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.warp.usage";
    }

    @Override
    public void processCommand(ICommandSender var1, String[] var2)
    {        
    }
    
    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
