package com.wenkrang.lib;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;


public class CollisionChecker {
    public static boolean isCollidingWithBlocks(Entity entity, int checkRadius) {
        Location location = entity.getLocation();

        for (int x = -checkRadius; x <= checkRadius; x++) {
            for (int y = -checkRadius; y <= checkRadius; y++) {
                for (int z = -checkRadius; z <= checkRadius; z++) {
                    Block block = location.getBlock().getRelative(x, y, z); // 获取相对位置的方块
                    if (block.getType() != Material.AIR) { // 如果方块不是空气，则发生碰撞
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
