package me.ghosrec35.mpc.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class CommandSmelt extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "smelt";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.smelt.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            ItemStack stackInHand = player.inventory.getStackInSlot(player.inventory.currentItem);
            ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(stackInHand);
            if(result != null)
            {
                result.stackSize = stackInHand.stackSize;
                player.inventory.mainInventory[player.inventory.currentItem] = result;
            }
        }
    }
}
