package com.HotFlow.TribeCraft.Listener;

import static org.bukkit.Bukkit.getServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;

import com.HotFlow.TribeCraft.TribeCraft;
import com.HotFlow.TribeCraft.Inventory.DeathInventory;
import com.HotFlow.TribeCraft.Inventory.Item.ArmorType;
import com.HotFlow.TribeCraft.Player.TribePlayer;
import com.HotFlow.TribeCraft.PortalGate.PortalGate;
import com.HotFlow.TribeCraft.PortalGate.PortalGateType;

/**
 * @author HotFlow
 */
public class Listeners implements Listener {
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		final TribePlayer player = TribeCraft.getPlayerManager().getPlayer(
				event.getEntity().getPlayer().getName());
		List<ItemStack> items = new ArrayList<ItemStack>();
		HashMap<ArmorType, ItemStack> equiements = new HashMap<ArmorType, ItemStack>();
		DeathInventory inventory = new DeathInventory();

		for (ItemStack item : event.getEntity().getPlayer().getInventory()
				.getContents()) {
			if (item != null && item.getType() != Material.AIR) {
				items.add(item);
			}
		}

		if (player.getCraftPlayer().getInventory().getHelmet() != null) {
			equiements.put(ArmorType.Helmet, player.getCraftPlayer()
					.getInventory().getHelmet());
		}
		if (player.getCraftPlayer().getInventory().getChestplate() != null) {
			equiements.put(ArmorType.Chestplate, player.getCraftPlayer()
					.getInventory().getChestplate());
		}
		if (player.getCraftPlayer().getInventory().getLeggings() != null) {
			equiements.put(ArmorType.Leggings, player.getCraftPlayer()
					.getInventory().getLeggings());
		}
		if (player.getCraftPlayer().getInventory().getBoots() != null) {
			equiements.put(ArmorType.Boots, player.getCraftPlayer()
					.getInventory().getBoots());
		}

		if (player.getVIPList().size() > 0) {
			player.getCraftPlayer().sendMessage(
					"您的VIP等级为: " + player.getVIPList().get(0).getLevel());
			player.getCraftPlayer()
					.sendMessage(
							"每件物品的掉落机率为: "
									+ (player.getVIPList().get(0)
											.getChanceOfDrops() * 100) + "%");

			List<ItemStack> protectedItems = new ArrayList<ItemStack>();

			for (ItemStack item : items) {
				if (Math.random() >= player.getVIPList().get(0)
						.getChanceOfDrops()) {
					inventory.items.add(item);
					protectedItems.add(item);
				}
			}

			for (ArmorType type : equiements.keySet()) {
				ItemStack armor = equiements.get(type);

				if (Math.random() >= player.getVIPList().get(0)
						.getChanceOfDrops()) {
					inventory.equiments.put(type, armor);
					protectedItems.add(armor);
				}
			}

			event.setKeepLevel(true);
			event.setDroppedExp(0);

			player.setDeathProtectedItems(inventory);

			for (ItemStack pitem : protectedItems) {
				for (int i = 0; i < items.size(); i++) {
					ItemStack item = items.get(i);
					if (item.equals(pitem)) {
						event.getDrops().remove(item);
						break;
					}
				}
			}

			for (ItemStack pitem : protectedItems) {
				for (ItemStack armor : equiements.values()) {
					if (armor.equals(pitem)) {
						event.getDrops().remove(armor);
						break;
					}
				}
			}
		} else {
			player.getCraftPlayer().sendMessage("您的VIP等级为: 0");
			player.getCraftPlayer().sendMessage("每件物品的掉落机率为: 50%");

			List<ItemStack> protectedItems = new ArrayList<ItemStack>();

			for (ItemStack item : items) {
				if (Math.random() >= 0.5) {
					inventory.items.add(item);
					protectedItems.add(item);
				}
			}

			for (ArmorType type : equiements.keySet()) {
				ItemStack armor = equiements.get(type);

				if (Math.random() >= 0.5) {
					inventory.equiments.put(type, armor);
					protectedItems.add(armor);
				}
			}

			player.setDeathProtectedItems(inventory);

			for (ItemStack pitem : protectedItems) {
				for (int i = 0; i < items.size(); i++) {
					ItemStack item = items.get(i);
					if (item.equals(pitem)) {
						event.getDrops().remove(item);
						break;
					}
				}
			}

			for (ItemStack pitem : protectedItems) {
				for (ItemStack armor : equiements.values()) {
					if (armor.equals(pitem)) {
						event.getDrops().remove(armor);
						break;
					}
				}
			}
		}

	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		final TribePlayer player = TribeCraft.getPlayerManager().getPlayer(
				event.getPlayer().getName());
		player.getCraftPlayer().getInventory().clear();

		for (ItemStack item : player.getDeathProtectedItems().items) {
			player.getCraftPlayer().getInventory().addItem(item);
		}

		for (ArmorType type : player.getDeathProtectedItems().equiments
				.keySet()) {
			ItemStack armor = player.getDeathProtectedItems().equiments
					.get(type);

			if (type == ArmorType.Helmet) {
				player.getCraftPlayer().getInventory().setHelmet(armor);
			} else if (type == ArmorType.Chestplate) {
				player.getCraftPlayer().getInventory().setChestplate(armor);
			} else if (type == ArmorType.Leggings) {
				player.getCraftPlayer().getInventory().setLeggings(armor);
			} else if (type == ArmorType.Boots) {
				player.getCraftPlayer().getInventory().setBoots(armor);
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		final TribePlayer player = new TribePlayer(event.getPlayer());
		TribeCraft.getPlayerManager().setPlayer(event.getPlayer().getName(),
				player);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if ((event.getPlayer().getItemInHand() != null)
				&& (!event.getPlayer().getItemInHand().getType()
						.equals(Material.AIR))) {
			if (event.getPlayer().getItemInHand().getType()
					.equals(Material.INK_SACK)) {
				if (event
						.getPlayer()
						.hasPermission(
								new com.HotFlow.TribeCraft.Permissions.Permissions().admin)) {
					return;
				}

				if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if (event.getClickedBlock().getType() == Material
							.getMaterial(6)) {
						event.setCancelled(true);
						event.getPlayer().sendMessage(
								"本服务器禁止使用骨粉对 " + Material.getMaterial(6).name()
										+ " 进行催长!");
					}
				}
			} else if (event.getPlayer().getItemInHand().getType()
					.equals(Material.WOOD_SPADE)) {
				if (event
						.getPlayer()
						.hasPermission(
								new com.HotFlow.TribeCraft.Permissions.Permissions().admin)) {
					TribePlayer tribePlayer = TribeCraft.getPlayerManager()
							.getPlayer(event.getPlayer().getName());
					Block block = event.getClickedBlock();

					if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
						tribePlayer.setGateSelectedLocation1(block
								.getLocation());
						tribePlayer.getCraftPlayer().sendMessage(
								"左键选择了["
										+ "世界="
										+ block.getLocation().getWorld()
												.getName() + "," + "x="
										+ block.getLocation().getX() + ","
										+ "y=" + block.getLocation().getY()
										+ "," + "z="
										+ block.getLocation().getZ() + "]");
						event.setCancelled(true);
					} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						tribePlayer.setGateSelectedLocation2(block
								.getLocation());
						tribePlayer.getCraftPlayer().sendMessage(
								"右键选择了["
										+ "世界="
										+ block.getLocation().getWorld()
												.getName() + "," + "x="
										+ block.getLocation().getX() + ","
										+ "y=" + block.getLocation().getY()
										+ "," + "z="
										+ block.getLocation().getZ() + "]");
						event.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerPortal(PlayerPortalEvent event) {
		if (event.getCause().equals(TeleportCause.NETHER_PORTAL)) {
			event.useTravelAgent(false);

			if (TribeCraft.getPortalGateManager()
					.getPortalGate(event.getFrom()) != null) {
				PortalGate gate = TribeCraft.getPortalGateManager()
						.getPortalGate(event.getFrom());

				if (!gate.getMessage().equals("")) {
					event.getPlayer().sendMessage(gate.getMessage());
				}

				if (gate.getType().equals(PortalGateType.Location)) {
					if (gate.getTo() != null) {
						event.getPlayer().teleport(gate.getTo());
						event.setCancelled(true);
					} else {
						event.getPlayer().sendMessage("该传送门尚未设置传送目的地!");
						event.setCancelled(true);
					}
				} else if (gate.getType().equals(PortalGateType.Random)) {
					Random random = new Random();
					int x = random.nextInt(3000) + 1;
					int y = 100;
					int z = random.nextInt(3000) + 1;

					Location location = new Location(event.getPlayer()
							.getWorld(), x, y, z);

					event.getPlayer().teleport(location);
				} else {
					TribeCraft.getPermissionManager().playerAdd(
							event.getPlayer(), "Tribe.user.survival");
					event.setCancelled(true);
				}

				for (String command : gate.getCommands()) {
					event.getPlayer().performCommand(command);
				}

				TribeCraft.getPermissionManager().playerRemove(
						event.getPlayer(), "Tribe.user.survival");
			}
		}
	}

	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		TribePlayer player = TribeCraft.getPlayerManager().getPlayer(
				event.getPlayer().getName());
		if ((event.getFrom().getX() != event.getTo().getX())
				|| (event.getFrom().getZ() != event.getTo().getZ())) {
			if (player.teleportingTask != 0) {
				getServer().getScheduler().cancelTask(player.teleportingTask);
				player.teleportingTask = 0;
				player.setTeleportingTime(10);
				player.getCraftPlayer().sendMessage(ChatColor.GOLD + "已取消传送!");
			}
		}
	}

	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		if (event.getCause().equals(TeleportCause.UNKNOWN)) {
			if (TribeCraft.getPortalGateManager().getPortalGate(
					event.getPlayer().getLocation()) != null) {
				PortalGate gate = TribeCraft.getPortalGateManager()
						.getPortalGate(event.getFrom());

				if (!gate.getMessage().equals("")) {
					event.getPlayer().sendMessage(gate.getMessage());
				}
				if (gate.getType().equals(PortalGateType.Location)) {
					if (gate.getTo() != null) {
						event.setTo(gate.getTo());
					}
				} else if (gate.getType().equals(PortalGateType.Random)) {
					Random random = new Random();
					int x = random.nextInt(3000) + 1;
					int y = 100;
					int z = random.nextInt(3000) + 1;

					Location location = new Location(event.getPlayer()
							.getWorld(), x, y, z);

					event.getPlayer().teleport(location);
				} else {
					event.setCancelled(true);
				}

				for (String command : gate.getCommands()) {
					event.getPlayer().performCommand(command);
				}
			}
		}
	}
}
