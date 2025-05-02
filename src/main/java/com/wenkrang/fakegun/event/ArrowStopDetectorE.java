package com.wenkrang.fakegun.event;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowStopDetectorE implements Listener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        // 检查射出物是否为箭矢
        if (event.getEntityType() == EntityType.ARROW) {
            Arrow arrow = (Arrow) event.getEntity();
            if (arrow.getScoreboardTags().contains("AntiAir")) {
                arrow.remove();
            }
        }
    }
}
