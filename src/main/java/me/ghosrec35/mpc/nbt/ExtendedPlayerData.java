package me.ghosrec35.mpc.nbt;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayerData implements IExtendedEntityProperties
{
    public static final String EXTENDED_PROPS_IDENT = "MPCommandsPlayerData";
    
    private double playerHomeX;
    private double playerHomeY;
    private double playerHomeZ;
    
    private boolean isGod;
    
    private EntityPlayer player;
    
    public ExtendedPlayerData(EntityPlayer entityPlayer)
    {
        player = entityPlayer;
        playerHomeX = player.posX;
        playerHomeY = player.posY;
        playerHomeZ = player.posZ;
    }
    
    @Override
    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setDouble("PlayerHomeX", playerHomeX);
        tag.setDouble("PlayerHomeY", playerHomeY);
        tag.setDouble("PlayerHomeZ", playerHomeZ);
        compound.setTag(EXTENDED_PROPS_IDENT, tag);
        tag.setBoolean("MPCGodMode", isGod);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound props = (NBTTagCompound) compound.getTag(EXTENDED_PROPS_IDENT);
        playerHomeX = props.getDouble("PlayerHomeX");
        playerHomeY = props.getDouble("PlayerHomeY");
        playerHomeZ = props.getDouble("PlayerHomeZ");
        
        isGod = compound.getBoolean("MPCGodMode");
        
        if(isGod && player.getHealth() != player.getMaxHealth())
        {
            player.setHealth(player.getMaxHealth());
        }
    }
    
    @Override
    public void init(Entity entity, World world)
    {
    }

    public double getHomeXCoordinate()
    {
        return playerHomeX;
    }
    
    public double getHomeYCoordinate()
    {
        return playerHomeY;
    }
    
    public double getHomeZCoordinate()
    {
        return playerHomeZ;
    }

    public boolean isGodActivated()
    {
        return isGod;
    }
}
