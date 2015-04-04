package com.HotFlow.TribeCraft.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.HotFlow.TribeCraft.Player.TribePlayer;

/**
 * @author HotFlow
 */
public class PlayerManager {
	private final HashMap<String, TribePlayer> players = new HashMap<String, TribePlayer>();

	/**
	 * 获取玩家
	 * 
	 * @param name
	 * @return
	 */
	public TribePlayer getPlayer(String name) {
		return this.players.get(name);
	}

	/**
	 * 设置玩家
	 * 
	 * @param name
	 * @param player
	 */
	public void setPlayer(String name, TribePlayer player) {
		this.players.put(name, player);
	}

	/**
	 * 获取所有玩家
	 * 
	 * @return
	 */
	public List<TribePlayer> getPlayers() {
		List<TribePlayer> playerList = new ArrayList<TribePlayer>();

		for (TribePlayer player : this.players.values()) {
			playerList.add(player);
		}
		return playerList;
	}
}
