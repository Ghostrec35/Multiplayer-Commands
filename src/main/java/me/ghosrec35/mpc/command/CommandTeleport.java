package me.ghosrec35.mpc.command;

import java.util.List;

import me.ghosrec35.mpc.nbt.ExtendedPlayerData;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandTeleport extends CommandMPCBase
{

    @Override
    public String getCommandName()
    {
        return "tp";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.tp.mpc.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender) && params.length == 1)
        {
            List<EntityPlayerMP> playerList = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList;
            EntityPlayer target = null;
            for(int i = 0; i < playerList.size(); i++)
            {
                if(playerList.get(i).getDisplayName().equalsIgnoreCase(params[0]))
                {
                    target = playerList.get(i);
                    break;
                }
            }
            
            if(target != null)
            {
                EntityPlayer player = getCommandSenderAsPlayer(sender);
                ExtendedPlayerData data = (ExtendedPlayerData)player.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
                player.setPositionAndUpdate(target.posX, target.posY, target.posZ);
                NBTTagCompound tag = new NBTTagCompound();
                data.saveNBTData(tag);
                NBTTagCompound innerTag = (NBTTagCompound) tag.getTag(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
                innerTag.setDouble("LastPositionX", player.posX);
                innerTag.setDouble("LastPositionY", player.posY);
                innerTag.setDouble("LastPositionZ", player.posZ);
                tag.setTag(ExtendedPlayerData.EXTENDED_PROPS_IDENT, innerTag);
                data.loadNBTData(tag);
            }
        }
    }
}
