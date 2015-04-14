package com.HotFlow.TribeCraft.EnhanciveCommand;

import com.HotFlow.TribeCraft.Configuration.TribeConfiguration;
import com.HotFlow.TribeCraft.TribeCraft;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

/**
 *
 * @author thtTNT
 */
public class ECCommandExecute implements CommandExecutor {

    public File DataFolder = TribeCraft.plugin.getDataFolder();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sendHelp(sender);
        }
        if (args[0].equalsIgnoreCase("add")) {
            if (args.length == 2) {
                if (!isFollowRule(args[1]).equalsIgnoreCase("pass")) {
                    sender.sendMessage("§4请不要使用保留关键字 " + args[1] + " 来作为指令的列表标识符");
                    return true;
                }
                File file = new File(DataFolder, "/EnhanciveCommand/" + args[1] + ".yml");
                if (file.exists()) {
                    sender.sendMessage("§4本指令列表标识符已经存在");
                    sender.sendMessage("§4输入指令/ec info <指令> 来获取更多信息");
                    return true;
                }
                TribeConfiguration config = new TribeConfiguration();
                try {
                    config.load(file);
                } catch (IOException ex) {
                    TribeCraft.logger.warning(ex.toString());
                } catch (InvalidConfigurationException ex) {
                    TribeCraft.logger.warning(ex.toString());
                }
                config.set("NumOfCommands", 0);
                config.set("permission", null);
                config.set("isEnable", false);
                try {
                    config.save(file);
                } catch (IOException ex) {
                    TribeCraft.logger.warning(ex.toString());
                }
                sender.sendMessage("§a指令列表标识符创建完成");
                return true;
            } else {
                sender.sendMessage("§a/ec add <指令> 添加增加强指令");
            }
        }
        if (args[1].equalsIgnoreCase("remove")) {
            if (args.length == 2) {
                File file = new File(DataFolder, "/EnhanciveCommand/" + args[1] + ".yml");
                if (!file.exists()) {
                    sender.sendMessage("§4指令标识符不存在");
                    return true;
                }
                file.delete();
                sender.sendMessage("删除成功");
                return true;
            } else {
                sender.sendMessage("§a/ec remove <指令> 删除增强指令");
            }
        }
        if (args[0].equalsIgnoreCase("enable")) {
            if (args.length == 2) {
                
                
            }
        }
        return false;
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage("§a/ec add <指令> 添加增加强指令");
        sender.sendMessage("§a/ec remove <指令> 删除增强指令");
        sender.sendMessage("§a/ec enable <指令> 启动增强指令");
    }

    private String isFollowRule(String st) {
        if (st.equalsIgnoreCase("config")) {
            return "config";
        }
        if (st.equalsIgnoreCase("add")) {
            return "add";
        }
        if (st.equalsIgnoreCase("remove")) {
            return "remove";
        }
        return "pass";
    }

    public TribeConfiguration getCommandConfig(String command) throws IOException, InvalidConfigurationException {
        File file = new File(DataFolder, "/EnhanciveCommand/" + command + ".yml");
        TribeConfiguration config = new TribeConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException ex) {
            throw ex;
        }
        return config;
    }
    public void SaveCommandConfig(String command,TribeConfiguration config) throws IOException, InvalidConfigurationException {
        File file = new File(DataFolder, "/EnhanciveCommand/" + command + ".yml");
        config.save(file);
    }
    
    public boolean editCommandConfig(String Command,String key,Object vaule,CommandSender sender){
       TribeConfiguration config;
        config = new TribeConfiguration();
        try {
                config = getCommandConfig(Command);
            } catch (IOException ex) {
                    sender.sendMessage("§a指令标识符不存在");
                    return false;
            } catch (InvalidConfigurationException ex) {
                    sender.sendMessage("§a读取文件错误");
                    String info = "增强指令系统警告——指令标识符" + Command + "config文件错误，请及时检查修复";
                    TribeCraft.plugin.getLogger().info(info);
                    return false;
            }
        config.set(key,vaule);
        try {
            SaveCommandConfig(Command,config);
        }  catch (IOException ex) {
                    sender.sendMessage("§a指令标识符不存在");
                    return false;
            } catch (InvalidConfigurationException ex) {
                    sender.sendMessage("§a读取文件错误");
                    String info = "增强指令系统警告——指令标识符" + Command + "config文件错误，请及时检查修复";
                    TribeCraft.plugin.getLogger().info(info);
                    return false;
        }
        return true;
    }
}
