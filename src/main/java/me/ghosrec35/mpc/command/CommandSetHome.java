package me.ghosrec35.mpc.command;

import me.ghosrec35.mpc.nbt.ExtendedPlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class CommandSetHome extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "sethome";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.sethome.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setDouble("PlayerHomeX", player.posX);
            tag.setDouble("PlayerHomeY", player.posY);
            tag.setDouble("PlayerHomeZ", player.posZ);
            ExtendedPlayerData properties = (ExtendedPlayerData)player.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
            NBTTagCompound compound = new NBTTagCompound();
            compound.setTag(ExtendedPlayerData.EXTENDED_PROPS_IDENT, tag);
            properties.loadNBTData(compound);
        }
    }
    
    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
