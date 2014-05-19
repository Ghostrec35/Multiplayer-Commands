package me.ghosrec35.mpc.command;

import net.minecraft.command.ICommandSender;

public class CommandNick extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "nick";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.nick.usage";
    }

    @Override
    public void processCommand(ICommandSender var1, String[] var2)
    {
        
    }
}
