package me.ghosrec35.mpc.nbt;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import me.ghosrec35.mpc.util.WorldCoordinates;

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
    }
}
