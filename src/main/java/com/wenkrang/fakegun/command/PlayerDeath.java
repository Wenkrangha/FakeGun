package com.wenkrang.fakegun.command;

import com.wenkrang.fakegun.FakeGun;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDeath implements Listener {
    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        // 延迟 1 tick 确保玩家进入死亡状态
        if (data.blue.contains(player) || data.red.contains(player)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    // 切换到旁观模式
                    player.sendTitle("§9重生§f冷却" , "", 0, 5, 3);
                    player.setGameMode(GameMode.SPECTATOR);
//                    if (data.red.contains(player)) {
//                        player.teleport(data.set1.getLocation());
//                    }
//                    if (data.blue.contains(player)) {
//                        player.teleport(data.set2.getLocation());
//                    }
                    player.teleport(data.set1.getLocation());

                    // 5 秒后执行恢复操作（20 ticks = 1 秒）
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (player.isOnline()) {
                                // 切换回冒险模式
                                player.setGameMode(GameMode.ADVENTURE);

                                // 传送到指定位置
//                                if (data.red.contains(player)) {
//                                    player.teleport(data.set1.getLocation());
//                                }
//                                if (data.blue.contains(player)) {
//                                    player.teleport(data.set2.getLocation());
//                                }
                                player.teleport(data.set1.getLocation());
                            }
                        }
                    }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 5 * 20); // 5 秒延迟
                }
            }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 1); // 1 tick 延迟
        }

    }
}
