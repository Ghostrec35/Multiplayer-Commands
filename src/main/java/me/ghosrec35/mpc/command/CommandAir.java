package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.ref.ConfigurationData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandAir extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "air";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.air.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender) && ConfigurationData.AIR)
        {
           EntityPlayer player = getCommandSenderAsPlayer(sender);
           player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, -1));
        }
        else
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            ChatComponentText text = new ChatComponentText("Unfortunately, an error occurred while attempting to execute this command. You either do not have the required permissions to use this command, or the command has been disabled on the MPC Configuration.");
            text.setChatStyle(new ChatStyle().setBold(true).setColor(EnumChatFormatting.RED));
            player.addChatMessage(text);
        }
    }
}
