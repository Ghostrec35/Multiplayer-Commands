package me.ghosrec35.mpc;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CommandHeal extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "heal";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "heal.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            if(params.length == 1)
            {
                player.setHealth(Float.parseFloat(params[0]));
            }
            else
            {
                player.setHealth(player.getMaxHealth());
            }
        }
    }
}
