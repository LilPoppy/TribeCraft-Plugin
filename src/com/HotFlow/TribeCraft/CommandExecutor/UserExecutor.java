package com.HotFlow.TribeCraft.CommandExecutor;

import com.HotFlow.TribeCraft.Player.Extension.DelayTask;
import com.HotFlow.TribeCraft.Player.TribePlayer;
import com.HotFlow.TribeCraft.TribeCraft;
import com.HotFlow.TribeCraft.World.Area;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author HotFlow
 */
public class UserExecutor implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            final Player player = (Player) sender;
            final TribePlayer tribePlayer = TribeCraft.getPlayerManager().getPlayer(player.getName());

            if (args.length > 0)
            {
                if (args[0].equalsIgnoreCase("survival"))
                {
                    for (DelayTask task : tribePlayer.getDelayTaskList())
                    {
                        if (task.getDescription() != null)
                        {
                            if (task.getDescription().equalsIgnoreCase("Teleport"))
                            {
                                player.sendMessage("请勿重复使用此命令!");
                                return false;
                            }
                        }
                    }

                    final Area area = new Area(
                            TribeCraft.getResidenceManager().getByName(TribeCraft.getPluginConfig().getSurvival().mainTown).getArea(TribeCraft.getPluginConfig().getSurvival().subArea).getHighLoc(),
                            TribeCraft.getResidenceManager().getByName(TribeCraft.getPluginConfig().getSurvival().mainTown).getArea(TribeCraft.getPluginConfig().getSurvival().subArea).getLowLoc());

                    if ((player.getWorld().equals(getServer().getWorld("world"))) && (Area.isAreaContainLocation(area, player.getLocation())))
                    {
                        getServer().getScheduler().runTask(TribeCraft.plugin, new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                while (true)
                                {
                                    Random random = new Random();
                                    int x = random.nextInt(TribeCraft.getPluginConfig().getSurvival().maxX) + 1;
                                    int z = random.nextInt(TribeCraft.getPluginConfig().getSurvival().maxZ) + 1;
                                    int h = player.getWorld().getMaxHeight();

                                    for (int y = h; y > 0; y--)
                                    {
                                        Block block = new Location(player.getWorld(), (double) x, (double) y, (double) z).getBlock();

                                        if (!(block.getType().equals(Material.AIR)) && !(block.getType().equals(Material.BEDROCK)))
                                        {
                                            if ((!block.getType().equals(Material.LAVA))
                                                    && (!block.getType().equals(Material.WATER))
                                                    && (!block.getType().equals(Material.STATIONARY_WATER))
                                                    && (!block.getType().equals(Material.STATIONARY_LAVA)))
                                            {
                                                if (y > TribeCraft.getPluginConfig().getSurvival().maxY)
                                                {
                                                    break;
                                                }

                                                final Location location = new Location(player.getWorld(), x, (y + 1), z);

                                                Block block1 = location.getBlock();

                                                if (!Area.isAreaContainLocation(area, location))
                                                {
                                                    if (TribeCraft.getResidenceManager().getByLoc(location) == null)
                                                    {
                                                        if (TribeCraft.getPermissionManager().playerHas(player, "Tribe.user.survival"))
                                                        {
                                                            player.sendMessage(ChatColor.GOLD + "正在传送...");
                                                            player.teleport(location);
                                                            return;
                                                        }
                                                        else
                                                        {
                                                            player.sendMessage(ChatColor.GOLD + "传送将在" + 10 + " 秒内开始.不要移动");

                                                            tribePlayer.addDelayTask(new DelayTask(10, "Teleport")
                                                            {
                                                                @Override
                                                                public void run()
                                                                {
                                                                    tribePlayer.getCraftPlayer().sendMessage(ChatColor.GOLD + "准备传送...");
                                                                    tribePlayer.getCraftPlayer().teleport(location);
                                                                }
                                                            });
                                                            return;
                                                        }
                                                    }
                                                    else
                                                    {
                                                        break;
                                                    }
                                                }
                                                else
                                                {
                                                    break;
                                                }
                                            }
                                            else
                                            {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                        });
                    }
                    else
                    {
                        player.sendMessage("你已经在主城外!");
                        return false;
                    }
                }
                else
                {
                    player.sendMessage("/tribe survival: 随机传送出主城范围。");
                    return false;
                }
            }
            else
            {
                player.sendMessage("/tribe survival: 随机传送出主城范围。");
                return false;
            }
        }
        else
        {
            TribeCraft.logger.info("该命令只能由玩家发出!");
            return false;
        }
        return false;
    }
}
