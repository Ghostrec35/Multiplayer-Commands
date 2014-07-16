package net.epoxide.mpc.command;

import java.util.Iterator;
import java.util.List;

import net.epoxide.mpc.MultiplayerCommands;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandSeeInventory extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "seeinv";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.seeinv.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length == 1)
            {
                MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                EntityPlayer commPlayer = getCommandSenderAsPlayer(sender);
                EntityPlayer player = getPlayerByUsername(server.getConfigurationManager().playerEntityList, params[0]);
                commPlayer.getEntityData().setString("PlayerViewName", params[0]);
                commPlayer.openGui(MultiplayerCommands.instance, 0, player.worldObj, (int)commPlayer.posX, (int)commPlayer.posY, (int)commPlayer.posZ);
            }
        }
    }
    
    private EntityPlayer getPlayerByUsername(List playerEntityList, String usernameGiven)
    {
        Iterator<EntityPlayerMP> playerItr = playerEntityList.iterator();
        EntityPlayerMP player;
        while(playerItr.hasNext())
        {
            player = playerItr.next();
            if(player.getDisplayName().equals(usernameGiven));
        }
        return null;
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
