package me.ghosrec35.mpc.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandGod extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "god";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "god.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            boolean godMode = player.capabilities.disableDamage;
            player.capabilities.disableDamage = !godMode;
            player.sendPlayerAbilities();
        }
    }
}
