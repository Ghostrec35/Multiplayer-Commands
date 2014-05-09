package me.ghosrec35.mpc;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandHunger extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "hunger";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "hunger.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            if(params.length == 1)
            {
                player.getFoodStats().setFoodLevel(Integer.parseInt(params[0]));
            }
            else
            {
                player.getFoodStats().setFoodLevel(20);
            }
        }
    }
}
