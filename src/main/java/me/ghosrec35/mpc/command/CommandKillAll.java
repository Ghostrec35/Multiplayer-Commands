package me.ghosrec35.mpc.command;

import java.util.List;

import me.ghosrec35.mpc.MultiplayerCommands;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommandKillAll extends CommandMPCBase
{
    @Override
    public int getRequiredPermissionLevel()
    {
        return 1;
    }
    
    @Override
    public String getCommandName()
    {
        return "killall";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "command.killall.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            World world = (World)(getCommandSenderAsPlayer(sender).worldObj);
            if(params.length > 0)
            {
                Class<? extends Entity> type = MultiplayerCommands.entityMap.get(params[0]);
                for(Entity entity : (List<Entity>)world.loadedEntityList)
                {
                    if(entity.getClass() == type)
                        entity.setDead();
                }
            }
            else
            {
                for(Entity entity : (List<Entity>)world.loadedEntityList)
                {
                    if(entity instanceof EntityLiving && !(entity instanceof EntityPlayer))
                    {
                        entity.setDead();
                    }
                }   
            }
        }
    }
    
    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
