package me.ghosrec35.mpc.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandIgnite extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "ignite";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "ignite.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer commandUser = getCommandSenderAsPlayer(sender);
            if(params.length == 2)
            {
                EntityPlayer target = commandUser.worldObj.getPlayerEntityByName(params[0]);
                target.setFire(Integer.parseInt(params[1]));
            }
        }
    }
}
