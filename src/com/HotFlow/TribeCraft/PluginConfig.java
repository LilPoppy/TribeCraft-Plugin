package com.HotFlow.TribeCraft;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HotFlow
 */
public class PluginConfig
{

    private final ServerConfig serverConfig;
    private final CommandsInfo commandsInfo;
    private final VIPInfo vipInfo;

    public PluginConfig()
    {
        this.serverConfig = new ServerConfig();
        this.commandsInfo = new CommandsInfo();
        this.vipInfo = new VIPInfo();
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
        private final DispenserItemBansConfiguration dispenserItemBans;
        private final NetherPortalEntityBansConfiguration netherPortalEntityBans;
        private final Boolean blockCantFloating;
        private final Boolean cleanRedstoneClock;
        private final HeightWaterRemovesConfiguration heightWaterRemoves;
        private final HeightLavaRemovesConfiguration heightLavaRemoves;

        public ServerConfig()
        {

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

            this.blockCantFloating = Main.config.getBoolean("全局配置.服务器设置.禁止浮空方块.开启");

            this.cleanRedstoneClock = Main.config.getBoolean("全局配置.服务器设置.清理高频红石.开启");

            this.heightWaterRemoves = new HeightWaterRemovesConfiguration(Main.config.getBoolean("全局配置.服务器设置.清理高空流水.开启"),
                    Main.config.getInt("全局配置.服务器设置.清理高空流水.源环境高度"),
                    Main.config.getInt("全局配置.服务器设置.清理高空流水.流水向下流动长度")
            );

            this.heightLavaRemoves = new HeightLavaRemovesConfiguration(Main.config.getBoolean("全局配置.服务器设置.清理高空岩浆.开启"),
                    Main.config.getInt("全局配置.服务器设置.清理高空岩浆.源环境高度"),
                    Main.config.getInt("全局配置.服务器设置.清理高空岩浆.岩浆向下流动长度")
            );

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
         * 是否禁止浮空方块
         *
         * @return
         */
        public Boolean isBlockCantFloating()
        {
            return this.blockCantFloating;
        }

        /**
         * 是否清理高频红石
         *
         * @return
         */
        public Boolean isCleanRedstoneClock()
        {
            return this.cleanRedstoneClock;
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

        public class PermissionDetectorConfigraution
        {

            private final OPDetector opDetector;
            private final CreativeDetector creativeDetector;

            public PermissionDetectorConfigraution(OPDetector opDetector, CreativeDetector creativeDetector)
            {
                this.opDetector = opDetector;
                this.creativeDetector = creativeDetector;
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
        private RateOfDrop dropInfo;

        public VIPInfo()
        {
            this.dropInfo = new RateOfDrop();
        }

        public RateOfDrop getRateOfDrop()
        {
            return this.dropInfo;
        }

        public class RateOfDrop
        {
            private VIP[] VipInfolist = new VIP[10];

            public RateOfDrop()
            {
                VipInfolist[0].RateOfItem = Main.config.getDouble("全局配置.死亡保护.普通用户.物品掉落几率");
                VipInfolist[0].RateOfEquipment = Main.config.getDouble("全局配置.死亡保护.普通用户.装备掉落几率");
                VipInfolist[0].RateofExp = Main.config.getDouble("全局配置.死亡保护.普通用户.经验掉落百分比");

                for (int i = 1; i <= 10; i++)
                {
                    VipInfolist[i].RateOfItem = Main.config.getDouble("全局配置.死亡保护.VIP" + i + ".物品掉落几率");
                    VipInfolist[i].RateOfEquipment = Main.config.getDouble("全局配置.死亡保护.VIP" + i + ".装备掉落几率");
                    VipInfolist[i].RateofExp = Main.config.getDouble("全局配置.死亡保护.VIP" + i + ".经验掉落百分比");
                }
            }

            /**
             * 获取掉落几率的信息
             *
             * @param level
             * @return
             */
            public VIP getVIP(int level)
            {
                return VipInfolist[level];
            }

            public class VIP
            {
                public double RateOfItem;
                public double RateOfEquipment;
                public double RateofExp;
            }
        }
    }
}
