package net.epoxide.mpc.permission;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.epoxide.mpc.registry.CommandRegistry;
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
                if(CommandRegistry.commandMap.containsKey(str))
                    commands.add(CommandRegistry.commandMap.get(str));
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
