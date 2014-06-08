package me.ghosrec35.mpc.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandBroadcast extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "broadcast";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.broadcast.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender) && params.length > 0)
        {
            StringBuilder broadcastMessage = new StringBuilder(params[0]);
            for(int i = 1; i < params.length; i++)
            {
                broadcastMessage.append(" " + params[i]);
            }
            
            ChatComponentText text = new ChatComponentText("[Server] " + broadcastMessage.toString());
            ChatStyle style = new ChatStyle();
            style.setColor(EnumChatFormatting.LIGHT_PURPLE);
            text.setChatStyle(style); 
            FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(text);
        }
    }

	@Override
	public boolean hasProperParams(String[] params) {
		// TODO Do basic param checks
		return false;
	}
}
