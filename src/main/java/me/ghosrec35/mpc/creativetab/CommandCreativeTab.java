package me.ghosrec35.mpc.creativetab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.ghosrec35.mpc.MultiplayerCommands;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CommandCreativeTab extends CreativeTabs
{
    public static Map<String, CommandCreativeTab> tabMap = new HashMap<String, CommandCreativeTab>();
    
    private Item item;
    private List<ItemStack> items = new ArrayList<ItemStack>();

    public CommandCreativeTab(String label, Item i)
    {
        super(label);
        item = i;
    }

    @Override
    public Item getTabIconItem()
    {
        return item;
    }
    
    public void addItem(ItemStack item)
    {
        if(!items.contains(item))
            items.add(item);
    }
    
    public void removeItem(ItemStack item)
    {
        if(items.contains(item))
            items.remove(item);
    }
    
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return this.getTabLabel();
    }
    
    @SideOnly(Side.CLIENT)
    public void displayAllReleventItems(List itemsList)
    {
        for(ItemStack stack : items)
        {
            itemsList.add(stack);
        }
        super.displayAllReleventItems(itemsList);
    }
    
    public static void dynamicallyGenerateTab(String label, ItemStack i)
    {
        CommandCreativeTab.tabMap.put(label, new CommandCreativeTab(label, i.getItem()));
    }
}
