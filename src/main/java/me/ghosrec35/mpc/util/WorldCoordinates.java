package me.ghosrec35.mpc.util;

public class WorldCoordinates
{
    private double posX;
    private double posY;
    private double posZ;
    
    public WorldCoordinates(double x, double y, double z)
    {
        posX = x;
        posY = y;
        posZ = z;
    }
    
    public double getX()
    {
        return posX;
    }
    
    public double getY()
    {
        return posY;
    }
    
    public double getZ()
    {
        return posZ;
    }
}
