package me.ghosrec35.mpc.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandExtinguish extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "extinguish";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.extinguish.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length > 0)
            {
                EntityPlayer player = getCommandSenderAsPlayer(sender).worldObj.getPlayerEntityByName(params[0]);
                player.extinguish();
            }
            else
            {
                getCommandSenderAsPlayer(sender).extinguish();
            }
        }
    }
}
