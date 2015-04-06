package com.HotFlow.TribeCraft.Mysql;

/**
 * 
 * @author thtTNT
 *
 */
  public enum SlotRule{
	  PrimaryKey("Primary Key"),
	    NotNull("Not Null"),
	    Unique("Unique"),
	    Binary("Binary"),
	    Unsigned("Unsigned"),
	    ZeroFill("Zero Fill"),
	    AutoIncrement("Auto Increment");
	    
	    String name;
	    
	    private SlotRule(String name)
	    {
	        this.name = name;
	    }
  }
