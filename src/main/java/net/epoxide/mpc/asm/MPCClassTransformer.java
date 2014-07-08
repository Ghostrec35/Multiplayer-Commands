package net.epoxide.mpc.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import com.jcraft.jorbis.Block;

public class MPCClassTransformer implements IClassTransformer 
{
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) 
	{
		if(name.equals(MPCObfuscationTable.lookup(Block.class)))
		{
			return ASMHelper.injectHook(bytes, null, transformedName, transformedName, transformedName);
		}	
		return bytes;
	}
}
