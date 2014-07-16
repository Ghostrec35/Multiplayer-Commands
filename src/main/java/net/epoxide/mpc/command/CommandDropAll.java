package net.epoxide.mpc.command;

import java.util.Random;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentTranslation;

public class CommandDropAll extends CommandMPCBase
{
    private Random random = new Random();
    
    @Override
    public int getRequiredPermissionLevel()
    {
        return 1;
    }
    
    @Override
    public String getCommandName()
    {
        return "dropall";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.dropall.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            InventoryPlayer inv = player.inventory;
            double xMot = random.nextFloat() * 0.5f, zMot = random.nextFloat() * 0.5f;
            int numItemsDropped = 0;
            for(int item = 0; item < inv.mainInventory.length; item++)
            {
                numItemsDropped += dropItemIfNecessary(player, inv.mainInventory[item], xMot, zMot);
                inv.mainInventory[item] = null;
            }
            for(int item = 0; item < inv.armorInventory.length; item++)
            {
                numItemsDropped += dropItemIfNecessary(player, inv.armorInventory[item], xMot, zMot);
                inv.armorInventory[item] = null;
            }
            sender.addChatMessage(new ChatComponentTranslation("commands.dropall.success", numItemsDropped));
        }
    }

    private int dropItemIfNecessary(EntityPlayer player, ItemStack stack, double xMot, double zMot)
    {
        if(stack == null || stack.stackSize < 1)
        {
            return 0;
        }
        else
        {
            EntityItem item = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, stack);
            item.delayBeforeCanPickup = 40;
            item.motionX += xMot;
            item.motionY += 0.1;
            item.motionZ += zMot;
            player.joinEntityItemWithWorld(item);
            player.addStat(StatList.dropStat, 1);
        }
        return stack.stackSize;
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
