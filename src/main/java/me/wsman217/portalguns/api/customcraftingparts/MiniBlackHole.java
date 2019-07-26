package main.java.me.wsman217.portalguns.api.customcraftingparts;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import main.java.me.wsman217.portalguns.PortalGuns;
import main.java.me.wsman217.portalguns.listeners.CraftingListener;

public class MiniBlackHole {
	
	public static void initRecipe() {
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(PortalGuns.getInstance(), "black_hole"), createBlackHole());
		recipe.shape("AAA", "ABA", "AAA");
		recipe.setIngredient('A', EnderPearlDust.createEnderPearlDust().getType());
		recipe.setIngredient('B', Material.NETHER_STAR);
		
		PortalGuns.getInstance().getServer().addRecipe(recipe);
		
		HashMap<Integer, ItemStack> blackHoleRecipe = new HashMap<Integer, ItemStack>();
		ItemStack epearlDust = EnderPearlDust.createEnderPearlDust();
		blackHoleRecipe.put(0, epearlDust);
		blackHoleRecipe.put(1, epearlDust);
		blackHoleRecipe.put(2, epearlDust);
		blackHoleRecipe.put(3, epearlDust);
		blackHoleRecipe.put(4, new ItemStack(Material.NETHER_STAR));
		blackHoleRecipe.put(5, epearlDust);
		blackHoleRecipe.put(6, epearlDust);
		blackHoleRecipe.put(7, epearlDust);
		blackHoleRecipe.put(8, epearlDust);
		CraftingListener.addRecipe(blackHoleRecipe);
		CraftingListener.addOutput(createBlackHole());
	}

	public static ItemStack createBlackHole() {
		
		ItemStack blackHoleItem = new ItemStack(Material.LARGE_FERN);
		ItemMeta blackHoleMeta = blackHoleItem.getItemMeta();
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Crafting Material.");

		blackHoleMeta.setDisplayName(ChatColor.BLUE + "Mini Black Hole");
		blackHoleMeta.setLore(lore);
		blackHoleMeta.setCustomModelData(1);
		blackHoleMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		blackHoleMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		blackHoleMeta.setUnbreakable(true);
		blackHoleItem.setItemMeta(blackHoleMeta);
		
		return blackHoleItem;
	}
}
