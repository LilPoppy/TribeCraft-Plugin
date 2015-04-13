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

    public PluginConfig()
    {
        this.serverConfig = new ServerConfig();
        this.commandsInfo = new CommandsInfo();
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

    private class ServerConfig
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

            for (Integer itemID : TribeCraft.config.getIntegerList("全局配置.服务器设置.禁止发射器物品列表"))
            {
                itemIDs.add(itemID);
            }

            this.dispenserItemBans = new DispenserItemBansConfiguration(itemIDs);

            List<String> entityNames = new ArrayList<String>();

            for (String entityName : TribeCraft.config.getStringList("全局配置.服务器设置.禁止物体通过地狱门列表"))
            {
                entityNames.add(entityName);
            }

            this.netherPortalEntityBans = new NetherPortalEntityBansConfiguration(entityNames);

            this.blockCantFloating = TribeCraft.config.getBoolean("全局配置.服务器设置.禁止浮空方块.开启");

            this.cleanRedstoneClock = TribeCraft.config.getBoolean("全局配置.服务器设置.清理高频红石.开启");

            this.heightWaterRemoves = new HeightWaterRemovesConfiguration(TribeCraft.config.getBoolean("全局配置.服务器设置.清理高空流水.开启"),
                    TribeCraft.config.getInt("全局配置.服务器设置.清理高空流水.源环境高度"),
                    TribeCraft.config.getInt("全局配置.服务器设置.清理高空流水.流水向下流动长度")
            );
            
            this.heightLavaRemoves = new HeightLavaRemovesConfiguration(TribeCraft.config.getBoolean("全局配置.服务器设置.清理高空岩浆.开启"),
                    TribeCraft.config.getInt("全局配置.服务器设置.清理高空岩浆.源环境高度"),
                    TribeCraft.config.getInt("全局配置.服务器设置.清理高空岩浆.岩浆向下流动长度")
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
                    TribeCraft.config.getBoolean("全局配置.用户指令.Survival.开启"),
                    TribeCraft.config.getString("全局配置.用户指令.Survival.主城领地"),
                    TribeCraft.config.getString("全局配置.用户指令.Survival.子领地"),
                    TribeCraft.config.getBoolean("全局配置.用户指令.Survival.目的地可为领地"),
                    TribeCraft.config.getInt("全局配置.用户指令.Survival.随机最大X"),
                    TribeCraft.config.getInt("全局配置.用户指令.Survival.随机最大Y"),
                    TribeCraft.config.getInt("全局配置.用户指令.Survival.随机最大Z"));
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
}
