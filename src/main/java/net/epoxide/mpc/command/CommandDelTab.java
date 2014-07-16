package net.epoxide.mpc.command;

import java.lang.reflect.Field;

import net.epoxide.mpc.creativetab.CreativeTabCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

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
        if (canCommandSenderUseCommand(sender))
        {
            for (int i = 0; i < CreativeTabs.creativeTabArray.length; i++)
            {
                if (CreativeTabs.creativeTabArray[i].equals(CreativeTabCommand.tabMap.get(params[0])))
                    CreativeTabs.creativeTabArray[i] = null;
            }
            CreativeTabCommand.tabMap.remove(params[0]);
            setTabIndex(CreativeTabs.tabBlock.getTabIndex());
            cleanCreativeTabArray();
            sender.addChatMessage(new ChatComponentTranslation("commands.deltab.success", params[0]));
        }
    }

    /**
     * Retrieves and sets the selectedTabIndex field from the
     * GuiContainerCreative class on the client Failure to do so after removal
     * and cleanup of a CreativeTab will cause an IndexOutOfBoundsException,
     * assuming the player was previously on the last tab before deleteing a
     * custom tab.
     **/
    private void setTabIndex(int i)
    {
        try
        {
            Class<?> guiContainerCreative = Class.forName("net.minecraft.client.gui.inventory.GuiContainerCreative");
            Field f = guiContainerCreative.getDeclaredField("selectedTabIndex");
            f.setAccessible(true);
            f.setInt(null, i);
            f = guiContainerCreative.getDeclaredField("tabPage");
            f.setAccessible(true);
            f.setInt(null, 0);
        }
        catch (Exception e)
        {
            // Discard silently...command is running on the server side.
            // There is nothing for this method to do on the server. 
            // GuiContainerCreative is client-side only.
        }
    }

    private void cleanCreativeTabArray()
    {
        int numRemovals = 0;
        for (int i = 0; i < CreativeTabs.creativeTabArray.length; i++)
        {
            if (CreativeTabs.creativeTabArray[i] == null)
                numRemovals++;
        }
        CreativeTabs[] newTabArray = new CreativeTabs[CreativeTabs.creativeTabArray.length - numRemovals];
        int nextIndex = 0;
        int index = 0;
        while (index < CreativeTabs.creativeTabArray.length)
        {
            if (CreativeTabs.creativeTabArray[index] != null)
            {
                newTabArray[nextIndex++] = CreativeTabs.creativeTabArray[index];
            }
            index++;
        }

        CreativeTabs.creativeTabArray = newTabArray;
    }

    @Override
    public boolean hasProperParams(String[] params)
    {
        // TODO Do basic param checks
        return true;
    }
}
