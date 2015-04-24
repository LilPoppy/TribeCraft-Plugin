package com.HotFlow.TribeCraft.Configuration;

import com.HotFlow.TribeCraft.CommandExecutor.AdminExecutor;
import com.HotFlow.TribeCraft.Main;
import com.HotFlow.TribeCraft.Player.VIP.VIP;
import com.HotFlow.TribeCraft.Utils.System.ISystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

/**
 * @author HotFlow
 */
public class PluginConfig
{

    private ServerConfig serverConfig;
    private CommandsInfo commandsInfo;
    private VIPInfo vipInfo;

    public PluginConfig()
    {
        this.serverConfig = new ServerConfig();
        this.commandsInfo = new CommandsInfo();
        this.vipInfo = new VIPInfo();
    }

    /**
     * 重载服务器配置
     */
    public void reload()
    {
        this.serverConfig = new ServerConfig();
        this.commandsInfo = new CommandsInfo();
        this.vipInfo = new VIPInfo();
    }

    /**
     * 保存服务器配置
     */
    public void save()
    {
        try
        {
            Main.config.save(Main.configFile);
        }
        catch (IOException ex)
        {
            Logger.getLogger(AdminExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 获取服务器设置
     *
     * @return
     */
    public ServerConfig getServerConfig()
    {
        return this.serverConfig;
    }

    /**
     * 获取指令信息
     *
     * @return
     */
    public CommandsInfo getCommandsInfo()
    {
        return this.commandsInfo;
    }

    /**
     * 获取VIP信息
     *
     * @return
     */
    public VIPInfo getVIPInfo()
    {
        return this.vipInfo;
    }

    public class ServerConfig
    {
        private final Boolean deathProtect;
        private final PermissionDetectorConfiguration permissionDetector;
        private final DispenserItemBansConfiguration dispenserItemBans;
        private final NetherPortalEntityBansConfiguration netherPortalEntityBans;
        private final Boolean clearRedstoneClock;
        private final HeightWaterRemovesConfiguration heightWaterRemoves;
        private final HeightLavaRemovesConfiguration heightLavaRemoves;
        private final Boolean clearInfinityItems;
        private final ChunkRemovesConfiguration chunkRemoves;
        private final ChunkEntityRemovesConfiguration chunkEntityRemoves;
        private final ClearDropsConfiguration clearDrops;

        public ServerConfig()
        {
            this.deathProtect = Main.config.getBoolean("全局配置.服务器设置.死亡保护");

            List<String> opList = new ArrayList<String>();

            for (String name : Main.config.getStringList("全局配置.服务器设置.权限检测.OP检测.白名单"))
            {
                opList.add(name);
            }

            List<String> creativeList = new ArrayList<String>();

            for (String name : Main.config.getStringList("全局配置.服务器设置.权限检测.创造检测.白名单"))
            {
                creativeList.add(name);
            }

            this.permissionDetector = new PermissionDetectorConfiguration(
                    Main.config.getBoolean("全局配置.服务器设置.权限检测.OP检测.开启"), opList,
                    Main.config.getBoolean("全局配置.服务器设置.权限检测.创造检测.开启"), creativeList);

            List<Integer> itemIDs = new ArrayList<Integer>();

            for (Integer itemID : Main.config.getIntegerList("全局配置.服务器设置.禁止发射器物品列表"))
            {
                itemIDs.add(itemID);
            }

            this.dispenserItemBans = new DispenserItemBansConfiguration(itemIDs);

            List<String> entityNames = new ArrayList<String>();

            for (String entityName : Main.config.getStringList("全局配置.服务器设置.禁止物体通过地狱门列表"))
            {
                entityNames.add(entityName);
            }

            this.netherPortalEntityBans = new NetherPortalEntityBansConfiguration(entityNames);

            this.clearRedstoneClock = Main.config.getBoolean("全局配置.服务器设置.清理高频红石.开启");

            this.heightWaterRemoves = new HeightWaterRemovesConfiguration(Main.config.getBoolean("全局配置.服务器设置.清理高空流水.开启"),
                    Main.config.getInt("全局配置.服务器设置.清理高空流水.源环境高度"),
                    Main.config.getInt("全局配置.服务器设置.清理高空流水.流水向下流动长度")
            );

            this.heightLavaRemoves = new HeightLavaRemovesConfiguration(Main.config.getBoolean("全局配置.服务器设置.清理高空岩浆.开启"),
                    Main.config.getInt("全局配置.服务器设置.清理高空岩浆.源环境高度"),
                    Main.config.getInt("全局配置.服务器设置.清理高空岩浆.岩浆向下流动长度")
            );

            this.clearInfinityItems = Main.config.getBoolean("全局配置.服务器设置.清理无限物品");

            this.chunkRemoves = new ChunkRemovesConfiguration(Main.config.getBoolean("全局配置.服务器设置.清理区块.开启"), Main.config.getInt("全局配置.服务器设置.清理区块.周期"));

            ConfigurationSection sections = Main.config.getConfigurationSection("全局配置.服务器设置.区块实体上限.实体列表");

            Set<String> keys = sections.getKeys(false);

            HashMap<EntityType, Integer> map = new HashMap<EntityType, Integer>();

            for (String key : keys)
            {
                map.put(EntityType.fromName(key), Main.config.getInt("全局配置.服务器设置.区块实体上限.实体列表." + key));
            }

            this.chunkEntityRemoves = new ChunkEntityRemovesConfiguration(Main.config.getBoolean("全局配置.服务器设置.区块实体上限.开启"), map);

            sections = Main.config.getConfigurationSection("全局配置.服务器设置.清理掉落物.提前警告列表");

            keys = sections.getKeys(false);

            HashMap<Integer, String> warningList = new HashMap<Integer, String>();

            for (String key : keys)
            {
                if (ISystem.integer.isInt(key))
                {
                    warningList.put(Integer.parseInt(key), Main.config.getString("全局配置.服务器设置.清理掉落物.提前警告列表." + key));
                }
            }

            List<Integer> whiteList = Main.config.getIntegerList("全局配置.服务器设置.清理掉落物.物品白名单");

            this.clearDrops = new ClearDropsConfiguration(
                    Main.config.getBoolean("全局配置.服务器设置.清理掉落物.开启"),
                    Main.config.getInt("全局配置.服务器设置.清理掉落物.周期"),
                    Main.config.getString("全局配置.服务器设置.清理掉落物.公告信息"),
                    warningList,
                    whiteList
            );
        }

        /**
         * 是否开启了死亡保护
         *
         * @return
         */
        public Boolean isEnabledDeathProtect()
        {
            return this.deathProtect;
        }

        /**
         * 获取权限检测
         *
         * @return
         */
        public PermissionDetectorConfiguration getPermissionDetector()
        {
            return this.permissionDetector;
        }

        /**
         * 获取禁止发射器物品列表
         *
         * @return
         */
        public DispenserItemBansConfiguration getDispenserItemBans()
        {
            return this.dispenserItemBans;
        }

        /**
         * 获取禁止物体通过地狱门列表
         *
         * @return
         */
        public NetherPortalEntityBansConfiguration getNetherPortalEntityBans()
        {
            return this.netherPortalEntityBans;
        }

        /**
         * 是否开启了清理高频红石
         *
         * @return
         */
        public Boolean isEnabledClearRedstoneClock()
        {
            return this.clearRedstoneClock;
        }

        /**
         * 获取清理高空流水
         *
         * @return
         */
        public HeightWaterRemovesConfiguration getHeightWaterRemoves()
        {
            return this.heightWaterRemoves;
        }

        /**
         * 获取清理高空岩浆
         *
         * @return
         */
        public HeightLavaRemovesConfiguration getHeightLavaRemoves()
        {
            return this.heightLavaRemoves;
        }

        /**
         * 是否清理无限物品
         *
         * @return
         */
        public Boolean isClearInfinityItems()
        {
            return this.clearInfinityItems;
        }

        /**
         * 获取清理区块
         *
         * @return
         */
        public ChunkRemovesConfiguration getChunkRemoves()
        {
            return this.chunkRemoves;
        }

        /**
         * 获取区块实体上限
         *
         * @return
         */
        public ChunkEntityRemovesConfiguration getChunkEntityRemoves()
        {
            return this.chunkEntityRemoves;
        }

        /**
         * 获取清理掉落物
         *
         * @return
         */
        public ClearDropsConfiguration getClearDrops()
        {
            return this.clearDrops;
        }

        public class PermissionDetectorConfiguration
        {

            private final OPDetector opDetector;
            private final CreativeDetector creativeDetector;

            public PermissionDetectorConfiguration(Boolean opDetectorEnable, List<String> opList, Boolean creativeDetectorEnable, List<String> creativeList)
            {
                this.opDetector = new OPDetector(opDetectorEnable, opList);
                this.creativeDetector = new CreativeDetector(creativeDetectorEnable, creativeList);
            }

            /**
             * 获取OP检测
             *
             * @return
             */
            public OPDetector getOPDetector()
            {
                return this.opDetector;
            }

            /**
             * 获取创造检测
             *
             * @return
             */
            public CreativeDetector getCreativeDetector()
            {
                return this.creativeDetector;
            }

            public class OPDetector
            {
                public final Boolean enable;
                public final List<String> whiteList = new ArrayList<String>();

                public OPDetector(Boolean enable, List<String> whiteList)
                {
                    this.enable = enable;

                    for (String name : whiteList)
                    {
                        this.whiteList.add(name);
                    }
                }
            }

            public class CreativeDetector
            {

                public final Boolean enable;
                public final List<String> whiteList = new ArrayList<String>();

                public CreativeDetector(Boolean enable, List<String> whiteList)
                {
                    this.enable = enable;

                    for (String name : whiteList)
                    {
                        this.whiteList.add(name);
                    }
                }
            }
        }

        public class DispenserItemBansConfiguration
        {

            public final List<Integer> itemIDs = new ArrayList<Integer>();

            public DispenserItemBansConfiguration(List<Integer> itemIDs)
            {
                for (Integer itemID : itemIDs)
                {
                    this.itemIDs.add(itemID);
                }
            }
        }

        public class NetherPortalEntityBansConfiguration
        {

            public final List<String> entityNames = new ArrayList<String>();

            public NetherPortalEntityBansConfiguration(List<String> entityNames)
            {
                for (String entityName : entityNames)
                {
                    this.entityNames.add(entityName);
                }
            }
        }

        public class HeightWaterRemovesConfiguration
        {

            public final Boolean enable;
            public final int height;
            public final int flowRange;

            public HeightWaterRemovesConfiguration(Boolean enable, int height, int flowRange)
            {
                this.enable = enable;
                this.height = height;
                this.flowRange = flowRange;
            }
        }

        public class HeightLavaRemovesConfiguration
        {

            public final Boolean enable;
            public final int height;
            public final int flowRange;

            public HeightLavaRemovesConfiguration(Boolean enable, int height, int flowRange)
            {
                this.enable = enable;
                this.height = height;
                this.flowRange = flowRange;
            }
        }

        public class ChunkRemovesConfiguration
        {

            public final Boolean enable;
            public final int cooldown;

            public ChunkRemovesConfiguration(Boolean enable, int cooldown)
            {
                this.enable = enable;
                this.cooldown = cooldown;
            }
        }

        public class ChunkEntityRemovesConfiguration
        {

            public final Boolean enable;
            public final HashMap<EntityType, Integer> list;

            public ChunkEntityRemovesConfiguration(Boolean enable, HashMap<EntityType, Integer> list)
            {
                this.enable = enable;
                this.list = list;
            }
        }

        public class ClearDropsConfiguration
        {
            public final Boolean enable;
            public final int cooldown;
            public final String broadcast;
            public final HashMap<Integer, String> warningList;
            public final List<Integer> whiteList;

            public ClearDropsConfiguration(Boolean enable, int cooldown, String broadcast, HashMap<Integer, String> warningList, List<Integer> whiteList)
            {
                this.enable = enable;
                this.cooldown = cooldown;
                this.broadcast = broadcast;
                this.warningList = warningList;
                this.whiteList = whiteList;
            }
        }
    }

    public class CommandsInfo
    {
        private final SurvivalConfiguration survival;

        public CommandsInfo()
        {
            this.survival = new SurvivalConfiguration(
                    Main.config.getBoolean("全局配置.用户指令.Survival.开启"),
                    Main.config.getString("全局配置.用户指令.Survival.主城领地"),
                    Main.config.getString("全局配置.用户指令.Survival.子领地"),
                    Main.config.getBoolean("全局配置.用户指令.Survival.目的地可为领地"),
                    Main.config.getInt("全局配置.用户指令.Survival.随机最大X"),
                    Main.config.getInt("全局配置.用户指令.Survival.随机最大Y"),
                    Main.config.getInt("全局配置.用户指令.Survival.随机最大Z"));
        }

        /**
         * 获取survival指令的信息
         *
         * @return
         */
        public SurvivalConfiguration getSurvival()
        {
            return this.survival;
        }

        public class SurvivalConfiguration
        {

            public final Boolean enable;
            public final String mainTown;
            public final String subArea;
            public final Boolean canInResidence;
            public final int maxX;
            public final int maxY;
            public final int maxZ;

            public SurvivalConfiguration(Boolean enable, String mainTown, String subArea, Boolean canInResidence, int maxX, int maxY, int maxZ)
            {
                this.enable = enable;
                this.mainTown = mainTown;
                this.subArea = subArea;
                this.canInResidence = canInResidence;
                this.maxX = maxX;
                this.maxY = maxY;
                this.maxZ = maxZ;
            }
        }
    }

    public class VIPInfo
    {
        private List<VIP> vips = new ArrayList<VIP>();

        public VIPInfo()
        {
            ConfigurationSection sections = Main.config.getConfigurationSection("全局配置.死亡保护");

            if (sections == null)
            {
                return;
            }

            Set<String> keys = sections.getKeys(false);

            if (keys == null)
            {
                return;
            }

            for (String key : keys)
            {
                try
                {
                    if (key.equals("普通用户"))
                    {
                        key = "VIP0";
                    }

                    VIP vip = (VIP) Class.forName("com.HotFlow.TribeCraft.Player.VIP." + key).newInstance();

                    if (key.equals("VIP0"))
                    {
                        key = "普通用户";
                    }

                    vip.setItemDropChance(Main.config.getDouble("全局配置.死亡保护." + key + ".物品掉落机率"));
                    vip.setArmorDropChance(Main.config.getDouble("全局配置.死亡保护." + key + ".装备掉落机率"));
                    vip.setExpDropPercentage(Main.config.getDouble("全局配置.死亡保护." + key + ".经验掉落百分比"));
                    vips.add(vip);
                }
                catch (ClassNotFoundException ex)
                {
                    Logger.getLogger(PluginConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (InstantiationException ex)
                {
                    Logger.getLogger(PluginConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IllegalAccessException ex)
                {
                    Logger.getLogger(PluginConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public VIP getVIP(int level)
        {
            for (VIP vip : this.vips)
            {
                if (vip.getLevel() == level)
                {
                    return vip;
                }
            }

            return null;
        }
    }
}
