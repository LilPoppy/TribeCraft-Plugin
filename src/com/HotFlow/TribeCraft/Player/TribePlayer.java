package com.HotFlow.TribeCraft.Player;

import com.HotFlow.TribeCraft.Inventory.DeathInventory;
import com.HotFlow.TribeCraft.Main;
import com.HotFlow.TribeCraft.NBT.CompoundTag;
import com.HotFlow.TribeCraft.NBT.DoubleTag;
import com.HotFlow.TribeCraft.NBT.ListTag;
import com.HotFlow.TribeCraft.NBT.NBTInputStream;
import com.HotFlow.TribeCraft.NBT.NBTOutputStream;
import com.HotFlow.TribeCraft.NBT.StringTag;
import com.HotFlow.TribeCraft.NBT.Tag;
import com.HotFlow.TribeCraft.Player.Extension.DelayTask;
import com.HotFlow.TribeCraft.Player.VIP.VIP;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author HotFlow
 */
public class TribePlayer
{

    private final UUID uuid;
    private Location gateLoc1;
    private Location gateLoc2;
    private final List<VIP> vips = new ArrayList<VIP>();
    private DeathInventory deathInventory = new DeathInventory();
    private int deathExp;
    private final List<DelayTask> delayTaskList = new ArrayList<DelayTask>();
    private int dollar;

    public TribePlayer(UUID uuid)
    {
        this.uuid = uuid;
    }

    /**
     * 获取 org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer
     *
     * @return
     */
    public Player getCraftPlayer()
    {
        for(Player player : getServer().getOnlinePlayers())
        {
            if(player.getUniqueId().equals(uuid))
            {
                return player;
            }
        }
        
        return null;
    }

    /**
     * 获取传送门选择点1
     *
     * @return
     */
    public Location getGateSelectedLocation1()
    {
        return this.gateLoc1;
    }

    /**
     * 设置传送门选择点1
     *
     * @param loc
     */
    public void setGateSelectedLocation1(Location loc)
    {
        this.gateLoc1 = loc;
    }

    /**
     * 获取传送门选择点2
     *
     * @return
     */
    public Location getGateSelectedLocation2()
    {
        return this.gateLoc2;
    }

    /**
     * 设置传送门选择点2
     *
     * @param loc
     */
    public void setGateSelectedLocation2(Location loc)
    {
        this.gateLoc2 = loc;
    }

    /**
     * 获取VIP列表
     *
     * @return
     */
    public List<VIP> getVIPList()
    {
        Collections.sort(this.vips, new Comparator<VIP>()
        {
            @Override
            public int compare(VIP vip1, VIP vip2)
            {
                return vip2.getLevel() - vip1.getLevel();
            }

        });

        return this.vips;
    }

    /**
     * 设置死亡时保护物品
     *
     * @param inventory
     */
    public void setDeathProtectedItems(DeathInventory inventory)
    {
        this.deathInventory = inventory;
    }

    /**
     * 获取死亡时保护物品
     *
     * @return
     */
    public DeathInventory getDeathProtectedItems()
    {
        return this.deathInventory;
    }

    /**
     * 设置死亡时保护经验
     *
     * @param exp
     */
    public void setDeathProtectedExp(int exp)
    {
        this.deathExp = exp;
    }

    /**
     * 获取死亡时保护经验
     *
     * @return
     */
    public int getDeathProtectedExp()
    {
        return this.deathExp;
    }

    /**
     * 获取预约执行器列表
     *
     * @return
     */
    public List<DelayTask> getDelayTaskList()
    {
        return this.delayTaskList;
    }

    /**
     * 添加预约执行器
     *
     * @param task
     */
    public void addDelayTask(DelayTask task)
    {
        this.delayTaskList.add(task);
    }

    /**
     * 移除预约执行器
     *
     * @param task
     */
    public void removeDelayTask(DelayTask task)
    {
        this.delayTaskList.remove(task);
    }

    /**
     * 获取玩家击退抗性
     *
     * @return
     */
    public double getKnockbackResistant()
    {
        try
        {
            File playerFile = new File(System.getProperty("user.dir"),
                    "world\\playerdata\\"
                    + this.getCraftPlayer().getUniqueId().toString()
                    + ".dat");

            this.getCraftPlayer().saveData();

            NBTInputStream playerInputStream = new NBTInputStream(new FileInputStream(playerFile));
            CompoundTag playerTag = (CompoundTag) playerInputStream.readTag();
            playerInputStream.close();

            Map<String, Tag> playerData = new LinkedHashMap<String, Tag>(((CompoundTag) playerTag).getValue());

            List<Tag> attributes = ((ListTag) playerData.get("Attributes")).getValue();

            for (Tag attribute : attributes)
            {
                if (((StringTag) ((CompoundTag) attribute).getValue().get("Name")).getValue().equalsIgnoreCase("generic.knockbackResistance"))
                {
                    Double base = ((DoubleTag) ((CompoundTag) attribute).getValue().get("Base")).getValue();
                    return base;
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Player.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Player.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return 0.0;
    }

    /**
     * 设置玩家击退抗性
     *
     * @param value
     */
    public void setKnockbackResistant(Double value)
    {
        try
        {
            File playerFile = new File(System.getProperty("user.dir"),
                    "world\\playerdata\\"
                    + this.getCraftPlayer().getUniqueId().toString()
                    + ".dat");

            this.getCraftPlayer().saveData();

            NBTInputStream playerInputStream = new NBTInputStream(new FileInputStream(playerFile));
            CompoundTag playerTag = (CompoundTag) playerInputStream.readTag();
            playerInputStream.close();

            Map<String, Tag> playerData = new LinkedHashMap<String, Tag>(((CompoundTag) playerTag).getValue());

            List<Tag> attributes = ((ListTag) playerData.get("Attributes")).getValue();

            for (int i = 0; i < attributes.size(); i++)
            {
                Tag attribute = attributes.get(i);

                if (((StringTag) ((CompoundTag) attribute).getValue().get("Name")).getValue().equalsIgnoreCase("generic.knockbackResistance"))
                {
                    ((CompoundTag) ((ListTag) playerData.get("Attributes")).getValue().get(i)).getValue().put("Base", new DoubleTag("Base", value));

                    CompoundTag newTag = new CompoundTag("", playerData);

                    NBTOutputStream playerOutputStream = new NBTOutputStream(new FileOutputStream(playerFile));
                    playerOutputStream.writeTag(newTag);
                    playerOutputStream.close();
                    getServer().getPlayer(uuid).loadData();
                    return;
                }
            }

        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 获取玩家点卷数量
     *
     * @return 玩家点卷数量
     */
    public int getDollar()
    {
        return dollar;
    }

    /**
     * 增加玩家点卷
     *
     * @param CountOfAdd
     * @return 增加后的数量
     */
    public int addDollar(int CountOfAdd)
    {
        dollar = dollar + CountOfAdd;
        return dollar;
    }

    /**
     * 减少玩家点卷数量
     *
     * @param CountOfTake
     * @return 减少后的数量
     */
    public int TakeDollar(int CountOfTake)
    {
        dollar = dollar + CountOfTake;
        return dollar;
    }

    /**
     * 设置玩家点卷数量
     *
     * @param dollar
     */
    public void setDollar(int dollar)
    {
        this.dollar = dollar;
    }
}
