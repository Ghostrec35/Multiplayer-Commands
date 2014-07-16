package net.epoxide.mpc.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandExplode extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "explode";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.explode.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer commandUser = getCommandSenderAsPlayer(sender);
            if(params.length == 1)
            {
                EntityPlayer target = commandUser.worldObj.getPlayerEntityByName(params[0]);
                target.worldObj.createExplosion(target, target.posX, target.posY, target.posZ, 4.0f, true);
                sender.addChatMessage(new ChatComponentTranslation("commands.explode.success", target.posX, target.posY, target.posZ));
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
