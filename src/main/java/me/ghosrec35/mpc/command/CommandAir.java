package me.ghosrec35.mpc.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;

public class CommandAir extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "air";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.air.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
	    if(canCommandSenderUseCommand(sender))
	    {
	    	EntityPlayer player = getCommandSenderAsPlayer(sender);
	    	if(player.isPotionActive(Potion.waterBreathing))
	    		player.removePotionEffect(Potion.waterBreathing.id);
	    	else
	    		player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, Integer.MAX_VALUE));
	    }
	    else
	    {
	    	sender.addChatMessage(new ChatComponentTranslation("commands.general.lackofperms"));
	    }
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
