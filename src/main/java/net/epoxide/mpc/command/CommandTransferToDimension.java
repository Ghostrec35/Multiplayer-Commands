package net.epoxide.mpc.command;

import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.common.DimensionManager;

public class CommandTransferToDimension extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "dim";
    }

    @Override
    public List getCommandAliases()
    {
        return genAliasList("dimension");
    }
    
    @Override
    public String getCommandUsage(ICommandSender p_71518_1_)
    {
        return "commands.dim.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayerMP player = getCommandSenderAsPlayer(sender);
            int dimID = Integer.parseInt(params[0]);
            if(dimID < -1 || dimID > 1)
            {
                DimensionManager.initDimension(dimID);
            }
            player.travelToDimension(Integer.parseInt(params[0]));
            sender.addChatMessage(new ChatComponentTranslation("commands.dim.success", DimensionManager.getProvider(dimID).getDimensionName(), dimID));
        }
    }

    @Override
    public boolean hasProperParams(String[] params)
    {
        // TODO Add proper params
        return true;
    }
}
