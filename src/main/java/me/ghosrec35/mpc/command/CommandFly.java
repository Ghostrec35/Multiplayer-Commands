package me.ghosrec35.mpc.command;

import ibxm.Player;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandFly extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "fly";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "fly.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            boolean allowFly = player.capabilities.allowFlying;
            if(!allowFly)
            {
                player.capabilities.allowFlying = true;
                player.capabilities.isFlying = true;
                player.sendPlayerAbilities();
            }
            else
            {
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
                player.sendPlayerAbilities();
            }
        }
    }
}
