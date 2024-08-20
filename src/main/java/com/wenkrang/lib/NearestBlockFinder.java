package com.wenkrang.lib;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class NearestBlockFinder {
    public static Block getNearestBlock(Entity entity, int searchRadius) {
        Location location = entity.getLocation();
        double smallestDistanceSquared = Double.MAX_VALUE;
        Block nearestBlock = null;

        for (int x = -searchRadius; x <= searchRadius; x++) {
            for (int y = -searchRadius; y <= searchRadius; y++) {
                for (int z = -searchRadius; z <= searchRadius; z++) {
                    Location blockLocation = location.clone().add(x, y, z);
                    Block block = blockLocation.getBlock();

                    // 检查是否是有效的方块类型（可选）
                    if (block.getType() != Material.AIR) {
                        double distanceSquared = entity.getLocation().distanceSquared(blockLocation);
                        if (distanceSquared < smallestDistanceSquared) {
                            smallestDistanceSquared = distanceSquared;
                            nearestBlock = block;
                        }
                    }
                }
            }
        }

        return nearestBlock;
    }
}
