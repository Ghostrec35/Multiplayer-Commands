package net.epoxide.mpc.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class BaseMethodVisitor extends MethodVisitor 
{	
	public BaseMethodVisitor() 
	{
		super(Opcodes.ASM4, null);
	}
	
	public final void setMethodVisitor(MethodVisitor mv)
	{
		this.mv = mv;
	}
}
