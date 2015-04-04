package com.HotFlow.TribeCraft.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import com.HotFlow.TribeCraft.Inventory.Item.ArmorType;

/**
 * @author HotFlow
 */
public class DeathInventory {
	public List<ItemStack> items = new ArrayList<ItemStack>();
	public HashMap<ArmorType, ItemStack> equiments = new HashMap<ArmorType, ItemStack>();
}
