package com.HotFlow.TribeCraft;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.HotFlow.TribeCraft.Configuration.TribeConfiguration;
import com.HotFlow.TribeCraft.Configuration.Writter;
import com.HotFlow.TribeCraft.Manager.PlayerManager;
import com.HotFlow.TribeCraft.Manager.PortalGateManager;
import com.HotFlow.TribeCraft.PortalGate.PortalGate;
import com.HotFlow.TribeCraft.PortalGate.PortalGateType;
import com.HotFlow.TribeCraft.World.Area;
import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ResidenceManager;

/**
 * @author HotFlow
 * @author a404510
 */
public class TribeCraft extends JavaPlugin {
	public static TribeCraft plugin;
	public static File dataFile;
	public static TribeConfiguration data = new TribeConfiguration();
	private static final PlayerManager playerManager = new PlayerManager();
	private static final PortalGateManager portalGateManager = new PortalGateManager();
	public static final Logger logger = Logger.getLogger("HotFlow");
	public static final String prefix = "[蛮族部落]";
	public static Residence residence;
	private static ResidenceManager residenceManager;
	private static Economy economyManager;
	private static Permission permissionManager;
	private static Chat chatManager;

	@Override
	public void onEnable() {
		TribeCraft.plugin = this;
		TribeCraft.dataFile = new File(getDataFolder(), "data.yml");

		this.loadData();

		if (TribeCraft.setupResidence()) {
			TribeCraft.logger.log(Level.INFO, prefix + " 领地系统安装成功!");
		} else {
			TribeCraft.logger.log(Level.SEVERE, prefix + " 领地系统安装失败成功!");
		}

		if (TribeCraft.setupEconomy()) {
			TribeCraft.logger.log(Level.INFO, prefix + " 经济系统安装成功!");
		} else {
			TribeCraft.logger.log(Level.SEVERE, prefix + " 经济系统安装失败成功!");
		}

		if (TribeCraft.setupPermission()) {
			TribeCraft.logger.log(Level.INFO, prefix + " 权限系统安装成功!");
		} else {
			TribeCraft.logger.log(Level.SEVERE, prefix + " 权限系统安装失败成功!");
		}

		if (TribeCraft.setupChat()) {
			TribeCraft.logger.log(Level.INFO, prefix + " 频道系统安装成功!");
		} else {
			TribeCraft.logger.log(Level.SEVERE, prefix + " 频道系统安装失败成功!");
		}

		getServer().getPluginManager().addPermission(
				new com.HotFlow.TribeCraft.Permissions.Permissions().admin);
		getServer().getPluginManager().addPermission(
				new com.HotFlow.TribeCraft.Permissions.Permissions().user);
		getCommand("Tribe").setExecutor(
				new com.HotFlow.TribeCraft.CommandExecutor.UserExecutor());
		getCommand("TribeAdmin").setExecutor(
				new com.HotFlow.TribeCraft.CommandExecutor.AdminExecutor());

		getServer().getPluginManager().registerEvents(
				new com.HotFlow.TribeCraft.Listener.Listeners(), this);
	}

	@Override
	public void onDisable() {
		getServer().getPluginManager().removePermission(
				new com.HotFlow.TribeCraft.Permissions.Permissions().admin);
		getServer().getPluginManager().removePermission(
				new com.HotFlow.TribeCraft.Permissions.Permissions().user);
		this.saveData();
	}

	/**
	 * 获取玩家管理中心
	 * 
	 * @return
	 */
	public static PlayerManager getPlayerManager() {
		return TribeCraft.playerManager;
	}

	/**
	 * 获取传送门管理中心
	 * 
	 * @return
	 */
	public static PortalGateManager getPortalGateManager() {
		return TribeCraft.portalGateManager;
	}

	/**
	 * 获取领地管理中心
	 * 
	 * @return
	 */
	public static ResidenceManager getResidenceManager() {
		return TribeCraft.residenceManager;
	}

	/**
	 * 获取经济管理中心
	 * 
	 * @return
	 */
	public static Economy getEconomyManager() {
		return TribeCraft.economyManager;
	}

	/**
	 * 获取权限管理中心
	 * 
	 * @return
	 */
	public static Permission getPermissionManager() {
		return TribeCraft.permissionManager;
	}

	/**
	 * 获取频道管理中心
	 * 
	 * @return
	 */
	public static Chat getChatManager() {
		return TribeCraft.chatManager;
	}

	/**
	 * 载入数据
	 */
	private void loadData() {
		if (!TribeCraft.dataFile.exists()) {
			try {
				TribeCraft.dataFile.getParentFile().mkdir();
				TribeCraft.dataFile.createNewFile();
				Writter writter = new Writter(TribeCraft.dataFile);
				writter.genData();
			} catch (IOException ex) {
				TribeCraft.logger.info(ex.toString());
			}
		}

		try {
			TribeCraft.data.load(TribeCraft.dataFile);
		} catch (IOException ex) {
			TribeCraft.logger.info(ex.toString());
		} catch (InvalidConfigurationException ex) {
			Logger.getLogger(TribeCraft.class.getName()).log(Level.SEVERE,
					null, ex);
		}

		ConfigurationSection gateSections = data
				.getConfigurationSection("传送门列表");

		if (gateSections != null) {
			Set<String> keys = gateSections.getKeys(false);

			if (keys != null) {
				for (String key : keys) {
					PortalGate gate = new PortalGate(key);

					String message = TribeCraft.data.getString("传送门列表." + key
							+ ".提示信息");

					gate.setMessage(message);
					gate.setCommands(TribeCraft.data.getStringList("传送门列表."
							+ key + ".触发命令"));

					Area area = new Area(
							new Location(getServer().getWorld(
									TribeCraft.data.getString("传送门列表." + key
											+ ".传送出发点.世界")),
									Double.parseDouble(TribeCraft.data
											.getString(
													"传送门列表." + key
															+ ".传送出发点.坐标点1")
											.split(",")[0]),
									Double.parseDouble(TribeCraft.data
											.getString(
													"传送门列表." + key
															+ ".传送出发点.坐标点1")
											.split(",")[1]),
									Double.parseDouble(TribeCraft.data
											.getString(
													"传送门列表." + key
															+ ".传送出发点.坐标点1")
											.split(",")[2])), new Location(
									getServer().getWorld(
											TribeCraft.data.getString("传送门列表."
													+ key + ".传送出发点.世界")),
									Double.parseDouble(TribeCraft.data
											.getString(
													"传送门列表." + key
															+ ".传送出发点.坐标点2")
											.split(",")[0]),
									Double.parseDouble(TribeCraft.data
											.getString(
													"传送门列表." + key
															+ ".传送出发点.坐标点2")
											.split(",")[1]),
									Double.parseDouble(TribeCraft.data
											.getString(
													"传送门列表." + key
															+ ".传送出发点.坐标点2")
											.split(",")[2])));

					gate.setFrom(area);

					if (TribeCraft.data.getString("传送门列表." + key + ".传送类型")
							.equalsIgnoreCase("传送点")) {
						gate.setType(PortalGateType.Location);
					} else if (TribeCraft.data.getString(
							"传送门列表." + key + ".传送类型").equalsIgnoreCase("随机点")) {
						gate.setType(PortalGateType.Random);
					} else {
						gate.setType(PortalGateType.Command);
					}

					Location location = new Location(
							getServer().getWorld(
									TribeCraft.data.getString("传送门列表." + key
											+ ".传送到达点.世界")),
							Double.parseDouble(TribeCraft.data.getString(
									"传送门列表." + key + ".传送到达点.坐标点").split(",")[0]),
							Double.parseDouble(TribeCraft.data.getString(
									"传送门列表." + key + ".传送到达点.坐标点").split(",")[1]),
							Double.parseDouble(TribeCraft.data.getString(
									"传送门列表." + key + ".传送到达点.坐标点").split(",")[2]));

					gate.setTo(location);

					TribeCraft.portalGateManager.addPortalGate(gate);
				}
			}
		}
	}

	/**
	 * 保存数据
	 */
	private void saveData() {
		for (PortalGate gate : TribeCraft.getPortalGateManager()
				.getPortalGates()) {
			if (gate.getTo() != null) {
				TribeCraft.data.set("传送门列表." + gate.getName() + ".提示信息",
						gate.getMessage());
				TribeCraft.data.set("传送门列表." + gate.getName() + ".触发命令",
						gate.getCommands());
				TribeCraft.data.set("传送门列表." + gate.getName() + ".传送出发点.世界",
						gate.getFrom().getLocation1().getWorld().getName());
				TribeCraft.data.set("传送门列表." + gate.getName() + ".传送出发点.坐标点1",
						gate.getFrom().getLocation1().getX() + ","
								+ gate.getFrom().getLocation1().getY() + ","
								+ gate.getFrom().getLocation1().getZ());
				TribeCraft.data.set("传送门列表." + gate.getName() + ".传送出发点.坐标点2",
						gate.getFrom().getLocation2().getX() + ","
								+ gate.getFrom().getLocation2().getY() + ","
								+ gate.getFrom().getLocation2().getZ());
				TribeCraft.data.set("传送门列表." + gate.getName() + ".传送到达点.世界",
						gate.getTo().getWorld().getName());
				TribeCraft.data.set("传送门列表." + gate.getName() + ".传送到达点.坐标点",
						gate.getTo().getX() + "," + gate.getTo().getY() + ","
								+ gate.getTo().getZ());

				if (gate.getType().equals(PortalGateType.Location)) {
					TribeCraft.data.set("传送门列表." + gate.getName() + ".传送类型",
							"传送点");
				} else if (gate.getType().equals(PortalGateType.Random)) {
					TribeCraft.data.set("传送门列表." + gate.getName() + ".传送类型",
							"随机点");
				} else {
					TribeCraft.data.set("传送门列表." + gate.getName() + ".传送类型",
							"命令点");
				}
			} else {
				TribeCraft.logger.log(Level.SEVERE, TribeCraft.prefix + "传送门"
						+ gate.getName() + "没有传送点，不保存。");
			}
		}

		try {
			TribeCraft.data.save(dataFile);
		} catch (IOException ex) {
			TribeCraft.logger.info(ex.toString());
		}
	}

	/**
	 * 安装领地系统
	 */
	private static Boolean setupResidence() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Residence") == null) {
			return false;
		}

		TribeCraft.residence = (Residence) Bukkit.getPluginManager().getPlugin(
				"Residence");

		TribeCraft.residenceManager = Residence.getResidenceManager();

		if (TribeCraft.residenceManager == null) {
			return false;
		}

		if (!TribeCraft.residence.isEnabled()) {
			return false;
		}

		return (TribeCraft.residence != null);
	}

	/**
	 * 安装经济系统
	 * 
	 * @return
	 */
	public static Boolean setupEconomy() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		@SuppressWarnings("rawtypes")
		RegisteredServiceProvider economyProvider = Bukkit.getServer()
				.getServicesManager().getRegistration(Economy.class);

		if (economyProvider != null) {
			TribeCraft.economyManager = (Economy) economyProvider.getProvider();
		}

		return TribeCraft.economyManager != null;
	}

	/**
	 * 安装权限系统
	 * 
	 * @return
	 */
	public static Boolean setupPermission() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		@SuppressWarnings("rawtypes")
		RegisteredServiceProvider permissionProvider = Bukkit.getServer()
				.getServicesManager().getRegistration(Permission.class);

		if (permissionProvider != null) {
			TribeCraft.permissionManager = (Permission) permissionProvider
					.getProvider();
		}

		return TribeCraft.permissionManager != null;
	}

	/**
	 * 安装频道系统
	 * 
	 * @return
	 */
	public static Boolean setupChat() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		@SuppressWarnings("rawtypes")
		RegisteredServiceProvider chatProvider = Bukkit.getServer()
				.getServicesManager().getRegistration(Chat.class);

		if (chatProvider != null) {
			TribeCraft.chatManager = (Chat) chatProvider.getProvider();
		}

		return TribeCraft.chatManager != null;
	}
}
