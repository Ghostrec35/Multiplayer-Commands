package net.epoxide.mpc.command;

import java.io.File;

import net.epoxide.mpc.nbt.NBTBasedSaveFile;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandSetWarp extends CommandMPCBase
{
    public NBTBasedSaveFile saveFile = new NBTBasedSaveFile(new File(FMLCommonHandler.instance().getMinecraftServerInstance().getWorldName(), "warp_data.dat"));
    
    @Override
    public String getCommandName()
    {
        return "setwarp";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.setwarp.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length == 1)
            {
                //Set Warp at player's current position with name params[0]
                
            }
            else
            if(params.length == 4)
            {
                //Set Warp at (params[1], params[2], params[3]), with name params[0]
            }
        } 
    }
    
    private void attemptWarpRegistration(String warpName, int[] warpCoords)
    {
        assert warpCoords.length == 3: "The value of the coordinates should be a value that is logical, with x, y, and z coordinates.";
        
        NBTTagIntArray intArray = new NBTTagIntArray(warpCoords);
        NBTTagCompound tag = saveFile.getData();
        NBTTagList warpNames = tag.getTagList("WarpNames", 8);
        boolean isPresent = false;
        for(int i = 0; i < warpNames.tagCount(); i++)
        {
            if(warpNames.getCompoundTagAt(i).getString("WarpName").equalsIgnoreCase(warpName))
            {
                isPresent = true;
            }
        }
        if(!isPresent)
            warpNames.appendTag(new NBTTagString(warpName));
        
        tag.setTag("WarpNames", warpNames);
        saveFile.addData(tag);
    }
    
    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
