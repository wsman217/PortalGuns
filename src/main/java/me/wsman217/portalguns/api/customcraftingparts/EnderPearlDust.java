package main.java.me.wsman217.portalguns.api.customcraftingparts;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import main.java.me.wsman217.portalguns.PortalGuns;

public class EnderPearlDust {
	
	public static void initRecipe() {
		ItemStack output = createEnderPearlDust();
		output.setAmount(8);
		FurnaceRecipe recipe = new FurnaceRecipe(new NamespacedKey(PortalGuns.getInstance(), "ender_pearl_dust"), output, Material.ENDER_PEARL, 2L, 20);
		
		PortalGuns.getInstance().getServer().addRecipe(recipe);
	}

	public static ItemStack createEnderPearlDust() {

		ItemStack blackHoleItem = new ItemStack(Material.LARGE_FERN);
		ItemMeta blackHoleMeta = blackHoleItem.getItemMeta();

		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Crafting Material.");

		blackHoleMeta.setDisplayName(ChatColor.BLUE + "Ender Pearl Dust");
		blackHoleMeta.setLore(lore);
		blackHoleMeta.setCustomModelData(2);
		blackHoleMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		blackHoleMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		blackHoleMeta.setUnbreakable(true);
		blackHoleItem.setItemMeta(blackHoleMeta);

		return blackHoleItem;
	}
}
