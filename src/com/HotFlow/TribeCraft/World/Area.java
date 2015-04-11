package com.HotFlow.TribeCraft.World;

import org.bukkit.Location;

/**
 * @author HotFlow
 */
public class Area {
	private final Location loc1;
	private final Location loc2;

	public Area(Location loc1, Location loc2) {
		this.loc1 = loc1;
		this.loc2 = loc2;
	}

	/**
	 * 获取坐标点1
	 * 
	 * @return
	 */
	public Location getLocation1() {
		return this.loc1;
	}

	/**
	 * 获取坐标点2
	 * 
	 * @return
	 */
	public Location getLocation2() {
		return this.loc2;
	}

	/**
	 * 区域是否与区域重叠
	 * 
	 * @param area1
	 * @param area2
	 * @return
	 */
	public static Boolean isAreaOverlappingArea(Area area1, Area area2) {
		if (area1.getLocation1().getWorld().getName() == null ? area2
				.getLocation1().getWorld().getName() == null : area1
				.getLocation1().getWorld().getName()
				.equals(area2.getLocation1().getWorld().getName())
				&& area1.getLocation2().getWorld().getName() == null ? area2
				.getLocation1().getWorld().getName() == null : area1
				.getLocation2().getWorld().getName()
				.equals(area2.getLocation1().getWorld().getName())) {
			if ((area1.getLocation1().getBlockX() >= area2.getLocation1()
					.getBlockX() && area1.getLocation1().getBlockX() <= area2
					.getLocation2().getBlockX())
					|| (area1.getLocation1().getBlockX() <= area2
							.getLocation1().getBlockX() && area1.getLocation1()
							.getBlockX() >= area2.getLocation2().getBlockX())
					|| (area1.getLocation2().getBlockX() >= area2
							.getLocation1().getBlockX() && area1.getLocation2()
							.getBlockX() <= area2.getLocation2().getBlockX())
					|| (area1.getLocation2().getBlockX() <= area2
							.getLocation1().getBlockX() && area1.getLocation2()
							.getBlockX() >= area2.getLocation2().getBlockX())) {
				if ((area1.getLocation1().getBlockZ() >= area2.getLocation1()
						.getBlockZ() && area1.getLocation1().getBlockZ() <= area2
						.getLocation2().getBlockZ())
						|| (area1.getLocation1().getBlockZ() <= area2
								.getLocation1().getBlockZ() && area1
								.getLocation1().getBlockZ() >= area2
								.getLocation2().getBlockZ())
						|| (area1.getLocation2().getBlockZ() >= area2
								.getLocation1().getBlockZ() && area1
								.getLocation2().getBlockZ() <= area2
								.getLocation2().getBlockZ())
						|| (area1.getLocation2().getBlockZ() <= area2
								.getLocation1().getBlockZ() && area1
								.getLocation2().getBlockZ() >= area2
								.getLocation2().getBlockZ())) {
					if ((area1.getLocation1().getBlockY() >= area2
							.getLocation1().getBlockY() && area1.getLocation1()
							.getBlockY() <= area2.getLocation2().getBlockY())
							|| (area1.getLocation1().getBlockY() <= area2
									.getLocation1().getBlockY() && area1
									.getLocation1().getBlockY() >= area2
									.getLocation2().getBlockY())
							|| (area1.getLocation2().getBlockY() >= area2
									.getLocation1().getBlockY() && area1
									.getLocation2().getBlockY() <= area2
									.getLocation2().getBlockY())
							|| (area1.getLocation2().getBlockY() <= area2
									.getLocation1().getBlockY() && area1
									.getLocation2().getBlockY() >= area2
									.getLocation2().getBlockY())) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * 区域是否包含坐标
	 * 
	 * @param area
	 * @param location
	 * @return
	 */
	public static Boolean isAreaContainLocation(Area area, Location location) {
		if (location.getWorld().getName() == null ? area.getLocation1()
				.getWorld().getName() == null : location.getWorld().getName()
				.equals(area.getLocation1().getWorld().getName())) {
			if ((location.getBlockX() >= area.getLocation1().getBlockX() && location
					.getBlockX() <= area.getLocation2().getBlockX())
					|| (location.getBlockX() <= area.getLocation1().getBlockX() && location
							.getBlockX() >= area.getLocation2().getBlockX())) {
				if ((location.getBlockZ() >= area.getLocation1().getBlockZ() && location
						.getBlockZ() <= area.getLocation2().getBlockZ())
						|| (location.getBlockZ() <= area.getLocation1()
								.getBlockZ() && location.getBlockZ() >= area
								.getLocation2().getBlockZ())) {
					if ((location.getBlockY() >= area.getLocation1()
							.getBlockY() && location.getBlockY() <= area
							.getLocation2().getBlockY())
							|| (location.getBlockY() <= area.getLocation1()
									.getBlockY() && location.getBlockY() >= area
									.getLocation2().getBlockY())) {
						return true;
					}
				}
			}
		}

		return false;
	}
}
