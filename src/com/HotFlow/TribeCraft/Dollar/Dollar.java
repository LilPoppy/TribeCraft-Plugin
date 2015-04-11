package com.HotFlow.TribeCraft.Dollar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;

import com.HotFlow.TribeCraft.TribeCraft;
import com.HotFlow.TribeCraft.Configuration.TribeConfiguration;

public class Dollar {
	
	public static double getPlayerDollar(String name) {
		return 0;
	}
    /**
     * 初始化点卷系统
     * @return 1 初始化成功
     *  -1 mysql连接出错
     */
	public static int init() {
		File file = new File(TribeCraft.plugin.getDataFolder(), "dollar.yml");
		TribeConfiguration config = new TribeConfiguration();
		if (!TribeCraft.mysql.isConnecting()){
			return -1;
		}
		if (!file.exists()) {
			try {
				config.load(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			}
			config.set("table", "dollar");
			try {
				config.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        String table=config.getString("table");
        boolean hasTable=TribeCraft.mysql.hasTable(table);
        if (!hasTable){
        	TribeCraft.mysql.createTalbe(table, )
        }
		return 0;
	}
}
