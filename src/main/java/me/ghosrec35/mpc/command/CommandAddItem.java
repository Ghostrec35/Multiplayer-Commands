package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.creativetab.CommandCreativeTab;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;

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
	            CommandCreativeTab tab = CommandCreativeTab.tabMap.get(params[0]);
	            ItemStack stack;
	            try
	            {
	                stack = new ItemStack(Item.getItemById(Integer.parseInt(params[1])));
	            }
	            catch(Exception e)
	            {
	                stack = new ItemStack((Item)Item.itemRegistry.getObject(params[1].toLowerCase()));
	            }
	            tab.addItem(stack);
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
