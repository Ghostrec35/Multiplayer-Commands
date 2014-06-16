package me.ghosrec35.mpc.asm;

import java.util.HashMap;
import java.util.Map;

import com.jcraft.jorbis.Block;

import net.minecraft.world.World;


public class MPCObfuscationTable 
{
	private static final boolean isObfuscated;
	private static Map<Class<?>, String> obfLookup = new HashMap<Class<?>, String>();
	
	static
	{
		boolean temp = true;
		try
		{
			temp = !(World.class.getSimpleName().equals("World") ? true : false);
		}
		catch(Exception e)
		{ 
			// Ignore Exception
		}
		
		isObfuscated = temp;
		initialize();
	}

	public static String lookup(Class<?> clazz) 
	{
		return obfLookup.get(clazz);
	}
	
	private static void initialize() 
	{
		if(isSrcObfuscated())
		{
		    
		}
		else
		{
			
		}
	}
	
	public static boolean isSrcObfuscated()
	{
		return isObfuscated;
	}
}
