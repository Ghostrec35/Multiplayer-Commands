package me.ghosrec35.mpc.command;

import java.awt.image.BufferedImage;

import me.ghosrec35.mpc.client.resource.CapeImageBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class CommandCape extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "cape";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return canCommandSenderUseCommand(sender) ? "commands.cape.usage" : "commands.cape.lackofperms";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))  
        {
            if(params.length > 0 & params.length < 3)
            {
                if(params.length == 2)
                {
                    EntityPlayer player = getCommandSenderAsPlayer(sender).worldObj.getPlayerEntityByName(params[0]);
                    
                    ITextureObject cape = buildTexture(params[1]);
                    ResourceLocation location = new ResourceLocation("cloaks", player.getDisplayName());
                    Minecraft.getMinecraft().renderEngine.loadTexture(location, cape);
                }
            }
            else
            {
                EntityPlayer player = getCommandSenderAsPlayer(sender);
                ITextureObject cape = buildTexture(params[0]);
                ResourceLocation location = new ResourceLocation("cloaks", player.getDisplayName());
                Minecraft.getMinecraft().renderEngine.loadTexture(location, cape);
            }
        }
    }

    private ITextureObject buildTexture(String url)
    {
        return new ThreadDownloadImageData(url, null, new CapeImageBuffer());
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
