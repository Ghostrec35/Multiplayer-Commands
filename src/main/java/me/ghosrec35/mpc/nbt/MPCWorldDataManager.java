package me.ghosrec35.mpc.nbt;

import java.util.HashMap;
import java.util.Map;

import me.ghosrec35.mpc.util.WorldCoordinates;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class MPCWorldDataManager
{
    public Map<String, WorldCoordinates> warpList = new HashMap<String, WorldCoordinates>();
 
    private NBTBasedSaveFile worldDataFile;
    
    public MPCWorldDataManager(String worldName, String dataFileName)
    {
        worldDataFile = SaveFileUtilities.buildNBTSaveFile(worldName, dataFileName);
        loadWarpList();
    }

    private void loadWarpList()
    {
        NBTTagCompound nbtEntry = worldDataFile.getData();
        loadWarpEntries(nbtEntry);
    }
    
    public void loadWarpEntries(NBTTagCompound tag)
    {
        NBTTagList warpNames = tag.getTagList("WarpNames", 8);
        NBTTagList warpLocations = tag.getTagList("WarpLocations", 8);
        assert warpNames.tagCount() == warpLocations.tagCount() : "An error ocurred with Warps! Please report this to the mod author!";
        warpList.clear();
        for(int i = 0; i < warpNames.tagCount(); i++)
        {
            String warpName = warpNames.getCompoundTagAt(i).getString("WarpName");
            int[] warpLocation = warpLocations.getCompoundTagAt(i).getIntArray("WarpLocations");
            warpList.put(warpName, new WorldCoordinates(warpLocation[0], warpLocation[1], warpLocation[2]));
        }
    }
    
    public void addWarpEntry(String warpName, int x, int y, int z)
    {
        NBTTagCompound tag = worldDataFile.getData();
        NBTTagList warpNames = tag.getTagList("WarpNames", 8);
        NBTTagList warpLocations = tag.getTagList("WarpLocations", 8);
        warpNames.appendTag(new NBTTagString(warpName));
        warpLocations.appendTag(new NBTTagIntArray(new int[]{x, y, z}));
        tag.setTag("WarpNames", warpNames);
        tag.setTag("WarpLocations", warpLocations);
        loadWarpEntries(tag);
    }
    
    public void removeWarpEntry(String warpName)
    {
        NBTTagCompound tag = worldDataFile.getData();
        NBTTagList warpNames = tag.getTagList("WarpNames", 8);
        NBTTagList warpLocations = tag.getTagList("WarpLocations", 8);
        for(int i = 0; i < warpNames.tagCount(); i++)
        {
            warpNames.getCompoundTagAt(i).removeTag("WarpName");
            warpLocations.getCompoundTagAt(i).removeTag("WarpLocation");
        }
        tag.setTag("WarpNames", warpNames);
        tag.setTag("WarpLocations", warpLocations);
        loadWarpEntries(tag);
    }
}
