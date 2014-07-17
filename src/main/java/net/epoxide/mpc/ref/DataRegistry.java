package net.epoxide.mpc.ref;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModContainer.Disableable;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

public class DataRegistry
{
    public static HashMap<String, Class<? extends Entity>> entityMap = new HashMap<String, Class<? extends Entity>>();
    public static Map<String, Enchantment> enchantmentMap = new HashMap<String, Enchantment>();
    
    public static void initialize()
    {
        for(Class<? extends Entity> entityClass : ((Map<String, Class<? extends Entity>>) EntityList.stringToClassMapping).values())
        {
            entityMap.put(genEntityCommandKey(entityClass.getName()), entityClass);
        }
        for(Enchantment enchant : Enchantment.enchantmentsList)
        {
            if(enchant != null)
                enchantmentMap.put(enchant.getName().toLowerCase(), enchant);
        }
    }
    
    private static String genEntityCommandKey(String className)
    {
        String[] entityNameParts = className.toLowerCase().split("\\.");
        String entityName = entityNameParts[entityNameParts.length - 1].replaceAll("entity", "");
        return entityName;
    }
}
