package net.epoxide.mpc.command;

import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CommandItem extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "i";
    }
    
    public List getCommandAliases()
    {
        return genAliasList("item");
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.item.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length >= 2)
            {
                ItemStack item;
                EntityPlayer player = getCommandSenderAsPlayer(sender);
                try
                {
                    item = new ItemStack(Item.getItemById(Integer.parseInt(params[0])));
                }
                catch(Exception e)
                {
                    item = new ItemStack((Item)Item.itemRegistry.getObject(params[0].toLowerCase()));
                }
                
                if(item.getItem() == null)
                    return;
                
                try
                {
                    int stackSize = Integer.parseInt(params[1]);
                    item.stackSize = stackSize;
                }
                catch(Exception e)
                {}
                
                player.inventory.addItemStackToInventory(item);
            }
        }
    }
    
    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
