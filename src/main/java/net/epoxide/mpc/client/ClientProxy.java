package net.epoxide.mpc.client;

import net.epoxide.mpc.CommonProxy;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

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
