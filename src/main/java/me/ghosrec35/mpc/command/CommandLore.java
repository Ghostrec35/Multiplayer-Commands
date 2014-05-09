package me.ghosrec35.mpc.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandLore extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "lore";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "lore.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            ChatStyle style = new ChatStyle();
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            ItemStack stack = player.getCurrentEquippedItem();
            if(stack != null)
            {
                for(String val : params[0].split(":"))
                {
                    int value = Integer.parseInt(val);
                    if(value < 16)
                    {
                        style.setColor(EnumChatFormatting.values()[value]);
                    }
                    else
                    {
                        switch(EnumChatFormatting.values()[value])
                        {
                        case OBFUSCATED:
                            style.setObfuscated(true);
                            break;
                        case BOLD:
                            style.setBold(true);
                            break;
                        case STRIKETHROUGH:
                            style.setStrikethrough(true);
                            break;
                        case UNDERLINE:
                            style.setUnderlined(true);
                            break;
                        case ITALIC:
                            style.setItalic(true);
                            break;
                        default: 
                            break;
                        }
                    }
                }
                StringBuilder name = new StringBuilder();
                for(String str : params[1].split(":"))
                {
                    name.append(str + " ");
                }
                if(!stack.hasTagCompound())
                    stack.setTagCompound(new NBTTagCompound());
                NBTTagList list = new NBTTagList();
                StringBuilder desc = new StringBuilder();
                for(int i = 1; i < params.length; i++)
                {
                    desc.append(params[i].replace(':', ' '));
                }

                NBTTagString strTag = new NBTTagString(style.getFormattingCode() + desc.toString());
                list.appendTag(strTag);
                stack.stackTagCompound.getCompoundTag("display").setTag("Lore", list);
            }
        }
    }
}
