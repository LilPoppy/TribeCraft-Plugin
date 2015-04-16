package com.HotFlow.TribeCraft.EnhanciveCommand;

import com.HotFlow.TribeCraft.TribeCraft;
import java.io.File;

/**
 *
 * @author thtTNT
 */
public class EnhanciveCommand {
    private static final File dataFolder=TribeCraft.plugin.getDataFolder();
    public static void init(){
        if (!TribeCraft.plugin.getDataFolder().exists()){
            dataFolder.mkdirs();
        }
        File configFile=new File(dataFolder,"/EnhanciveCommand");
        if (!configFile.exists()){
            configFile.mkdirs();
        }
    }
}
