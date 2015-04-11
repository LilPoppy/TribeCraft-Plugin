package com.HotFlow.TribeCraft.Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import com.HotFlow.TribeCraft.TribeCraft;
import com.HotFlow.TribeCraft.Timer.Timer;

/**
 * 
 * @author a404510
 *
 */
public class Mysql {
	public String ip;
	public String username;
	public String password;
	public int port;
	public String schema;
	private Connection conn;
	private Timer ConnectingTime = new Timer();

	public Mysql(String ip, int port, String username, String password,
			String schema) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.schema = schema;
		ConnectingTime.setTime(0);
	}

	/**
	 * 连接mysql
	 * 
	 * @return 是否成功
	 * 
	 */
	public synchronized Boolean connect() {
		String driver = "com.mysql.jdbc.Driver";
		ConnectingTime.setTime(0);
		ConnectingTime.setWork(false);
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection("jdbc:mysql://" + ip + port
					+ "/" + schema, username, password);
			if (conn.isClosed()) {
				return false;
			} else {
				ConnectingTime.setWork(true);
				return true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 创建表
	 * 
	 * @param slots
	 * @return 是否成功
	 */
	public synchronized boolean createTalbe(String name, Slot[] slots) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < slots.length; i++) {
			Slot slot = slots[i];
			sb.append(slot.flag);
			if (i < slots.length - 1) {
				sb.append(", ");
			}
		}
		try {
			PreparedStatement sql = this.conn.prepareStatement("CREATE TABLE "
					+ this.schema + "." + name + "(" + sb.toString() + ");");
			return sql.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public synchronized boolean isValueExist(String table, String key,
			String value) {
		int size = 0;
		try {
			PreparedStatement sql = this.conn.prepareStatement("SELECT " + key
					+ " FROM " + this.schema + "." + table + " WHERE " + key
					+ " ='" + value + "';");
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				size++;
			}
		} catch (SQLException ex) {
			TribeCraft.logger.log(Level.SEVERE, ex.toString());
		}
		return size > 0;
	}

	/**
	 * 添加数据
	 * 
	 * @param key
	 * @param value
	 * @return 添加数量
	 */
	public synchronized int insert(String table, String key, String value) {
		try {
			PreparedStatement sql = this.conn.prepareStatement("Insert into "
					+ this.schema + " " + table + "(" + key + ")" + " vaule "
					+ value + ");");
			sql.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取mysql连接时间
	 * 
	 * @return mysql连接时间
	 */
	public long getConnectingTime() {
		return ConnectingTime.getTime();
	}

	public boolean isConnecting() {
		try {
			return !conn.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public synchronized boolean hasTable(String table) {
		int size = 0;
		try {
			PreparedStatement sql = this.conn
					.prepareStatement("SHOW TABLES LIKE '" + table + "';");
			ResultSet result = sql.executeQuery();
			while (result.next()) {
				size++;
			}
		} catch (SQLException ex) {
			TribeCraft.logger.log(Level.SEVERE, ex.toString());
		}
		return size > 0;
	}
}
