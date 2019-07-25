package main.java.me.wsman217.portalguns;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import main.java.me.wsman217.portalguns.api.PortalGunsAPI;
import main.java.me.wsman217.portalguns.listeners.CraftingListener;

public class PortalGuns extends JavaPlugin {

	private static PortalGuns instance;

	@Override
	public void onEnable() {
		instance = this;
		/*
		 * for (Player p : Bukkit.getOnlinePlayers()) {
		 * p.getInventory().addItem(PortalGunsAPI.createPortalGun(Material.IRON_SHOVEL))
		 * ; }
		 */
		
		HashMap<Character, ItemStack> items = new HashMap<Character, ItemStack>();
		ItemStack pearl = new ItemStack(Material.ENDER_PEARL);
		ItemMeta meta = pearl.getItemMeta();
		meta.setDisplayName("Test");
		pearl.setItemMeta(meta);
		
		items.put(new Character('A'), null);
		items.put(new Character('B'), null);
		items.put(new Character('C'), null);
		items.put(new Character('D'), null);
		items.put(new Character('E'), pearl);
		items.put(new Character('F'), null);
		items.put(new Character('G'), null);
		items.put(new Character('H'), null);
		items.put(new Character('I'), pearl);

		PortalGunsAPI.createPGRecipe("ABC", "DEF", "GHI", items, Material.IRON_SHOVEL);

		initListeners();
	}

	private void initListeners() {
		getServer().getPluginManager().registerEvents(new CraftingListener(), this);
	}

	public static PortalGuns getInstance() {
		return instance;
	}
}
