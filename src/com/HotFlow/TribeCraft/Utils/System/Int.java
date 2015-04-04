package com.HotFlow.TribeCraft.Utils.System;

/**
 * @author HotFlow
 */
public class Int {
	public Boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
}
