package com.wenkrang.fakegun.event;

import com.wenkrang.fakegun.PlayerCheck;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;

public class PlayerJoinE implements Listener {
    @EventHandler
    public static void OnPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        PlayerCheck.StartCheck(player);
    }
}
