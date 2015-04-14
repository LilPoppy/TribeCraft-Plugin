/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.HotFlow.TribeCraft.EnhanciveCommand;

import com.HotFlow.TribeCraft.TribeCraft;
import java.io.File;

/**
 *
 * @author Administrator
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
