package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.MultiplayerCommands;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandSeeInventory extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "seeinv";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "seeinv.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length == 2)
            {
                MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                EntityPlayer player = server.worldServers[Integer.parseInt(params[0])].getPlayerEntityByName(params[1]);
                EntityPlayer commPlayer = getCommandSenderAsPlayer(sender);
                commPlayer.openGui(MultiplayerCommands.instance, 0, commPlayer.worldObj, (int)commPlayer.posX, (int)commPlayer.posY, (int)commPlayer.posZ);
            }
        }
    }
}
