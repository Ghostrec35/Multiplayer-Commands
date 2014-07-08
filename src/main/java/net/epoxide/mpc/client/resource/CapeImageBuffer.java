package net.epoxide.mpc.client.resource;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.minecraft.client.renderer.IImageBuffer;

public class CapeImageBuffer implements IImageBuffer
{
    @Override
    public BufferedImage parseUserSkin(BufferedImage image)
    {
        int width = image.getWidth(null) <= 64 ? 64 : image.getWidth(null);
        int height = image.getHeight(null) <= 32 ? 32 : image.getHeight(null);
        
        BufferedImage cape = new BufferedImage(width, height, 2);
        Graphics graphics = cape.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return cape;
    }

	@Override
	public void func_152634_a() 
	{
	}
}
