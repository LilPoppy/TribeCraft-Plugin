package com.HotFlow.TribeCraft.Mysql;

public class Slot {
	public String flag;

	public Slot(String type, SlotRule[] rules, String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ").append(type).append(" ");
		for (SlotRule r : rules) {
			sb.append(r.name).append(" ");
		}
		this.flag = sb.toString();
	}
}
