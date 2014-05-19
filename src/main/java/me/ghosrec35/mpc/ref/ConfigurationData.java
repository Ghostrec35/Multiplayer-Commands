package me.ghosrec35.mpc.ref;

import net.minecraftforge.common.config.Configuration;

public class ConfigurationData
{
    public static final String CATEGORY_COMMANDS = "commands";
    
    public static boolean AIR;
    public static boolean BACK;
    public static boolean BROADCAST;
    public static boolean DAMAGE;
    public static boolean DELWARP;
    public static boolean DROP;
    public static boolean DROPALL;
    public static boolean ENCHANT;
    public static boolean EXPLODE;
    public static boolean FILLEDCHEST;
    public static boolean FLY;
    public static boolean GOD;
    public static boolean HEAL;
    public static boolean HOME;
    public static boolean HUNGER;
    public static boolean ICEAURA;
    public static boolean IGNITE;
    public static boolean ITEMNAME;
    public static boolean KILLALL;
    public static boolean LIFT;
    public static boolean LISTWARP;
    public static boolean LORE;
    public static boolean MAXHEALTH;
    public static boolean NICK;
    public static boolean REPAIR;
    public static boolean SEEINV;
    public static boolean SETHOME;
    public static boolean SETWARP;
    public static boolean SPAWNER;
    public static boolean SPEED;
    public static boolean TELEPORT;
    public static boolean TELEPORTREQUEST;
    public static boolean WARP;
    
    public static void setConfigValues(Configuration config)
    {
        AIR = config.get(CATEGORY_COMMANDS, "Air", true).getBoolean(true);
    }    
}
