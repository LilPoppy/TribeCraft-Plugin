package com.HotFlow.TribeCraft.Manager;

import com.HotFlow.TribeCraft.TribeCraft;

/**
 * @author HotFlow
 */
public class PluginManager 
{
    private final SurvivalConfiguration survivalConfig;
    
    public PluginManager()
    {
        this.survivalConfig = new SurvivalConfiguration(TribeCraft.config.getBoolean("全局配置.用户指令.Survival.开启"),
                                                TribeCraft.config.getString("全局配置.用户指令.Survival.主城领地"),
                                                TribeCraft.config.getString("全局配置.用户指令.Survival.子领地"),
                                                TribeCraft.config.getBoolean("全局配置.用户指令.Survival.目的地可为领地"),
                                                TribeCraft.config.getInt("全局配置.用户指令.Survival.随机最大X"),
                                                TribeCraft.config.getInt("全局配置.用户指令.Survival.随机最大Y"),
                                                TribeCraft.config.getInt("全局配置.用户指令.Survival.随机最大Z"));
    }
    
    /**
     * 获取survival指令的信息
     * @return 
     */
    public SurvivalConfiguration getSurvivalProperties()
    {
        return this.survivalConfig;
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
        
        public SurvivalConfiguration(Boolean enable,String mainTown,String subArea,Boolean canInResidence,int maxX,int maxY,int maxZ)
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
