package me.ghosrec35.mpc.asm;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@TransformerExclusions({"me.ghosrec35.mpc.asm"})
@MCVersion("1.7.2")
public class MPCLoadingPlugin implements IFMLLoadingPlugin 
{
	@Override
	public String[] getASMTransformerClass() 
	{
		return new String[]{"me.ghosrec35.mpc.asm.MPCClassTransformer"};
	}

	@Override
	public String getModContainerClass() 
	{
		return null;
	}

	@Override
	public String getSetupClass() 
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) 
	{
	}

	@Override
	public String getAccessTransformerClass() 
	{
		return null;
	}
}
