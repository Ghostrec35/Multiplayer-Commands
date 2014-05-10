package me.ghosrec35.mpc.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class CommandAir extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "air";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "air.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
           EntityPlayer player = getCommandSenderAsPlayer(sender);
           player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, -1));
        }
    }
}
