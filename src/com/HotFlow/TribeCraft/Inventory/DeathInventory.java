package com.HotFlow.TribeCraft.Inventory;

import com.HotFlow.TribeCraft.Inventory.Item.ArmorType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.inventory.ItemStack;

/**
 * @author HotFlow
 */
public class DeathInventory
{
    public List<ItemStack> items = new ArrayList<ItemStack>();
    public HashMap<ArmorType,ItemStack> equiments = new HashMap<ArmorType,ItemStack>();
}
