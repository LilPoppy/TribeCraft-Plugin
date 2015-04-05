package com.HotFlow.TribeCraft.Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
	  private Connection conn;
	  public Mysql(String ip,int port,String username,String password){
		  this.ip=ip;
		  this.port=port;
		  this.username=username;
		  this.password=password;
	  }
	  /**
	   * 连接mysql
	   * @return true 连接成功
	   * false 连接失败
	   * 
	   */
	  public Boolean connect(){
		  String url=ip+":"+port;
		  String driver = "com.mysql.jdbc.Driver"; 
		try{
		  Class.forName(driver);
		  conn = DriverManager.getConnection(url, username, password); 
			if (conn.isClosed()){
				  return false;
			  }else{
				  return true;
			  }
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return false;
	  }
	  /**
	   * 执行指令
	   * @param command
	   * @return 返回信息
	   */
	public ResultSet executeCommand(String command){
		  Statement statement;
		try {
			statement = conn.createStatement();
			return statement.executeQuery(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	  }
	public ResultSet createDatabase(String name){
	  String command="create database "+name;
	  ResultSet rs=executeCommand(command);
	  
	}
}
