package com.HotFlow.TribeCraft.Mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
	  public String table;
	  private Connection conn;
	  public Mysql(String ip,int port,String username,String password,String schema,String table){
		  this.ip=ip;
		  this.port=port;
		  this.username=username;
		  this.password=password;
		  this.schema=schema;
		  this.table=table;
		  
	  }
	  /**
	   * 连接mysql
	   * @return 是否成功
	   * 
	   */
	  public Boolean connect(){
		  String driver = "com.mysql.jdbc.Driver"; 
		try{
		  Class.forName(driver);
		  conn = DriverManager.getConnection("jdbc:mysql://"+ip+port+"/"+schema,username,password); 
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
	   * 创建表
	   * @param slots
	   * @return 是否成功
	   */
      public synchronized boolean createTalbe(String name,Slot[] slots){
    	  StringBuilder sb=new StringBuilder();
    	  for (int i=0;i<slots.length;i++){
    		  Slot slot=slots[i];
    		  sb.append(slot.flag);
    		  if (i<slots.length-1){
    			  sb.append(", ");
    		  }
    	  }
    	  try {
			PreparedStatement sql=this.conn.prepareStatement("CREATE TABLE "+this.schema+"."+this.table+"("+sb.toString()+");");
			return sql.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
      }
      
      /**
       * 添加数据
       * @param key
       * @param value
       * @return 添加数量
       */
      public synchronized int insert(String key,String value){
		try {
			PreparedStatement sql=this.conn.prepareStatement("Insert into "+this.schema+" "+this.table+"("+key+")"+" vaule "+value+");");
			sql.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
      }
      
}
