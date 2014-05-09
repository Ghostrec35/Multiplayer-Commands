package me.ghosrec35.mpc.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CommandDamage extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "damage";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "damage.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender) && params.length > 0)
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            ItemStack stack = player.getCurrentEquippedItem();
            if(stack != null)
            {
                int damage = Integer.parseInt(params[0]);
                stack.setItemDamage(damage);
            }
        }
    }
}
