package main.java.me.wsman217.portalguns;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import main.java.me.wsman217.portalguns.api.PortalGunsAPI;
import main.java.me.wsman217.portalguns.api.customcraftingparts.EnderPearlDust;
import main.java.me.wsman217.portalguns.api.customcraftingparts.MiniBlackHole;
import main.java.me.wsman217.portalguns.listeners.CraftingListener;
import main.java.me.wsman217.portalguns.listeners.ResourcePackListener;

public class PortalGuns extends JavaPlugin {

	private static PortalGuns instance;
	private FileManager fileManager;

	@Override
	public void onEnable() {
		instance = this;
		fileManager = FileManager.getInstance().logInfo(true).setup(this);
		/*
		 * for (Player p : Bukkit.getOnlinePlayers()) {
		 * p.getInventory().addItem(PortalGunsAPI.createPortalGun(Material.IRON_SHOVEL))
		 * ; }
		 */
		
		HashMap<Character, ItemStack> items = new HashMap<Character, ItemStack>();
		
		ItemStack obby = new ItemStack(Material.OBSIDIAN);
		ItemStack iron = new ItemStack(Material.IRON_INGOT);
		ItemStack diamond = new ItemStack(Material.DIAMOND);
		ItemStack blackHole = MiniBlackHole.createBlackHole();
		items.put(new Character('A'), obby);
		items.put(new Character('B'), iron);
		items.put(new Character('C'), iron);
		
		items.put(new Character('D'), diamond);
		items.put(new Character('E'), blackHole);
		items.put(new Character('F'), iron);
		
		items.put(new Character('G'), iron);
		items.put(new Character('H'), obby);
		items.put(new Character('I'), diamond);

		PortalGunsAPI.createPGRecipe("ABC", "DEF", "GHI", items, Material.IRON_SHOVEL, 1);

		initRecipes();
		initListeners();
	}

	private void initListeners() {
		getServer().getPluginManager().registerEvents(new CraftingListener(), this);
		getServer().getPluginManager().registerEvents(new ResourcePackListener(), this);

	}

	private void initRecipes() {
		MiniBlackHole.initRecipe();
		EnderPearlDust.initRecipe();
	}
	
	public FileManager getFileManager() {
		if (fileManager == null)
			throw new NullPointerException("File manger for plugin PortalGuns was null");
		return fileManager;
	}
	
	public static PortalGuns getInstance() {
		return instance;
	}
}
