package net.epoxide.mpc.command;

import net.epoxide.mpc.creativetab.CreativeTabCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;

public class CommandAddTab extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "addtab";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(params.length == 2)
        {
        	if(canCommandSenderUseCommand(sender))
        	{
	            ItemStack stack;
	            try
	            {
	                stack = new ItemStack(Item.getItemById(Integer.parseInt(params[1])));
	            }
	            catch(Exception e)
	            {
	                stack = new ItemStack((Item)Item.itemRegistry.getObject(params[1].toLowerCase()));
	            }
	            CreativeTabCommand.dynamicallyGenerateTab(params[0], stack);
	            sender.addChatMessage(new ChatComponentTranslation("commands.addtab.success", params[0], stack.getDisplayName()));
        	}
        	else	
        	{
        		sender.addChatMessage(new ChatComponentTranslation("commands.general.lackofperms"));
        	}
        }
        else
        {
        	sender.addChatMessage(new ChatComponentTranslation(getCommandUsage(sender)));
        }
    }

	@Override
	public boolean hasProperParams(String[] params) 
	{
		if(params.length != 2)
			return false;
		return true;
	}
}
