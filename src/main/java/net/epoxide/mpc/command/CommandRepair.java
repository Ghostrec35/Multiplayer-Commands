package net.epoxide.mpc.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CommandRepair extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "repair";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.repair.usage";
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
    
    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
