package me.ghosrec35.mpc.command;

import java.lang.reflect.Field;
import java.util.UUID;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;

public class CommandSpeed extends CommandBase
{
    private static final UUID sprintingSpeedBoostModifierUUID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
    
    @Override
    public String getCommandName()
    {
        return "speed";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "speed.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            if(params.length == 1)
            {
                try
                {
                    double d = Double.parseDouble(params[0]);
                    player.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(d);
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                player.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
            }
        }
    }
}
