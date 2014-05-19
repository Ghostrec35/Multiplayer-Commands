package me.ghosrec35.mpc.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandRename extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "rename";
    }
    
    @Override
    public List getCommandAliases()
    {
        return genAliasList("itemname");
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.rename.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length > 0)
            {
                EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
                ItemStack stack = player.getCurrentEquippedItem();
                if(stack != null)
                {
                    ChatStyle style = new ChatStyle();
                    for(String val : params[1].split(":"))
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
                    for(String str : params[0].split(":"))
                    {
                        name.append(str + " ");
                    }
                    
                    if(!stack.hasTagCompound())
                        stack.setTagCompound(new NBTTagCompound());
                    
                    stack.setStackDisplayName(style.getFormattingCode() + name.toString());
                }
            }
        }
    }
}
