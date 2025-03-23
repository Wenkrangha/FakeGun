package com.wenkrang.fakegun.event;

import com.wenkrang.fakegun.FakeGun;
import com.wenkrang.fakegun.Gun;
import com.wenkrang.fakegun.PlayerCheck;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.Random;

public class PlayerJoin implements Listener {
    @EventHandler
    public static void OnPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        PlayerCheck.StartCheck(player);
    }
}
