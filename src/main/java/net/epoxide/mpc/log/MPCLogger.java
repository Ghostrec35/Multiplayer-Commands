package net.epoxide.mpc.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import cpw.mods.fml.common.FMLLog;

public class MPCLogger 
{
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger("MPC Log");
	
	public static void info(String msg)
	{
		logger.info(msg);
	}
	
	public static void error(String msg)
	{
		logger.error(msg);
	}
	
	public static void debug(String msg)
	{
		logger.debug(msg);
	}
	
	public static void warn(String msg)
	{
		logger.warn(msg);
	}
}
