package net.epoxide.mpc.command;

import net.epoxide.mpc.creativetab.CreativeTabCommand;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;

public class CommandAddItem extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "additem";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
    	if(params.length == 2 && canCommandSenderUseCommand(sender))
    	{
	        if(canCommandSenderUseCommand(sender))
	        {
	            CreativeTabCommand tab = CreativeTabCommand.tabMap.get(params[0]);
	            ItemStack stack;
	            try
	            {
	                stack = new ItemStack(Item.getItemById(Integer.parseInt(params[1])));
	            }
	            catch(Exception e)
	            {
	                stack = new ItemStack((Item)Item.itemRegistry.getObject(params[1].toLowerCase()));
	                if(stack.getItem() == null)
	                {
	                    stack = new ItemStack(Item.getItemFromBlock((Block)Block.blockRegistry.getObject(params[1].toLowerCase())));
	                }
	            }
	            tab.addItem(stack);
	            sender.addChatMessage(new ChatComponentTranslation("commands.additem.success", translate(stack.getDisplayName()), params[0]));
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
