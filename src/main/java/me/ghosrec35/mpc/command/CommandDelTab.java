package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.creativetab.CommandCreativeTab;
import net.minecraft.command.ICommandSender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CommandDelTab extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "deltab";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.deltab.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            for(int i = 0; i < CreativeTabs.creativeTabArray.length; i++)
            {
                if(CreativeTabs.creativeTabArray[i].equals(CommandCreativeTab.tabMap.get(i)))
                    CreativeTabs.creativeTabArray[i] = null;
            }
            CommandCreativeTab.tabMap.remove(params[0]);
        }
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
