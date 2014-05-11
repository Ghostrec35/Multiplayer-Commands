package me.ghosrec35.mpc.nbt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTBasedSaveFile
{
    private File fileToSave;
    
    public NBTBasedSaveFile(File file)
    {
        try
        {
             if(!file.exists())
                 file.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        fileToSave = file;
    }
    
    public void addData(String key, Object data)
    {
        NBTTagCompound tag = getData();
        writeToTag(tag, key, data);
        try
        {
            FileOutputStream fos = new FileOutputStream(fileToSave);
            CompressedStreamTools.writeCompressed(tag, fos);
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addData(NBTTagCompound tag)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(fileToSave);
            CompressedStreamTools.writeCompressed(tag, fos);
            fos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public NBTTagCompound getData()
    {
        try
        {
            FileInputStream fis = new FileInputStream(fileToSave);
            NBTTagCompound tag = CompressedStreamTools.readCompressed(fis);
            fis.close();
            return tag;
        }
        catch (IOException e)
        {
            try
            {
                FileOutputStream fos = new FileOutputStream(fileToSave);
                CompressedStreamTools.writeCompressed(new NBTTagCompound(), fos);
                fos.close();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
            return getData();
        }
    }
    
    private void writeToTag(NBTTagCompound tag, String key, Object data)
    {
        if(data instanceof Integer)
            tag.setInteger(key, (Integer)data);
        else if(data instanceof Byte)
            tag.setByte(key, (Byte)data);
        else if(data instanceof byte[])
            tag.setByteArray(key,(byte[])data);
        else if(data instanceof Double)
            tag.setDouble(key, (Double)data);
        else if(data instanceof Float)
            tag.setFloat(key, (Float)data);
        else if(data instanceof int[])
            tag.setIntArray(key, (int[])data);
        else if(data instanceof NBTTagList)
            tag.setTag(key, (NBTTagList)data);
        else if(data instanceof Long)
            tag.setLong(key, (Long)data);
        else if(data instanceof Short)
            tag.setShort(key, (Short)data);
        else if(data instanceof String)
            tag.setString(key, (String)data);
    }
    
    public String toString()
    {
        return fileToSave.getAbsolutePath();
    }
}