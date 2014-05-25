package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.ref.Reference;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandMPC extends CommandMPCBase 
{
	@Override
	public String getCommandName() 
	{
		return "mpc";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) 
	{
		return "commands.mpc.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] params) 
	{
		if(params.length == 1)
		{
			if(canCommandSenderUseCommand(sender))
			{
				if(params[0].equalsIgnoreCase("status"))
				{
					sender.addChatMessage(new ChatComponentTranslation("commands.mpc.status", genStatus(getCommandSenderAsPlayer(sender))));
				}
				else
				if(params[0].equalsIgnoreCase("version"))
				{
					sender.addChatMessage(new ChatComponentTranslation("commands.mpc.version", Reference.VERSION));
				}
			}
			else	
			{
				sender.addChatMessage(new ChatComponentText("You do not have the correct permissions to use this command."));
			}
		}
		else
		{
			sender.addChatMessage(new ChatComponentTranslation(getCommandUsage(sender)));
		}
	}
	
	public Object[] genStatus(EntityPlayer player)
	{
		
		return new Object[]{};
	}
}
