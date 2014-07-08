package net.epoxide.mpc.command;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class CommandDrop extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "drop";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.drop.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            World world = (World)player.worldObj;
            for(int y = ((int)player.posY) - 2; y > 0; y--)
            {
                if(isAir(world.getBlock((int)player.posX, y, (int)player.posZ)) && isAir(world.getBlock((int)player.posX, y + 1, (int)player.posZ)) && isViablePlatform(world.getBlock((int)player.posX, y - 1, (int)player.posZ)))
                {
                    player.setPositionAndUpdate(player.posX, (double)y, player.posZ);
                    return;
                }
            }
        }
    }
    
    public boolean isAir(Block b)
    {
        return b == Blocks.air;
    }
    
    public boolean isViablePlatform(Block b)
    {
        return !((b instanceof BlockLiquid) || (b instanceof BlockBush) || (b instanceof BlockTallGrass));
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
