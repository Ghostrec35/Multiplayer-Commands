package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.MultiplayerCommands;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class CommandEnchant extends CommandBase
{   
    @Override
    public String getCommandName()
    {
        return "enchant";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "enchant.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender) && params.length == 3)
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            ItemStack stack = player.getCurrentEquippedItem();
            if(params[0].equalsIgnoreCase("a") || params[0].equalsIgnoreCase("add"))
            {
                if(stack != null)
                {
                    Enchantment enchant;
                    int level;
                    try
                    {
                        enchant = Enchantment.enchantmentsList[Integer.parseInt(params[1])];
                        level = Integer.parseInt(params[2]);
                    }
                    catch(Exception e)
                    {
                        enchant = MultiplayerCommands.enchantmentMap.get(params[1].toLowerCase());
                        level = Integer.parseInt(params[2]);
                    }  
                    
                    if(enchant != null)
                        stack.addEnchantment(enchant, level);
                    
                    stack.getEnchantmentTagList();
                }
            }
            else
            if(params[0].equalsIgnoreCase("r") || params[0].equalsIgnoreCase("remove"))
            {
                Enchantment enchant = Enchantment.enchantmentsList[Integer.parseInt(params[1])];
                int level = Integer.parseInt(params[2]); 
                NBTTagCompound nbtTag = (NBTTagCompound) stack.stackTagCompound.copy();
                
                NBTTagList ench = stack.stackTagCompound.getTagList("ench", 10);
                for(int i = 0; i < ench.tagCount(); i++)
                {
                    ench.getCompoundTagAt(i);
                }
                stack.setTagCompound(nbtTag);
            }
        }
    }
}
