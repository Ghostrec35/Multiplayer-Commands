package net.epoxide.mpc.permission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GroupManager
{
    public static final String[] defaultFile = new String[]
            {
                "#"
            };
    
    
    private File groupFile;
    
    public GroupManager(File managerFile)
    {
        groupFile = managerFile;
        if(!groupFile.exists())
        {
            try
            {
                groupFile.createNewFile();
                writeDefaultValues();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void writeDefaultValues()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(groupFile);
            
            fos.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
