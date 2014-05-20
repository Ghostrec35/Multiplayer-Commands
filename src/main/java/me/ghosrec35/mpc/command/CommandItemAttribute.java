package me.ghosrec35.mpc.command;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import me.ghosrec35.mpc.util.StackUtils;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;

public class CommandItemAttribute extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "itemAttribute";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.itemAttribute.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            
            if (player.getHeldItem() != null) 
            {
            	ItemStack stack = player.getHeldItem();
            	
            	if (params.length == 3) {
            		
            		double value = Double.parseDouble(params[1]);
            		int operation = Integer.parseInt(params[2]);
            		
            		if (params[0].equalsIgnoreCase("health"))
            			StackUtils.setHealthAttribute(stack, value, operation);
            		
            		if (params[0].equalsIgnoreCase("knockback"))
            			StackUtils.setKnockbackResistanceAttribute(stack, value, operation);
            		
            		if (params[0].equalsIgnoreCase("follower"))
            			StackUtils.setFollowAttribute(stack, value, operation);
            		
            		if (params[0].equalsIgnoreCase("speed"))
            			StackUtils.setSpeedAttribute(stack, value, operation);
            		
            		if (params[0].equalsIgnoreCase("damage"))
            			StackUtils.setDamageAttribute(stack, value + StackUtils.getItemWeaponDamage(stack), operation);
            	}
            }
        }
    }
}
