package me.ghosrec35.mpc;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CommandRepair extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "repair";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "repair.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            ItemStack stack = player.getCurrentEquippedItem();
            if(stack != null)
                stack.setItemDamage(0);
        }
    }
}
