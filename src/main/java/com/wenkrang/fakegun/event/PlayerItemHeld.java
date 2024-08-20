package com.wenkrang.fakegun.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerItemHeld implements Listener {
    @EventHandler
    public static void OnItemHeldEvent(org.bukkit.event.player.PlayerItemHeldEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("FireNow")){
            event.getPlayer().removeScoreboardTag("FireNow");
        }
    }
}
