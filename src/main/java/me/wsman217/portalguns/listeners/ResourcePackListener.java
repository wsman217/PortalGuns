package main.java.me.wsman217.portalguns.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;

import main.java.me.wsman217.portalguns.FileManager.Files;
import main.java.me.wsman217.portalguns.PortalGuns;

public class ResourcePackListener implements Listener {

	private boolean resourcePack = PortalGuns.getInstance().getFileManager().getFile(Files.CONFIG)
			.getBoolean("MultiWorld.EnableResourcePack");
	private String prefix = ChatColor.translateAlternateColorCodes('&', "&7[&3PortalGuns&7] ");
	private static String url = PortalGuns.getInstance().getFileManager().getFile(Files.CONFIG).getString("MultiWorld.ResourcePackURL");
	private ArrayList<Player> usingPlayers = new ArrayList<Player>();

	@EventHandler
	private void onPlayerJoin(PlayerJoinEvent event) {
		if (resourcePack)
			applyResourcePack(event.getPlayer(), 20);
	}
	
	@EventHandler
	private void onPlayerLeave(PlayerQuitEvent event) {
		usingPlayers.remove(event.getPlayer());
	}

	@EventHandler
	private void resourcePackEvent(PlayerResourcePackStatusEvent e) {
		Player player = e.getPlayer();
		if (PortalGuns.getInstance().getFileManager().getFile(Files.CONFIG).getBoolean("MultiWorld.NotifyMessage"))
			if (e.getStatus() == Status.DECLINED) {
				player.sendMessage(" ");
				player.sendMessage(prefix + ChatColor.RED + "**Resource pack declined**");
				player.sendMessage("   " + ChatColor.GOLD + "Please apply the requested Resource Pack.");
				player.sendMessage("   " + ChatColor.GOLD + "The resource pack is required for visual effects.");
			} else if (e.getStatus() == Status.ACCEPTED) {
				player.sendMessage(prefix + ChatColor.GREEN + "Successfully loaded resource pack.");
			}
	}

	public void applyResourcePack(Player player, int delay) {
		if (url != null) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(PortalGuns.getInstance(), () -> {
				try {
					player.setResourcePack(url);
				} catch (Exception e) {
					Bukkit.getConsoleSender()
							.sendMessage("ResourcePackURL is null or URL is too long! Plugin disabled.");
					Bukkit.getPluginManager().disablePlugin(PortalGuns.getInstance());
					return;
				}
				usingPlayers.add(player);
			}, delay);
		}
	}
}
