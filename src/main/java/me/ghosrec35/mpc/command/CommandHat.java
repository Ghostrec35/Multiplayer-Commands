package me.ghosrec35.mpc.command;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;

public class CommandHat extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "hat";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.hat.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length > 1)
            {
                EntityPlayer entityPlayer = null;
                WorldServer[] servers = FMLCommonHandler.instance().getMinecraftServerInstance().worldServers;
                for(int i = 0; i < servers.length; i++)
                {
                    for(EntityPlayer player : (List<EntityPlayer>)servers[i].playerEntities)
                    {
                        if(player.getDisplayName().equalsIgnoreCase(params[0]))
                        {
                            entityPlayer = player;
                            break;
                        }
                    }
                }
                ItemStack stack;
                try
                {
                    stack = new ItemStack(Item.getItemById(Integer.parseInt(params[1])));
                }
                catch(Exception e)
                {
                    stack = new ItemStack((Item)Item.itemRegistry.getObject(params[1].toLowerCase()));
                }
                
                if(entityPlayer != null)
                {
                    entityPlayer.inventory.armorInventory[entityPlayer.inventory.armorInventory.length - 1] = stack;
                }
            }
            else
            {
                EntityPlayer player = getCommandSenderAsPlayer(sender);
                ItemStack temp = player.inventory.armorInventory[player.inventory.armorInventory.length - 1];
                player.inventory.armorInventory[player.inventory.armorInventory.length - 1] = player.getCurrentEquippedItem();
                player.inventory.mainInventory[player.inventory.currentItem] = temp;
            }
        }
    }
}
