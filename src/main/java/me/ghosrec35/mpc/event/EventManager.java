package me.ghosrec35.mpc.event;

import me.ghosrec35.mpc.nbt.ExtendedPlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventManager
{   
    @SubscribeEvent
    public void onEntityHurt(LivingDeathEvent event)
    {
        if(event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)event.entity;
            ExtendedPlayerData data = (ExtendedPlayerData) player.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
            
            NBTTagCompound tag = new NBTTagCompound();
            data.saveNBTData(tag);
            NBTTagCompound compound = (NBTTagCompound) tag.getTag(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
            compound.setDouble("LastDeathPosX", player.posX);
            compound.setDouble("LastDeathPosY", player.posY);
            compound.setDouble("LastDeathPosZ", player.posZ);
            tag.setTag(ExtendedPlayerData.EXTENDED_PROPS_IDENT, compound);
            data.loadNBTData(tag);
        }
    }
    
    @SubscribeEvent
    public void onEntityConstruction(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer)
            event.entity.registerExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT, new ExtendedPlayerData((EntityPlayer) event.entity));
    }
}
