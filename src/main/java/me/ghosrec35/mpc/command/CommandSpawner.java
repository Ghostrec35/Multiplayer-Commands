package me.ghosrec35.mpc.command;

import java.lang.reflect.Constructor;

import me.ghosrec35.mpc.MultiplayerCommands;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandSpawner extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "spawner";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "spawner.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length == 1)
            {
                EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
                Vec3 vec3 = player.getLookVec().normalize();
                World world = player.worldObj;
                
                double targetX = player.posX;
                double targetY = player.posY + player.getEyeHeight() - 0.10000000149011612D;
                double targetZ = player.posZ;
                
                while(Math.abs(targetX) < (Math.abs(player.posX) + Math.abs(vec3.xCoord * 100)))
                {
                    targetX += vec3.xCoord;
                    targetY += vec3.yCoord;
                    targetZ += vec3.zCoord;
                    
                    if(!world.isAirBlock((int)targetX, (int)targetY, (int)targetZ))
                    {
                        Block block = world.getBlock((int)targetX, (int)targetY, (int)targetZ);
                        if(block != null && block instanceof BlockMobSpawner)
                        {
                            Class<? extends Entity> entity = MultiplayerCommands.entityMap.get(params[0]);
                            Constructor<?> cons;
                            try
                            {
                                cons = entity.getConstructor(world.getClass());
                                TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity((int)targetX, (int)targetY, (int)targetZ);
                                spawner.func_145881_a().func_98265_a((Entity)cons.newInstance(world));
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}
