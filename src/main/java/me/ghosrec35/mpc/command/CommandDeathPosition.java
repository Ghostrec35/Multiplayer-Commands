package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.nbt.ExtendedPlayerData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandDeathPosition extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "deathpos";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.deathpos.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            ExtendedPlayerData data = (ExtendedPlayerData) player.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
            player.setPositionAndUpdate(data.getDeathXCoordinate(), data.getDeathYCoordinate(), data.getDeathZCoordinate());
        }
    }

	@Override
	public boolean hasProperParams(String[] params) {
		// TODO Do basic param checks
		return false;
	}
}
