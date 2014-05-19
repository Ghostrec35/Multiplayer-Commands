package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.creativetab.CommandCreativeTab;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CommandAddItem extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "additem";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "additem.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
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
    }
}
