package me.ghosrec35.mpc.client;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import me.ghosrec35.mpc.CommonProxy;

public class ClientProxy extends CommonProxy
{
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch(ID)
        {
        case 0: return new GuiInventory(world.getPlayerEntityByName(player.getDisplayName()));
        }
        return null;
    }
    
}
