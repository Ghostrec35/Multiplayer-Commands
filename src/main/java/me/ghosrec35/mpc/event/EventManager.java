package me.ghosrec35.mpc.event;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Random;

import me.ghosrec35.mpc.nbt.ExtendedPlayerData;
import me.ghosrec35.mpc.ref.MPCReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventManager
{   
    private Random rand = new Random();
    
    @SubscribeEvent 
    public void onPlayerCloned(net.minecraftforge.event.entity.player.PlayerEvent.Clone event)
    {
    	EntityPlayer oldPlayer = event.entityPlayer;
    	EntityPlayer newPlayer = event.original;
    	Field f;
    	if(oldPlayer.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT) != null)
    	{
			try 
			{
				f = MPCReflectionHelper.getClassField("extendedProperties", newPlayer);
				if(f != null)
				{
					f.setAccessible(true);
			    	HashMap<String, IExtendedEntityProperties> newPropMap = (HashMap<String, IExtendedEntityProperties>) f.get((Entity)newPlayer);
			    	IExtendedEntityProperties props = oldPlayer.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
			    	newPropMap.put(ExtendedPlayerData.EXTENDED_PROPS_IDENT, props);
			    	props.init(newPlayer, newPlayer.worldObj);
				}
			} 
			catch (SecurityException e) 
			{
				e.printStackTrace();
			}
			catch (IllegalArgumentException e) 
			{
				e.printStackTrace();
			} 
			catch (IllegalAccessException e) 
			{
				e.printStackTrace();
			}
    	}
    }
    
    @SubscribeEvent
    public void onPlayerFall(LivingFallEvent event)
    {
        if(event.entityLiving instanceof EntityPlayer)
        {
            ExtendedPlayerData data = (ExtendedPlayerData)((EntityPlayer)event.entityLiving).getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
            if(data.isFallDamageInactive())
            {
                event.setCanceled(true);
            }
        }
    }
    
    @SubscribeEvent
    public void onPlayerAttemptBlockHarvest(PlayerInteractEvent event)
    {
        if(event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK)
        {
            ExtendedPlayerData data = (ExtendedPlayerData)event.entityPlayer.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
            if(data.isInstaMineActive())
            {
                Block block = event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z);
                event.entityPlayer.worldObj.setBlockToAir(event.x, event.y, event.z);
                ItemStack stack = new ItemStack(block.getItemDropped(0, rand, 0));
                EntityItem item = new EntityItem(event.entityPlayer.worldObj, event.x, event.y, event.z, stack);
                event.entityPlayer.worldObj.spawnEntityInWorld(item);
            }
        }
    }
    
    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event)
    {
        ExtendedPlayerData data = (ExtendedPlayerData)event.entityPlayer.getExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT);
        if(data.isInstaKillActive())
        {
            System.out.println("Is in fact executing.");
            if(event.target instanceof EntityLivingBase)
            {
                EntityLivingBase living = (EntityLivingBase)event.target;
                living.attackEntityFrom(DamageSource.causePlayerDamage(event.entityPlayer), living.getHealth());
            }
            else
            {
                event.target.setDead();
            }
        }
    }
    
    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event)
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
    public void onEntityDeath(LivingHurtEvent event)
    {
        if(event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)event.entity;
            
            if(player.capabilities.disableDamage)
            {
                event.setCanceled(true);
            }
        }
    }
    
    @SubscribeEvent
    public void onEntityConstruction(EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer)
            event.entity.registerExtendedProperties(ExtendedPlayerData.EXTENDED_PROPS_IDENT, new ExtendedPlayerData((EntityPlayer) event.entity));
    }
}
