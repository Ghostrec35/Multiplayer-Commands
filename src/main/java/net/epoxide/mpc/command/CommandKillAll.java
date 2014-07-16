package net.epoxide.mpc.command;

import java.util.List;

import net.epoxide.mpc.MultiplayerCommands;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
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
                int numKilled = 0;
                Class<? extends Entity> type = MultiplayerCommands.entityMap.get(params[0]);
                for(Entity entity : (List<Entity>)world.loadedEntityList)
                {
                    if(entity.getClass() == type)
                    {
                        entity.setDead();
                        numKilled++;
                    }
                }
                
                sender.addChatMessage(new ChatComponentTranslation("commands.killall.success", numKilled));
            }
            else
            {
                int numKilled = 0;
                for(Entity entity : (List<Entity>)world.loadedEntityList)
                {
                    if(entity instanceof EntityLiving && !(entity instanceof EntityPlayer))
                    {
                        entity.setDead();
                        numKilled++;
                    }
                }   
                
                sender.addChatMessage(new ChatComponentTranslation("commands.killall.success", numKilled));
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
