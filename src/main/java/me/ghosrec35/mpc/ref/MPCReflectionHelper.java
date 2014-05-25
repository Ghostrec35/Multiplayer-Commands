package me.ghosrec35.mpc.ref;

import java.lang.reflect.Field;
import java.util.HashMap;

public class MPCReflectionHelper 
{	
	public static HashMap<String, Field> genFieldMap(Object obj)
	{
		HashMap<String, Field> fields = new HashMap<String, Field>();
		getFields(obj.getClass(), fields);
		return fields;
	}
	
	public static HashMap<String, Field> getFields(Class<?> clazz, HashMap<String, Field> map)
	{
		for(Field f : clazz.getDeclaredFields())
		{
			map.put(f.getName(), f);
		}
		
		if(clazz.getSuperclass() != null)
			getFields(clazz.getSuperclass(), map);
		
		return map;
	}
	
	public static Field getClassField(String name, Object obj)
	{
		Class<?> clazzToBeParsed = obj.getClass();
		while(clazzToBeParsed != null)
		{
			Field[] fields = clazzToBeParsed.getDeclaredFields();
			for(Field f : fields)
			{
				if(f.getName().equals(name))
				{
					return f;
				}
			}
			clazzToBeParsed = clazzToBeParsed.getSuperclass();
		}
		return null;
	}
}
