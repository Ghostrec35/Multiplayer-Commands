package net.epoxide.mpc.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandHeal extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "heal";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.heal.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            if(params.length == 1)
            {
                player.setHealth(player.getHealth() + Float.parseFloat(params[0]));
                sender.addChatMessage(new ChatComponentTranslation("commands.heal.success", params[0]));
            }
            else
            {
                player.setHealth(player.getMaxHealth());
                sender.addChatMessage(new ChatComponentTranslation("commands.heal.success", String.valueOf(player.getMaxHealth())));
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
