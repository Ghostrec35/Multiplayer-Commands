package me.ghosrec35.mpc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.minecraft.command.ICommand;

public class PlayerFile
{
    private List<ICommand> commands = new ArrayList<ICommand>();
    
    public PlayerFile(File file)
    {
        registerPlayerUseableCommands(file);
    }
    
    private void registerPlayerUseableCommands(File file)
    {
        try
        {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext())
            {
                String str = scanner.next();
                if(MultiplayerCommands.commandMap.containsKey(str))
                    commands.add(MultiplayerCommands.commandMap.get(str));
            }
            scanner.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public boolean hasPermission(ICommand command)
    {
        return commands.contains(command);
    }  
}
