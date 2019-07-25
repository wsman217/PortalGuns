package main.java.me.wsman217.portalguns.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import de.tr7zw.itemnbtapi.NBTItem;
import main.java.me.wsman217.portalguns.PortalGuns;
import main.java.me.wsman217.portalguns.listeners.CraftingListener;

public class PortalGunsAPI {

	private static ArrayList<ItemStack> portalGuns = new ArrayList<ItemStack>();

	public static ItemStack createPortalGun(Material mat) {

		NBTItem portalGunNBT = new NBTItem(new ItemStack(mat));
		portalGunNBT.setString("FirstPortal", "X:null, Y:null, Z:null");
		portalGunNBT.setString("SecondPortal", "X:null, Y:null, Z:null");

		ItemStack portalGunItem = portalGunNBT.getItem();
		ItemMeta portalGunMeta = portalGunItem.getItemMeta();

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Left click portal gun to fire portal entrance.");
		lore.add(ChatColor.GOLD + "Right click portal gun to fire portal exit.");
		lore.add(ChatColor.GOLD + "Shift + left click to remove portals linked to that portal gun.");

		portalGunMeta.setDisplayName(ChatColor.BLUE + "Portal Gun");
		portalGunMeta.setLore(lore);
		portalGunItem.setItemMeta(portalGunMeta);

		portalGuns.add(portalGunItem);
		return portalGunItem;
	}

	public static void createPGRecipe(String topRow, String midRow, String bottomRow,
			HashMap<Character, ItemStack> items, Material output) {
		ItemStack recipeOutput = new ItemStack(createPortalGun(output));
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(PortalGuns.getInstance(), "portalGun"), recipeOutput);

		recipe.shape(topRow, midRow, bottomRow);

		int index = 0;
		HashMap<Integer, ItemStack> recipeMap = new HashMap<Integer, ItemStack>();
		for (Character chars : items.keySet()) {
			if (items.get(chars) != null)
				recipe.setIngredient((char) chars, items.get(chars).getData());
			recipeMap.put(index, items.get(chars));
			index++;
		}

		CraftingListener.addRecipe(recipeMap);
		PortalGuns.getInstance().getServer().addRecipe(recipe);
	}

	public static ArrayList<ItemStack> getPortalGuns() {
		return portalGuns;
	}
}
