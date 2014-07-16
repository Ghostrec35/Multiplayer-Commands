package net.epoxide.mpc.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandMaxHealth extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "maxhealth";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.maxhealth.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            try
            {
                int value = Integer.parseInt(params[0]);
                player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(value);
                sender.addChatMessage(new ChatComponentTranslation("commands.maxhealth.success", value));
            }
            catch(Exception e)
            {
                player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
                sender.addChatMessage(new ChatComponentTranslation("commands.maxhealth.success", 20));

            }
        }
    }
    
    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
