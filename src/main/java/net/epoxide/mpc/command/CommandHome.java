package net.epoxide.mpc.command;

import net.epoxide.mpc.nbt.ExtendedPlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandHome extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "home";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.home.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            ExtendedPlayerData data = (ExtendedPlayerData) player.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
            player.setPositionAndUpdate(data.getHomeXCoordinate(), data.getHomeYCoordinate(), data.getHomeZCoordinate());
            sender.addChatMessage(new ChatComponentTranslation("commands.home.success", data.getHomeXCoordinate(), data.getHomeYCoordinate(), data.getHomeZCoordinate()));
        }
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
