package com.HotFlow.TribeCraft.CommandExecutor;

import com.HotFlow.TribeCraft.Main;
import com.HotFlow.TribeCraft.Player.TribePlayer;
import com.HotFlow.TribeCraft.Timer.Task.DelayTask;
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
            final TribePlayer tribePlayer = Main.getPlayerManager().getPlayer(player.getUniqueId());

            if (args.length > 0)
            {
                if (args[0].equalsIgnoreCase("survival"))
                {
                    for (DelayTask task : Main.getDelayTaskManager().getTasks())
                    {
                        if (task.getDescription() != null)
                        {
                            if (task.getDescription().equalsIgnoreCase(player.getName() + ":Teleport"))
                            {
                                player.sendMessage("请勿重复使用此命令!");
                                return false;
                            }
                        }
                    }

                    final Area area = new Area(
                            Main.getResidenceManager().getByName(Main.getPluginConfig().getCommandsInfo().getSurvival().mainTown).getArea(Main.getPluginConfig().getCommandsInfo().getSurvival().subArea).getHighLoc(),
                            Main.getResidenceManager().getByName(Main.getPluginConfig().getCommandsInfo().getSurvival().mainTown).getArea(Main.getPluginConfig().getCommandsInfo().getSurvival().subArea).getLowLoc());

                    if ((player.getWorld().equals(getServer().getWorld("world"))) && (Area.isAreaContainLocation(area, player.getLocation())))
                    {
                        getServer().getScheduler().runTask(Main.plugin, new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                while (true)
                                {
                                    Random random = new Random();
                                    int x = random.nextInt(Main.getPluginConfig().getCommandsInfo().getSurvival().maxX) + 1;
                                    int z = random.nextInt(Main.getPluginConfig().getCommandsInfo().getSurvival().maxZ) + 1;
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
                                                if (y > Main.getPluginConfig().getCommandsInfo().getSurvival().maxY)
                                                {
                                                    break;
                                                }

                                                final Location location = new Location(player.getWorld(), x, (y + 1), z);

                                                if (!Area.isAreaContainLocation(area, location))
                                                {
                                                    if(!Main.getPluginConfig().getCommandsInfo().getSurvival().canInResidence)
                                                    {
                                                        if (Main.getResidenceManager().getByLoc(location) != null)
                                                        {
                                                            break;
                                                        }
                                                    }

                                                    if (Main.getPermissionManager().playerHas(player, "Tribe.user.survival"))
                                                    {
                                                        player.sendMessage(ChatColor.GOLD + "正在传送...");
                                                        player.teleport(location);
                                                        return;
                                                    }
                                                    else
                                                    {
                                                        player.sendMessage(ChatColor.GOLD + "传送将在" + 10 + " 秒内开始.不要移动");

                                                        Main.getDelayTaskManager().getTasks().add(new DelayTask(10, player.getName() + ":Teleport")
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
            Main.logger.info("该命令只能由玩家发出!");
            return false;
        }
        return false;
    }
}
