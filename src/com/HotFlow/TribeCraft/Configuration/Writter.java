package com.HotFlow.TribeCraft.Configuration;

import com.HotFlow.TribeCraft.Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author HotFlow
 */
public class Writter
{
    private final File file;

    public Writter(File file)
    {
        this.file = file;
    }

    public void write(int index, String s)
    {
        if (!this.file.exists())
        {
            return;
        }

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
            reader.close();
            file.delete();

            if (index >= lines.size())
            {
                lines.add(s);
            }

            for (int i = 0; i < lines.size(); i++)
            {
                if (i == index)
                {
                    lines.set(index, s);
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < lines.size(); i++)
            {
                writer.write(new String(lines.get(i).getBytes("gb2312"), "gb2312"));
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException ex)
        {
            Main.logger.warning(ex.toString());
        }
    }

    public void write(String s)
    {
        if (!this.file.exists())
        {
            return;
        }

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
            lines.add(s);

            reader.close();
            file.delete();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < lines.size(); i++)
            {
                writer.write(new String(lines.get(i).getBytes("gb2312"), "gb2312"));
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException ex)
        {
            Main.logger.warning(ex.toString());
        }
    }

    public void genData()
    {
        this.write(0, "传送门列表:");
    }

    public void genConfig()
    {
        this.write("全局配置:");
        this.write("  服务器设置:");
        this.write("    死亡保护: true");
        this.write("    权限检测:");
        this.write("      OP检测:");
        this.write("        开启: true");
        this.write("        白名单:");
        this.write("        - HotFlow");
        this.write("        - SilenceFlow");
        this.write("        - thtTNT");
        this.write("      创造检测:");
        this.write("        开启: true");
        this.write("        白名单: []");
        this.write("    禁止发射器物品列表:");
        this.write("    - 326");
        this.write("    - 327");
        this.write("    - 351");
        this.write("    禁止物体通过地狱门列表:");
        this.write("    - MINECART");
        this.write("    - CHESTMINECART");
        this.write("    - HOPPERMINECART");
        this.write("    清理高频红石:");
        this.write("      开启: true");
        this.write("    清理高空流水:");
        this.write("      开启: true");
        this.write("      源环境高度: 120");
        this.write("      流水向下流动长度: 10");
        this.write("    清理高空岩浆:");
        this.write("      开启: true");
        this.write("      源环境高度: 120");
        this.write("      岩浆向下流动长度: 10");
        this.write("    清理区块:");
        this.write("      开启: true");
        this.write("      周期: 360");
        this.write("    区块实体上限:");
        this.write("      开启: true");
        this.write("      实体列表:");
        this.write("        CHICKEN: 8");
        this.write("        COW: 8");
        this.write("        MUSHROOMCOW: 8");
        this.write("        SHEEP: 8");
        this.write("        PIG: 8");
        this.write("        WOLF: 8");
        this.write("        HORSE: 8");
        this.write("        SNOWMAN: 8");
        this.write("        IRONGOLEM: 8");
        this.write("        VILLAGER: 8");
        this.write("        SQUID: 8");
        this.write("    清理无限物品: true");
        this.write("    清理掉落物:");
        this.write("      开启: true");
        this.write("      周期: 300");
        this.write("      公告信息: '&c&l[清理公告] &f已移除 %RemovedAmount% 件物品!'");
        this.write("      提前警告列表:");
        this.write("        60: '&c&l[清理公告] &f服务器将在一分钟后清除所有掉落物品!'");
        this.write("      物品白名单:");
        this.write("      - 56");
        this.write("      - 57");
        this.write("      - 129");
        this.write("      - 133");
        this.write("      - 138");
        this.write("      - 203");
        this.write("      - 264");
        this.write("      - 276");
        this.write("      - 277");
        this.write("      - 278");
        this.write("      - 279");
        this.write("      - 310");
        this.write("      - 311");
        this.write("      - 312");
        this.write("      - 313");
        this.write("      - 4414");
        this.write("      - 4415");
        this.write("    自动保存世界:");
        this.write("      开启: true");
        this.write("      周期: 300");
        this.write("  死亡保护:");
        this.write("    普通用户:");
        this.write("      物品掉落机率: 0.50");
        this.write("      装备掉落机率: 0.50");
        this.write("      经验掉落百分比: 0.50");
        this.write("    VIP1:");
        this.write("      物品掉落机率: 0.45");
        this.write("      装备掉落机率: 0.45");
        this.write("      经验掉落百分比: 0.45");
        this.write("    VIP2:");
        this.write("      物品掉落机率: 0.40");
        this.write("      装备掉落机率: 0.40");
        this.write("      经验掉落百分比: 0.40");
        this.write("    VIP3:");
        this.write("      物品掉落机率: 0.35");
        this.write("      装备掉落机率: 0.35");
        this.write("      经验掉落百分比: 0.35");
        this.write("    VIP4:");
        this.write("      物品掉落机率: 0.30");
        this.write("      装备掉落机率: 0.30");
        this.write("      经验掉落百分比: 0.30");
        this.write("    VIP5:");
        this.write("      物品掉落机率: 0.25");
        this.write("      装备掉落机率: 0.25");
        this.write("      经验掉落百分比: 0.25");
        this.write("    VIP6:");
        this.write("      物品掉落机率: 0.20");
        this.write("      装备掉落机率: 0.20");
        this.write("      经验掉落百分比: 0.20");
        this.write("    VIP7:");
        this.write("      物品掉落机率: 0.15");
        this.write("      装备掉落机率: 0.15");
        this.write("      经验掉落百分比: 0.15");
        this.write("    VIP8:");
        this.write("      物品掉落机率: 0.10");
        this.write("      装备掉落机率: 0.10");
        this.write("      经验掉落百分比: 0.10");
        this.write("    VIP9:");
        this.write("      物品掉落机率: 0.05");
        this.write("      装备掉落机率: 0.05");
        this.write("      经验掉落百分比: 0.05");
        this.write("    VIP10:");
        this.write("      物品掉落机率: 0.01");
        this.write("      装备掉落机率: 0.01");
        this.write("      经验掉落百分比: 0.01");
        this.write("  用户指令:");
        this.write("    Survival:");
        this.write("      开启: true");
        this.write("      主城领地: Main");
        this.write("      子领地: main");
        this.write("      目的地可为领地: false");
        this.write("      随机最大X: 3000");
        this.write("      随机最大Y: 100");
        this.write("      随机最大Z: 3000");
        this.write("  插件兼容:");
        this.write("    Vault:");
        this.write("      Permission: true");
        this.write("      Economy: true");
        this.write("      Chat: true");
        this.write("    Residence: true");
    }
}
