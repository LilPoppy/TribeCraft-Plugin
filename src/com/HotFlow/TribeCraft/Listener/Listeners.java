package com.HotFlow.TribeCraft.Listener;

import com.HotFlow.TribeCraft.Event.Player.PlayerStoreExperienceEvent;
import com.HotFlow.TribeCraft.Event.Player.PlayerStoreInventoryEvent;
import com.HotFlow.TribeCraft.Event.Player.PlayerTeleportingMoveEvent;
import com.HotFlow.TribeCraft.Event.Player.PlayerUseGateEvent;
import com.HotFlow.TribeCraft.Event.Player.PlayerUserBoneMealEvent;
import com.HotFlow.TribeCraft.Event.Plugin.PluginTimeChangeEvent;
import com.HotFlow.TribeCraft.Inventory.DeathInventory;
import com.HotFlow.TribeCraft.Inventory.Item.ArmorType;
import com.HotFlow.TribeCraft.Main;
import com.HotFlow.TribeCraft.Permissions.Permissions;
import com.HotFlow.TribeCraft.Player.Extension.DelayTask;
import com.HotFlow.TribeCraft.Player.TribePlayer;
import com.HotFlow.TribeCraft.PortalGate.PortalGate;
import com.HotFlow.TribeCraft.PortalGate.PortalGateType;
import com.HotFlow.TribeCraft.Utils.System.ISystem;
import java.util.HashMap;
import java.util.Random;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;

/**
 * @author HotFlow
 */
public class Listeners implements Listener
{
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        final TribePlayer player = Main.getPlayerManager().getPlayer(event.getEntity().getPlayer().getUniqueId());

        HashMap<ItemStack, ItemStack> items = new HashMap<ItemStack, ItemStack>();
        HashMap<ItemStack, HashMap<ArmorType, ItemStack>> equiements = new HashMap<ItemStack, HashMap<ArmorType, ItemStack>>();
        DeathInventory inventory = new DeathInventory();

        for (ItemStack item : event.getEntity().getPlayer().getInventory().getContents())
        {
            if (item != null && item.getType() != Material.AIR)
            {
                items.put(item, item);
            }
        }

        if (player.getCraftPlayer().getInventory().getHelmet() != null)
        {
            HashMap<ArmorType, ItemStack> map = new HashMap<ArmorType, ItemStack>();
            map.put(ArmorType.Helmet, player.getCraftPlayer().getInventory().getHelmet());
            equiements.put(player.getCraftPlayer().getInventory().getHelmet(), map);
        }
        if (player.getCraftPlayer().getInventory().getChestplate() != null)
        {
            HashMap<ArmorType, ItemStack> map = new HashMap<ArmorType, ItemStack>();
            map.put(ArmorType.Chestplate, player.getCraftPlayer().getInventory().getChestplate());
            equiements.put(player.getCraftPlayer().getInventory().getChestplate(), map);
        }
        if (player.getCraftPlayer().getInventory().getLeggings() != null)
        {
            HashMap<ArmorType, ItemStack> map = new HashMap<ArmorType, ItemStack>();
            map.put(ArmorType.Leggings, player.getCraftPlayer().getInventory().getLeggings());
            equiements.put(player.getCraftPlayer().getInventory().getLeggings(), map);
        }
        if (player.getCraftPlayer().getInventory().getBoots() != null)
        {
            HashMap<ArmorType, ItemStack> map = new HashMap<ArmorType, ItemStack>();
            map.put(ArmorType.Boots, player.getCraftPlayer().getInventory().getBoots());
            equiements.put(player.getCraftPlayer().getInventory().getBoots(), map);
        }

        if (!player.getCraftPlayer().hasPermission(new Permissions().deathSaveAll))
        {
            player.getCraftPlayer().sendMessage("您的VIP等级为: " + player.getVIPLevel());
            player.getCraftPlayer().sendMessage("物品掉落机率: " + (Main.getPluginConfig().getVIPInfo().getVIP(player.getVIPLevel()).getItemDropChance() * 100) + "%");
            player.getCraftPlayer().sendMessage("装备掉落机率: " + (Main.getPluginConfig().getVIPInfo().getVIP(player.getVIPLevel()).getArmorDropChance() * 100) + "%");
            player.getCraftPlayer().sendMessage("经验掉落百分比: " + (Main.getPluginConfig().getVIPInfo().getVIP(player.getVIPLevel()).getExpDropPercentage() * 100) + "%");

            player.setDeathProtectedExp((int) (ISystem.experience.getTotalExperience(player.getCraftPlayer()) * Main.getPluginConfig().getVIPInfo().getVIP(player.getVIPLevel()).getExpDropPercentage()));
            event.setDroppedExp((int) (ISystem.experience.getTotalExperience(player.getCraftPlayer()) * Main.getPluginConfig().getVIPInfo().getVIP(player.getVIPLevel()).getExpDropPercentage()));

            for (ItemStack item : items.keySet())
            {
                ItemStack newItem = item.clone();

                for (int i = 0; i <= item.getAmount(); i++)
                {
                    if (Math.random() <= Main.getPluginConfig().getVIPInfo().getVIP(player.getVIPLevel()).getItemDropChance())
                    {
                        newItem.setAmount(newItem.getAmount() - 1);
                    }
                }

                items.put(item, newItem);
                inventory.items.add(newItem);
            }

            for (HashMap<ArmorType, ItemStack> map : equiements.values())
            {
                for (ArmorType type : map.keySet())
                {
                    ItemStack armor = map.get(type);

                    if (Math.random() > Main.getPluginConfig().getVIPInfo().getVIP(player.getVIPLevel()).getArmorDropChance())
                    {
                        equiements.put(armor, null);
                        inventory.equiments.put(type, armor);
                    }
                }
            }

            for (ItemStack armor : equiements.keySet())
            {
                if (equiements.get(armor) == null)
                {
                    event.getDrops().remove(armor);
                }
            }

            for (ItemStack item : items.keySet())
            {
                event.getDrops().remove(item);
                ItemStack newItem = item.clone();
                newItem.setAmount(item.getAmount() - items.get(item).getAmount());
                event.getDrops().add(newItem);
            }
        }
        else
        {
            for (ItemStack item : items.keySet())
            {
                inventory.items.add(item);
            }

            for (HashMap<ArmorType, ItemStack> map : equiements.values())
            {
                for (ArmorType type : map.keySet())
                {
                    ItemStack armor = map.get(type);
                    inventory.equiments.put(type, armor);
                }
            }

            event.getDrops().clear();
            event.setKeepLevel(true);
        }

        player.setDeathProtectedItems(inventory);
    }

    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent event)
    {
        final TribePlayer player = Main.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());

        event.getPlayer().getInventory().clear();

        PlayerStoreInventoryEvent event1 = new PlayerStoreInventoryEvent(player);
        getServer().getPluginManager().callEvent(event1);

        if (!event1.isCancelled())
        {
            for (ItemStack item : player.getDeathProtectedItems().items)
            {
                event.getPlayer().getInventory().addItem(item);
            }

            for (ArmorType type : player.getDeathProtectedItems().equiments.keySet())
            {
                ItemStack armor = player.getDeathProtectedItems().equiments.get(type);

                if (type == ArmorType.Helmet)
                {
                    event.getPlayer().getInventory().setHelmet(armor);
                }
                else if (type == ArmorType.Chestplate)
                {
                    event.getPlayer().getInventory().setChestplate(armor);
                }
                else if (type == ArmorType.Leggings)
                {
                    event.getPlayer().getInventory().setLeggings(armor);
                }
                else if (type == ArmorType.Boots)
                {
                    event.getPlayer().getInventory().setBoots(armor);
                }
            }
        }

        player.setDeathProtectedItems(null);

        PlayerStoreExperienceEvent event2 = new PlayerStoreExperienceEvent(player);
        getServer().getPluginManager().callEvent(event2);

        if (!event2.isCancelled())
        {
            player.addDelayTask(new DelayTask(1, "Experience")
            {
                @Override
                public void run()
                {
                    if (!event.getPlayer().isDead() && event.getPlayer().isOnline())
                    {
                        ISystem.experience.setTotalExperience(event.getPlayer(), player.getDeathProtectedExp());
                        player.setDeathProtectedExp(0);
                    }
                }

            });
        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        int count = 0;
        for (Player player : getServer().getOnlinePlayers())
        {
            if (event.getPlayer().getName().equalsIgnoreCase(player.getName()))
            {
                count++;

                if (count > 1)
                {
                    player.kickPlayer("拥有相同名称在游戏中！");
                    return;
                }
            }
        }

        if (!Main.getPlayerManager().hasPlayer(event.getPlayer().getUniqueId()))
        {
            final TribePlayer player = new TribePlayer(event.getPlayer().getUniqueId());
            Main.getPlayerManager().setPlayer(event.getPlayer().getUniqueId(), player);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if ((event.getPlayer().getItemInHand() != null) && (!event.getPlayer().getItemInHand().getType().equals(Material.AIR)))
        {
            if (Main.getPluginConfig().getServerConfig().isClearInfinityItems())
            {
                if (event.getPlayer().getItemInHand().getAmount() <= -1)
                {
                    event.getPlayer().setItemInHand(null);
                    event.setCancelled(true);
                }
            }

            if (event.getPlayer().getItemInHand().getType().equals(Material.INK_SACK))
            {
                if (event.getPlayer().hasPermission(new com.HotFlow.TribeCraft.Permissions.Permissions().admin))
                {
                    return;
                }

                if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
                {
                    if (event.getClickedBlock().getType() == Material.getMaterial(6) || event.getClickedBlock().getType() == Material.RED_MUSHROOM || event.getClickedBlock().getType() == Material.BROWN_MUSHROOM)
                    {
                        PlayerUserBoneMealEvent event1 = new PlayerUserBoneMealEvent(Main.getPlayerManager().getPlayer(event.getPlayer().getUniqueId()));
                        getServer().getPluginManager().callEvent(event1);

                        if (event1.isCancelled())
                        {
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ChatColor.RED + "本服务器禁止使用骨粉对 " + ChatColor.WHITE + event.getClickedBlock().getType().name() + ChatColor.RED + " 进行催长!");
                        }
                    }
                }
            }
            else if (event.getPlayer().getItemInHand().getType().equals(Material.WOOD_SPADE))
            {
                if (event.getPlayer().hasPermission(new com.HotFlow.TribeCraft.Permissions.Permissions().admin))
                {
                    TribePlayer tribePlayer = Main.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());
                    Block block = event.getClickedBlock();

                    if (event.getAction() == Action.LEFT_CLICK_BLOCK)
                    {
                        tribePlayer.setGateSelectedLocation1(block.getLocation());
                        tribePlayer.getCraftPlayer().sendMessage("左键选择了[" + "世界=" + block.getLocation().getWorld().getName() + "," + "x=" + block.getLocation().getX() + "," + "y=" + block.getLocation().getY() + "," + "z=" + block.getLocation().getZ() + "]");
                        event.setCancelled(true);
                    }
                    else if (event.getAction() == Action.RIGHT_CLICK_BLOCK)
                    {
                        tribePlayer.setGateSelectedLocation2(block.getLocation());
                        tribePlayer.getCraftPlayer().sendMessage("右键选择了[" + "世界=" + block.getLocation().getWorld().getName() + "," + "x=" + block.getLocation().getX() + "," + "y=" + block.getLocation().getY() + "," + "z=" + block.getLocation().getZ() + "]");
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockDispense(BlockDispenseEvent event)
    {
        for (int id : Main.config.getIntegerList(""))
        {
            if (event.getItem().getType().equals(Material.getMaterial(id)) || event.getItem().getAmount() <= -1)
            {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerPortal(final PlayerPortalEvent event)
    {
        if (event.getCause().equals(TeleportCause.NETHER_PORTAL))
        {
            event.useTravelAgent(false);

            if (Main.getPortalGateManager().getPortalGate(event.getFrom()) != null)
            {
                PortalGate gate = Main.getPortalGateManager().getPortalGate(event.getFrom());

                PlayerUseGateEvent event1 = new PlayerUseGateEvent(Main.getPlayerManager().getPlayer(event.getPlayer().getUniqueId()), gate);
                getServer().getPluginManager().callEvent(event1);

                if (event1.isCancelled())
                {
                    return;
                }

                if (!gate.getMessage().equals(""))
                {
                    event.getPlayer().sendMessage(gate.getMessage());
                }

                if (gate.getType().equals(PortalGateType.Location))
                {
                    if (gate.getTo() != null)
                    {
                        event.getPlayer().teleport(gate.getTo());
                        event.setCancelled(true);
                    }
                    else
                    {
                        event.getPlayer().sendMessage("该传送门尚未设置传送目的地!");
                        event.setCancelled(true);
                    }
                }
                else if (gate.getType().equals(PortalGateType.Random))
                {
                    Random random = new Random();
                    int x = random.nextInt(3000) + 1;
                    int y = 100;
                    int z = random.nextInt(3000) + 1;

                    Location location = new Location(event.getPlayer().getWorld(), x, y, z);

                    event.getPlayer().teleport(location);
                }
                else
                {
                    Main.getPermissionManager().playerAdd(event.getPlayer(), "Tribe.user.survival");

                    Main.getPlayerManager().getPlayer(event.getPlayer().getUniqueId()).addDelayTask(new DelayTask(5)
                    {
                        @Override
                        public void run()
                        {
                            Main.getPermissionManager().playerRemove(event.getPlayer(), "Tribe.user.survival");
                        }

                    });

                    event.setCancelled(true);
                }

                for (String command : gate.getCommands())
                {
                    event.getPlayer().performCommand(command);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        TribePlayer player = Main.getPlayerManager().getPlayer(event.getPlayer().getUniqueId());

        if ((event.getFrom().getX() != event.getTo().getX()) || (event.getFrom().getZ() != event.getTo().getZ()))
        {
            for (DelayTask task : player.getDelayTaskList())
            {
                if (task.getDescription() != null)
                {
                    if (task.getDescription().equalsIgnoreCase("Teleport"))
                    {
                        PlayerTeleportingMoveEvent event1 = new PlayerTeleportingMoveEvent(player);
                        getServer().getPluginManager().callEvent(event1);

                        if (event1.isCancelled())
                        {
                            return;
                        }

                        player.removeDelayTask(task);
                        player.getCraftPlayer().sendMessage(ChatColor.GOLD + "已取消传送!");
                        return;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPluginTimeChange(final PluginTimeChangeEvent event)
    {
        if (Main.getPluginConfig().getServerConfig().getPermissionDetector().getOPDetector().enable)
        {
            Player:
            for (int i = 0; i < getServer().getOperators().size(); i++)
            {
                OfflinePlayer player = (OfflinePlayer) getServer().getOperators().toArray()[i];
                
                for (String name : Main.getPluginConfig().getServerConfig().getPermissionDetector().getOPDetector().whiteList)
                {
                    if (player.getName().equals(name))
                    {
                        continue Player;
                    }
                }

                getServer().broadcastMessage(ChatColor.DARK_RED + "【危险通知】 " + ChatColor.RED + "玩家 " + ChatColor.WHITE + player.getName() + ChatColor.RED + " 非法获得OP已封禁!");

                player.setOp(false);
                player.setBanned(true);

                if (player.isOnline())
                {
                    getServer().getPlayer(player.getName()).kickPlayer("非法获得OP已封禁!");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event)
    {
        if (Main.getPluginConfig().getServerConfig().getPermissionDetector().getCreativeDetector().enable)
        {
            if (event.getNewGameMode().equals(GameMode.CREATIVE))
            {
                if (Main.getPluginConfig().getServerConfig().getPermissionDetector().getOPDetector().enable)
                {
                    for (String name : Main.getPluginConfig().getServerConfig().getPermissionDetector().getOPDetector().whiteList)
                    {
                        if (event.getPlayer().getName().equals(name))
                        {
                            return;
                        }
                    }
                }

                for (String name : Main.getPluginConfig().getServerConfig().getPermissionDetector().getCreativeDetector().whiteList)
                {
                    if (event.getPlayer().getName().equals(name))
                    {
                        return;
                    }
                }

                getServer().broadcastMessage(ChatColor.DARK_RED + "【危险通知】 " + ChatColor.RED + "玩家 " + ChatColor.WHITE + event.getPlayer().getName() + ChatColor.RED + " 非法获得创造已解除!");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event)
    {
        if (Main.getPluginConfig().getServerConfig().getPermissionDetector().getOPDetector().enable)
        {
            if (event.getPlayer().isOp())
            {
                for (String name : Main.getPluginConfig().getServerConfig().getPermissionDetector().getOPDetector().whiteList)
                {
                    if (event.getPlayer().getName().equals(name))
                    {
                        return;
                    }
                }

                getServer().broadcastMessage(ChatColor.RED + "【危险通知】 " + ChatColor.DARK_RED + "玩家 " + ChatColor.WHITE + event.getPlayer().getName() + ChatColor.DARK_RED + " 非法获得OP已封禁!");
                event.getPlayer().setOp(false);
                event.getPlayer().setBanned(true);
                event.getPlayer().kickPlayer("非法获得OP已封禁!");
            }
        }
    }

    @EventHandler
    public void onEntityPortal(EntityPortalEvent event)
    {
        for (String name : Main.getPluginConfig().getServerConfig().getNetherPortalEntityBans().entityNames)
        {
            if (event.getEntityType().getName().equalsIgnoreCase(name))
            {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event)
    {
        if (event.getWhoClicked() instanceof Player)
        {
            Player player = (Player) event.getWhoClicked();

            if (Main.getPluginConfig().getServerConfig().isClearInfinityItems())
            {
                for (int i = 0; i < event.getInventory().getSize(); i++)
                {
                    if (event.getInventory().getItem(i) != null)
                    {
                        if (event.getInventory().getItem(i).getAmount() <= -1)
                        {
                            event.getInventory().setItem(i, null);
                        }
                    }
                }

                for (int i = 0; i < player.getInventory().getSize(); i++)
                {
                    if (player.getInventory().getItem(i) != null)
                    {
                        if (player.getInventory().getItem(i).getAmount() <= -1)
                        {
                            event.getInventory().setItem(i, null);
                        }
                    }
                }
            }
        }
    }
}
