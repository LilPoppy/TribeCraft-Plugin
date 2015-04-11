package com.HotFlow.TribeCraft.Configuration;

import com.HotFlow.TribeCraft.TribeCraft;
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
public class Writter {
	private final File file;

	public Writter(File file) {
		this.file = file;
	}

	public void write(int index, String s) {
		if (!this.file.exists()) {
			return;
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			ArrayList<String> lines = new ArrayList<String>();
			String line = null;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
			file.delete();

			if (index >= lines.size()) {
				lines.add(s);
			}

			for (int i = 0; i < lines.size(); i++) {
				if (i == index) {
					lines.set(index, s);
				}
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < lines.size(); i++) {
				writer.write(new String(lines.get(i).getBytes("gb2312"),
						"gb2312"));
				writer.newLine();
			}
			writer.close();
		} catch (IOException ex) {
			TribeCraft.logger.warning(ex.toString());
		}
	}

	public void genData() {
		this.write(0, "传送门列表:");
	}

	public void genConfig() {
		this.write(0, "全局配置:");
		this.write(1, "  死亡保护:");
		this.write(2, "    普通用户:");
		this.write(3, "      物品掉落机率: 0.50");
		this.write(4, "      装备掉落机率: 0.50");
		this.write(5, "      经验掉落百分比: 0.50");
		this.write(6, "    VIP1:");
		this.write(7, "      物品掉落机率: 0.45");
		this.write(8, "      装备掉落机率: 0.45");
		this.write(9, "      经验掉落百分比: 0.45");
		this.write(10, "    VIP2:");
		this.write(11, "      物品掉落机率: 0.40");
		this.write(12, "      装备掉落机率: 0.40");
		this.write(13, "      经验掉落百分比: 0.40");
		this.write(14, "    VIP3:");
		this.write(15, "      物品掉落机率: 0.35");
		this.write(16, "      装备掉落机率: 0.35");
		this.write(17, "      经验掉落百分比: 0.35");
		this.write(18, "    VIP4:");
		this.write(19, "      物品掉落机率: 0.30");
		this.write(20, "      装备掉落机率: 0.30");
		this.write(21, "      经验掉落百分比: 0.30");
		this.write(22, "    VIP5:");
		this.write(23, "      物品掉落机率: 0.25");
		this.write(24, "      装备掉落机率: 0.25");
		this.write(25, "      经验掉落百分比: 0.25");
		this.write(26, "    VIP6:");
		this.write(27, "      物品掉落机率: 0.20");
		this.write(28, "      装备掉落机率: 0.20");
		this.write(29, "      经验掉落百分比: 0.20");
		this.write(30, "    VIP7:");
		this.write(31, "      物品掉落机率: 0.15");
		this.write(32, "      装备掉落机率: 0.15");
		this.write(33, "      经验掉落百分比: 0.15");
		this.write(34, "    VIP8:");
		this.write(35, "      物品掉落机率: 0.10");
		this.write(36, "      装备掉落机率: 0.10");
		this.write(37, "      经验掉落百分比: 0.10");
		this.write(38, "    VIP9:");
		this.write(39, "      物品掉落机率: 0.5");
		this.write(40, "      装备掉落机率: 0.5");
		this.write(41, "      经验掉落百分比: 0.5");
		this.write(42, "    VIP10:");
		this.write(43, "      物品掉落机率: 0.1");
		this.write(44, "      装备掉落机率: 0.1");
		this.write(45, "      经验掉落百分比: 0.1");
		this.write(46, "  用户指令:");
		this.write(47, "    Survival:");
		this.write(48, "      开启: true");
		this.write(49, "      主城领地: Main");
		this.write(50, "      子领地: main");
		this.write(51, "      目的地可为领地: false");
		this.write(52, "      随机最大X: 3000");
		this.write(53, "      随机最大Y: 100");
		this.write(54, "      随机最大Z: 3000");
	}
}
