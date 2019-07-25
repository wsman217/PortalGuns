package main.java.me.wsman217.portalguns.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import main.java.me.wsman217.portalguns.api.PortalGunsAPI;

public class CraftingListener implements Listener {

	private static ArrayList<HashMap<Integer, ItemStack>> recipes = new ArrayList<HashMap<Integer, ItemStack>>();
	private static ArrayList<ItemStack> portalGuns = PortalGunsAPI.getPortalGuns();

	public static void addRecipe(HashMap<Integer, ItemStack> recipe) {
		recipes.add(recipe);
		System.out.println("Added");
	}
	
	@EventHandler
	public void onCraftEvent(CraftItemEvent e) {
				
		loop: if (e.getRawSlot() == 9) {
			ItemStack output = e.getInventory().getItem(9);
			for (ItemStack portalGun : portalGuns) {
				if (output.isSimilar(portalGun)) {
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
						if (!inv.getItem(i + 1).isSimilar(recipe.get(indexes))) {
							recipes.remove(recipe);
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
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
		ItemMeta meta = pearl.getItemMeta();
		meta.setDisplayName("Test");
		pearl.setItemMeta(meta);
		
		e.getPlayer().getInventory().addItem(pearl);
	}
}
