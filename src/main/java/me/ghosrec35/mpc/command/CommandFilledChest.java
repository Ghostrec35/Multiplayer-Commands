package me.ghosrec35.mpc.command;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class CommandFilledChest extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "filledchest";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.filledchest.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            EntityPlayerMP player = getCommandSenderAsPlayer(sender);
            World world = player.worldObj;
            MovingObjectPosition pos = world.rayTraceBlocks(player.getLookVec(), player.getLookVec().addVector(1000, 1000, 1000));
            int[] position = getChestPositionFromMovingObject(pos);
            world.setBlock(position[0], position[1], position[2], Blocks.chest);
            
            FMLLog.severe("Setting chest at: (%d, %d, %d)", position[0], position[1], position[2]);
            /*
            world.setTileEntity(position[0], position[1], position[2], Blocks.chest.createNewTileEntity(world, 0));
            TileEntityChest chest = (TileEntityChest) world.getTileEntity(position[0], position[1], position[2]);
            int numSlots = chest.getSizeInventory();
            ItemStack stack = getItemStackFromParam(params[0]);
            for(int slot = 0; slot < numSlots; slot++)	
            	chest.setInventorySlotContents(slot, stack.copy());
            */
        }
    }

    private ItemStack getItemStackFromParam(String string) 
    {
		return new ItemStack((Item)Item.itemRegistry.getObject(Integer.valueOf(string)), 64);
	}

	private int[] getChestPositionFromMovingObject(MovingObjectPosition pos) 
	{
		int[] position = new int[]{pos.blockX, pos.blockY, pos.blockZ};
		switch(pos.sideHit)
		{
		case 0:
			position[1] -= 1;
			break;
		case 1: 
			position[1] += 1;
			break;
		case 2:
			position[0] += 1;
			break;
		case 3:
			position[0] -= 1;
			break;
		case 4:
			position[2] += 1;
			break;
		case 5:
			position[2] -= 1;
			break;
		}
		return position;
	}

	@Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
