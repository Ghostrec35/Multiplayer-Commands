package me.ghosrec35.mpc.command;

import java.util.Arrays;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public abstract class CommandMPCBase extends CommandBase
{
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        boolean normalCommandReq = super.canCommandSenderUseCommand(sender);
        if((normalCommandReq && doesSenderHavePerms(sender)))
        {
            return true;
        }
        return false;
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return (canCommandSenderUseCommand(sender) ? "commands." + getCommandName() + ".usage" : "commands." + getCommandName() + ".lackofperms");
    }
    
    private boolean doesSenderHavePerms(ICommandSender sender)
    {
        return true;
    }
    
    protected List<String> genAliasList(String... strings)
    {
        return Arrays.asList(strings);
    }
}
