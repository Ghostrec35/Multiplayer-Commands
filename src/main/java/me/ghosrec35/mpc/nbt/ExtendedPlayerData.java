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
    
    private double lastDeathPosX;
    private double lastDeathPosY;
    private double lastDeathPosZ;
    
    private double lastPosX;
    private double lastPosY;
    private double lastPosZ;
    
    private boolean instaKillActive;
    
    private boolean instaMineActive;
    
    private boolean iceAura;
    
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
        
        tag.setDouble("LastDeathPosX", lastDeathPosX);
        tag.setDouble("LastDeathPosY", lastDeathPosY);
        tag.setDouble("LastDeathPosZ", lastDeathPosZ);
        
        tag.setDouble("LastPositionX", lastPosX);
        tag.setDouble("LastPositionY", lastPosY);
        tag.setDouble("LastPositionZ", lastPosZ);
        
        tag.setBoolean("InstaKill", instaKillActive);
        tag.setBoolean("InstaMine", instaMineActive);
        compound.setTag(EXTENDED_PROPS_IDENT, tag);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {
        NBTTagCompound props = (NBTTagCompound) compound.getTag(EXTENDED_PROPS_IDENT);
        playerHomeX = props.getDouble("PlayerHomeX");
        playerHomeY = props.getDouble("PlayerHomeY");
        playerHomeZ = props.getDouble("PlayerHomeZ");
        
        lastDeathPosX = props.getDouble("LastDeathPosX");
        lastDeathPosY = props.getDouble("LastDeathPosY");
        lastDeathPosZ = props.getDouble("LastDeathPosZ");
        
        lastPosX = props.getDouble("LastPositionX");
        lastPosY = props.getDouble("LastPositionY");
        lastPosZ = props.getDouble("LastPositionZ");
        
        instaKillActive = props.getBoolean("InstaKill");
        instaMineActive = props.getBoolean("InstaMine");
        System.out.println(instaKillActive);
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
    
    public double getDeathXCoordinate()
    {
        return lastDeathPosX;
    }
    
    public double getDeathYCoordinate()
    {
        return lastDeathPosY;
    }
    
    public double getDeathZCoordinate()
    {
        return lastDeathPosZ;
    }

    public double getBackXCoordinate()
    {
        return lastPosX;
    }
    
    public double getBackYCoordinate()
    {
        return lastPosY;
    }
    
    public double getBackZCoordinate()
    {
        return lastPosZ;
    }
    
    public boolean isIceAuraActivated()
    {
        return iceAura;
    }

    public boolean isInstaKillActive()
    {
        return instaKillActive;
    }

    public boolean isInstaMineActive()
    {
        return instaMineActive;
    }
}
