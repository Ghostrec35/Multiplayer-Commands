package net.epoxide.mpc.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandExtinguish extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "extinguish";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.extinguish.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length > 0)
            {
                EntityPlayer player = getCommandSenderAsPlayer(sender).worldObj.getPlayerEntityByName(params[0]);
                player.extinguish();
                sender.addChatMessage(new ChatComponentTranslation("commands.extinguish.success", params[0]));
            }
            else
            {
                getCommandSenderAsPlayer(sender).extinguish();
                sender.addChatMessage(new ChatComponentTranslation("commands.extinguish.success", sender.getCommandSenderName()));
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
