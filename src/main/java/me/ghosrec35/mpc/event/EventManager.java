package me.ghosrec35.mpc.event;

import me.ghosrec35.mpc.nbt.ExtendedPlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventManager
{   
    @SubscribeEvent
    public void onEntityHurt(LivingHurtEvent event)
    {
        if(event.entityLiving instanceof EntityPlayer)
        {
            ExtendedPlayerData data = (ExtendedPlayerData)((EntityPlayer)event.entityLiving).getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
            if(data.isGodActivated())
                event.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void onEntityConstruction(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer)
            event.entity.registerExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT, new ExtendedPlayerData((EntityPlayer) event.entity));
    }
}
