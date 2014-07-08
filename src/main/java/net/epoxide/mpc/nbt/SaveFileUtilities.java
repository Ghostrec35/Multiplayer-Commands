package net.epoxide.mpc.nbt;

import java.io.File;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class SaveFileUtilities
{
    public static NBTBasedSaveFile buildNBTSaveFile(String worldName, String saveFileName)
    {
        File file = new File(FMLCommonHandler.instance().getSavesDirectory() + "/" + worldName, saveFileName);
        NBTBasedSaveFile saveFile = new NBTBasedSaveFile(file);
        return saveFile;
    }
    
    public static void writeToFile(NBTBasedSaveFile file, NBTTagCompound tag)
    {
        file.addData(tag);
    }
    
    public static Object readValueFromFile(NBTBasedSaveFile file, String nbtKey)
    {
        NBTTagCompound compound = file.getData();
        return compound.getTag(nbtKey);
    }
}
