package me.ghosrec35.mpc.event;

import me.ghosrec35.mpc.nbt.ExtendedPlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventManager
{   
    @SubscribeEvent
    public void onEntityConstruction(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer)
            event.entity.registerExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT, new ExtendedPlayerData((EntityPlayer) event.entity));
    }
}
