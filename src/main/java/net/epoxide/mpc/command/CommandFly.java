package net.epoxide.mpc.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandFly extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "fly";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.fly.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            boolean allowFly = player.capabilities.allowFlying;
            if(!allowFly)
            {
                player.capabilities.allowFlying = true;
                player.capabilities.isFlying = true;
                player.sendPlayerAbilities();
                sender.addChatMessage(new ChatComponentTranslation("commands.fly.success", translate("commands.enabled")));
            }
            else
            {
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
                player.sendPlayerAbilities();
                sender.addChatMessage(new ChatComponentTranslation("commands.fly.success", translate("commands.disabled")));
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
