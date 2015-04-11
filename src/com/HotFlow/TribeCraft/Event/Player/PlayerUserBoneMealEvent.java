package com.HotFlow.TribeCraft.Event.Player;

import com.HotFlow.TribeCraft.Player.TribePlayer;
import org.bukkit.event.Cancellable;

/**
 * @author HotFlow
 */
<<<<<<< HEAD:src/com/HotFlow/TribeCraft/Event/Player/PlayerIncSackEvent.java
public class PlayerIncSackEvent extends PlayerEvent implements Cancellable {
	private Boolean cancelled = false;
=======
public class PlayerUserBoneMealEvent extends PlayerEvent implements Cancellable
{
    private Boolean cancelled = true;
    
    public PlayerUserBoneMealEvent(TribePlayer player) 
    {
        super(player);
    }
>>>>>>> 017c23e43d8ca6e31087bf75893607eb0121c352:src/com/HotFlow/TribeCraft/Event/Player/PlayerUserBoneMealEvent.java

	public PlayerIncSackEvent(TribePlayer player) {
		super(player);
	}

	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean bln) {
		this.cancelled = bln;
	}

}
