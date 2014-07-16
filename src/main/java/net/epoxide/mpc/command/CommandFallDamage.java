package net.epoxide.mpc.command;

import java.util.List;

import net.epoxide.mpc.nbt.ExtendedPlayerData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandFallDamage extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "falldamage";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return (canCommandSenderUseCommand(sender) ? "commands.falldamage.usage" : "commands.falldamage.lackofperms");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length > 0)
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
                
                if(entityPlayer != null)
                {
                    NBTTagCompound tag = new NBTTagCompound();
                    ExtendedPlayerData properties = (ExtendedPlayerData)entityPlayer.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
                    tag.setBoolean("FallDamage", !properties.isInstaKillActive());
                    NBTTagCompound compound = new NBTTagCompound();
                    compound.setTag(ExtendedPlayerData.EXTENDED_PROPS_IDENT, tag);
                    properties.loadNBTData(compound);
                    sender.addChatMessage(new ChatComponentTranslation("commands.falldamage.success", (properties.isInstaKillActive() ? translate("commands.enabled") : translate("commands.disabled"))));
                }
            }
            else
            {
                EntityPlayer player = getCommandSenderAsPlayer(sender);
                NBTTagCompound tag = new NBTTagCompound();
                ExtendedPlayerData properties = (ExtendedPlayerData)player.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
                tag.setBoolean("FallDamage", !properties.isInstaKillActive());
                NBTTagCompound compound = new NBTTagCompound();
                compound.setTag(ExtendedPlayerData.EXTENDED_PROPS_IDENT, tag);
                properties.loadNBTData(compound);
                sender.addChatMessage(new ChatComponentTranslation("commands.falldamage.success", (properties.isInstaKillActive() ? translate("commands.enabled") : translate("commands.disabled"))));
            }
        }
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
