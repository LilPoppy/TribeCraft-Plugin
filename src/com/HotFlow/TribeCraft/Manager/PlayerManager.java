package com.HotFlow.TribeCraft.Manager;

import com.HotFlow.TribeCraft.Player.TribePlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author HotFlow
 */
public class PlayerManager
{
    private final HashMap<UUID, TribePlayer> players = new HashMap<UUID, TribePlayer>();

    /**
     * 获取玩家
     *
     * @param uuid
     * @return
     */
    public TribePlayer getPlayer(UUID uuid)
    {
        for (UUID id : this.players.keySet())
        {
            if(id.equals(uuid))
            {
                return this.players.get(id);
            }
        }
        return null;
    }

    /**
     * 设置玩家
     *
     * @param uuid
     * @param player
     */
    public void setPlayer(UUID uuid, TribePlayer player)
    {
        this.players.put(uuid, player);
    }

    /**
     * 获取所有玩家
     *
     * @return
     */
    public List<TribePlayer> getPlayers()
    {
        List<TribePlayer> playerList = new ArrayList<TribePlayer>();

        for (TribePlayer player : this.players.values())
        {
            playerList.add(player);
        }
        
        return playerList;
    }

    /**
     * 是否拥有玩家
     *
     * @param uuid
     * @return
     */
    public Boolean hasPlayer(UUID uuid)
    {
        for (UUID id : this.players.keySet())
        {
            if(id.equals(uuid))
            {
                return true;
            }
        }

        return false;
    }
}
