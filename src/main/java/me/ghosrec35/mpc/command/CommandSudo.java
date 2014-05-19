package me.ghosrec35.mpc.command;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandSudo extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "sudo";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.sudo.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender) && params.length > 2)
        {
            EntityPlayer commandUser = getCommandSenderAsPlayer(sender);
            EntityPlayer target = commandUser.worldObj.getPlayerEntityByName(params[0]);
            ICommand command = (ICommand) FMLCommonHandler.instance().getMinecraftServerInstance().getCommandManager().getCommands().get(params[0]);
            ICommandSender newSender = (ICommandSender)target;
            String[] newParams = new String[params.length - 2];
            for(int i = 0; i < newParams.length; i++)   
            {
                newParams[i] = params[i + 2];
            }
            command.processCommand(newSender, params);
        }
    }
}
