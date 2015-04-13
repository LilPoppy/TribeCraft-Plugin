package com.HotFlow.TribeCraft.Listener;

import com.HotFlow.TribeCraft.Event.Block.BlockDispenseBoneMealEvent;
import com.HotFlow.TribeCraft.Event.Player.PlayerStoreExperienceEvent;
import com.HotFlow.TribeCraft.Event.Player.PlayerStoreInventoryEvent;
import com.HotFlow.TribeCraft.Event.Player.PlayerTeleportingMoveEvent;
import com.HotFlow.TribeCraft.Event.Player.PlayerUseGateEvent;
import com.HotFlow.TribeCraft.Event.Player.PlayerUserBoneMealEvent;
import com.HotFlow.TribeCraft.Event.Plugin.PluginTimeChangeEvent;
import com.HotFlow.TribeCraft.Inventory.DeathInventory;
import com.HotFlow.TribeCraft.Inventory.Item.ArmorType;
import com.HotFlow.TribeCraft.Permissions.Permissions;
import com.HotFlow.TribeCraft.Player.Extension.DelayTask;
import com.HotFlow.TribeCraft.Player.TribePlayer;
import com.HotFlow.TribeCraft.PortalGate.PortalGate;
import com.HotFlow.TribeCraft.PortalGate.PortalGateType;
import com.HotFlow.TribeCraft.TribeCraft;
import com.HotFlow.TribeCraft.Utils.System.ISystem;
import java.util.HashMap;
import java.util.Random;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
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
        final TribePlayer player = TribeCraft.getPlayerManager().getPlayer(event.getEntity().getPlayer().getName());
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
            if (player.getVIPList().size() > 0)
            {
                player.getCraftPlayer().sendMessage("您的VIP等级为: " + player.getVIPList().get(0).getLevel());
                player.getCraftPlayer().sendMessage("物品掉落机率: " + (player.getVIPList().get(0).getItemDropChance() * 100) + "%");
                player.getCraftPlayer().sendMessage("装备掉落机率: " + (player.getVIPList().get(0).getArmorDropChance() * 100) + "%");
                player.getCraftPlayer().sendMessage("经验掉落百分比: " + (player.getVIPList().get(0).getExpDropPercentage() * 100) + "%");

                player.setDeathProtectedExp((int) (player.getCraftPlayer().getTotalExperience() * player.getVIPList().get(0).getExpDropPercentage()));
                event.setDroppedExp((int) (player.getCraftPlayer().getTotalExperience() * player.getVIPList().get(0).getExpDropPercentage()));

                for (ItemStack item : items.keySet())
                {
                    ItemStack newItem = item.clone();

                    for (int i = 0; i <= item.getAmount(); i++)
                    {
                        if (Math.random() <= player.getVIPList().get(0).getItemDropChance())
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

                        if (Math.random() > player.getVIPList().get(0).getArmorDropChance())
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
                player.getCraftPlayer().sendMessage("您的VIP等级为: 0");
                player.getCraftPlayer().sendMessage("物品掉落机率: " + (TribeCraft.config.getDouble("全局配置.死亡保护.普通用户.物品掉落机率") * 100) + "%");
                player.getCraftPlayer().sendMessage("装备掉落机率: " + (TribeCraft.config.getDouble("全局配置.死亡保护.普通用户.装备掉落机率") * 100) + "%");
                player.getCraftPlayer().sendMessage("经验掉落百分比: " + (TribeCraft.config.getDouble("全局配置.死亡保护.普通用户.经验掉落百分比") * 100) + "%");

                player.setDeathProtectedExp((int) (player.getCraftPlayer().getTotalExperience() * (TribeCraft.config.getDouble("全局配置.死亡保护.普通用户.经验掉落百分比"))));
                event.setDroppedExp((int) (player.getCraftPlayer().getTotalExperience() * (TribeCraft.config.getDouble("全局配置.死亡保护.普通用户.经验掉落百分比"))));

                for (ItemStack item : items.keySet())
                {
                    ItemStack newItem = item.clone();

                    for (int i = 0; i <= item.getAmount(); i++)
                    {
                        if (Math.random() <= TribeCraft.config.getDouble("全局配置.死亡保护.普通用户.物品掉落机率"))
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

                        if (Math.random() > TribeCraft.config.getDouble("全局配置.死亡保护.普通用户.装备掉落机率"))
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
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        final TribePlayer player = TribeCraft.getPlayerManager().getPlayer(event.getPlayer().getName());
        player.getCraftPlayer().getInventory().clear();

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
                    if (!player.getCraftPlayer().isDead() && player.getCraftPlayer().isOnline())
                    {
                        ISystem.experience.setTotalExperience(player.getCraftPlayer(), player.getDeathProtectedExp());
                        player.setDeathProtectedExp(0);
                    }
                }

            });
        }
        
        
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if (!TribeCraft.getPlayerManager().hasPlayer(event.getPlayer().getName()))
        {
            final TribePlayer player = new TribePlayer(event.getPlayer());
            TribeCraft.getPlayerManager().setPlayer(event.getPlayer().getName(), player);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if ((event.getPlayer().getItemInHand() != null) && (!event.getPlayer().getItemInHand().getType().equals(Material.AIR)))
        {
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
                        PlayerUserBoneMealEvent event1 = new PlayerUserBoneMealEvent(TribeCraft.getPlayerManager().getPlayer(event.getPlayer().getName()));
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
                    TribePlayer tribePlayer = TribeCraft.getPlayerManager().getPlayer(event.getPlayer().getName());
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
        for(int id : TribeCraft.config.getIntegerList(""))
        {
            if(event.getItem().getType().equals(Material.getMaterial(id)))
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

            if (TribeCraft.getPortalGateManager().getPortalGate(event.getFrom()) != null)
            {
                PortalGate gate = TribeCraft.getPortalGateManager().getPortalGate(event.getFrom());

                PlayerUseGateEvent event1 = new PlayerUseGateEvent(TribeCraft.getPlayerManager().getPlayer(event.getPlayer().getName()), gate);
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
                    TribeCraft.getPermissionManager().playerAdd(event.getPlayer(), "Tribe.user.survival");

                    TribeCraft.getPlayerManager().getPlayer(event.getPlayer().getName()).addDelayTask(new DelayTask(5)
                    {
                        @Override
                        public void run()
                        {
                            TribeCraft.getPermissionManager().playerRemove(event.getPlayer(), "Tribe.user.survival");
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
        TribePlayer player = TribeCraft.getPlayerManager().getPlayer(event.getPlayer().getName());

        for (DelayTask task : player.getDelayTaskList())
        {
            if (task.getDescription() != null)
            {
                if (task.getDescription().equalsIgnoreCase("Teleport"))
                {
                    if ((event.getFrom().getX() != event.getTo().getX()) || (event.getFrom().getZ() != event.getTo().getZ()))
                    {
                        PlayerTeleportingMoveEvent event1 = new PlayerTeleportingMoveEvent(player);
                        getServer().getPluginManager().callEvent(event1);

                        if (!event1.isCancelled())
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
    public void onPluginTimeChange(PluginTimeChangeEvent event)
    {

    }
    
    @EventHandler
    public void onEntityPortal(EntityPortalEvent event)
    {

    }
}
