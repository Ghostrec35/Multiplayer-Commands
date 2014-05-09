package me.ghosrec35.mpc.command;

import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class CommandLift extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "lift";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "repair.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            World world = (World)player.worldObj;
            for(int y = ((int)player.posY) + 1; y < world.getActualHeight(); y++)
            {
                if(isAir(world.getBlock((int)player.posX, y, (int)player.posZ)) && isAir(world.getBlock((int)player.posX, y + 1, (int)player.posZ)) && isAir(world.getBlock((int)player.posX, y, (int)player.posZ)) && isAir(world.getBlock((int)player.posX, y + 3, (int)player.posZ)) && isAir(world.getBlock((int)player.posX, y + 4, (int)player.posZ)) && world.getBlock((int)player.posX, y - 1, (int)player.posZ) != Blocks.air)
                {
                    player.setPositionAndUpdate(player.posX, (double)y + 3, player.posZ);
                    return;
                }
            }
        }
    }
    
    public boolean isAir(Block b)
    {
        return b == Blocks.air;
    }
}
