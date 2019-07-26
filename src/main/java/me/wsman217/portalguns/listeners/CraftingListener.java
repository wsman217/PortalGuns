package main.java.me.wsman217.portalguns.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import main.java.me.wsman217.portalguns.api.PortalGunsAPI;

public class CraftingListener implements Listener {

	private static ArrayList<HashMap<Integer, ItemStack>> recipes = new ArrayList<HashMap<Integer, ItemStack>>();
	private static ArrayList<ItemStack> portalGuns = PortalGunsAPI.getPortalGuns();
	private static ArrayList<ItemStack> outputs = new ArrayList<ItemStack>();

	public static void addRecipe(HashMap<Integer, ItemStack> recipe) {
		recipes.add(recipe);
	}
	
	public static void addOutput(ItemStack output) {
		outputs.add(output);
	}

	@EventHandler
	public void onCraftEvent(CraftItemEvent e) {
		loop: if (e.getRawSlot() == 0) {
			ItemStack output = e.getInventory().getItem(0);
			for (ItemStack portalGun : portalGuns) {
				if (output.isSimilar(portalGun)) {
					break loop;
				}
			}
			
			for (ItemStack outs : outputs) {
				if (output.isSimilar(outs)) {
					break loop;
				}
			}
			return;
		}

		Inventory inv = e.getClickedInventory();

		if (inv == null)
			return;

		ArrayList<HashMap<Integer, ItemStack>> recipes = new ArrayList<HashMap<Integer, ItemStack>>();
		for (HashMap<Integer, ItemStack> map : CraftingListener.recipes) {
			recipes.add(map);
		}

		for (HashMap<Integer, ItemStack> recipe : CraftingListener.recipes) {
			for (int i = 0; i < 9; i++) {
				for (Integer indexes : recipe.keySet()) {
					if (i == indexes) {
						if (recipe.get(indexes) != null) {
							if (!inv.getItem(i + 1).isSimilar(recipe.get(indexes))) {
								recipes.remove(recipe);
							}
						}
					}
				}
			}
		}

		if (recipes.isEmpty()) {
			e.setCancelled(true);
			return;
		}
	}
}
