package net.epoxide.mpc.command;

import net.epoxide.mpc.nbt.ExtendedPlayerData;
import net.epoxide.mpc.ref.Reference;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandMPC extends CommandMPCBase 
{
	private static ChatComponentText separator = (ChatComponentText) new ChatComponentText("#########################################").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN));
	
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
					sender.addChatMessage(separator);
					Object[] values = genStatus(getCommandSenderAsPlayer(sender));
					for(int i = 1; i <= values.length; i++)
					{
						Object value = values[i - 1];
						if(value instanceof double[])
						{
							double[] coordinates = (double[])value;
							sender.addChatMessage(new ChatComponentTranslation("commands.mpc.status.line" + i, coordinates[0], coordinates[1], coordinates[2]));
						}
						else
						{
							sender.addChatMessage(new ChatComponentTranslation("commands.mpc.status.line" + i, value));
						}
					}
					sender.addChatMessage(separator);
				}
				else
				if(params[0].equalsIgnoreCase("version"))
				{
					sender.addChatMessage(new ChatComponentTranslation("commands.mpc.version", Reference.VERSION));
				}
			}
			else	
			{
				sender.addChatMessage(new ChatComponentTranslation("commands.general.lackofperms"));
			}
		}
		else
		{
			sender.addChatMessage(new ChatComponentTranslation(getCommandUsage(sender)));
		}
	}
	
	public Object[] genStatus(EntityPlayer player)
	{
		ExtendedPlayerData data = (ExtendedPlayerData) player.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
		return new Object[]
				{
					player.getDisplayName(),
					new double[]{data.getHomeXCoordinate(), data.getHomeYCoordinate(), data.getHomeZCoordinate()},
					new double[]{data.getDeathXCoordinate(), data.getDeathYCoordinate(), data.getDeathZCoordinate()},
					(player.capabilities.disableDamage == true ? "True" : "False"),
					(data.isInstaKillActive() == true ? "True" : "False"),
					(data.isInstaMineActive() == true ? "True" : "False"),
					(data.isFallDamageInactive() != true ? "True" : "False")
				};
	}
	
	@Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
