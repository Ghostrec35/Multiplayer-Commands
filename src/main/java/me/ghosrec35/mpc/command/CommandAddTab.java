package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.creativetab.CommandCreativeTab;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CommandAddTab extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "addtab";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "addtab.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender) && params.length == 2)
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
            CommandCreativeTab.dynamicallyGenerateTab(params[0], stack);
        }
    }
}
