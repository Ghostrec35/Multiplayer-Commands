package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.nbt.ExtendedPlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

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
        }
    }
}
