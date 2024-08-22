package com.wenkrang.fakegun.event;

import com.wenkrang.fakegun.FakeGun;
import com.wenkrang.fakegun.gun;
import com.wenkrang.lib.SpigotConsoleColors;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class PlayerJoin implements Listener {
    public static int countsomething(Inventory inventory, String s) {
        int count = 0;
        for (int i = 0;i < inventory.getSize();i++) {
            if (inventory.getItem(i).getItemMeta() != null && inventory.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase(s)) {
                count++;
            }
        }
        return count;
    }

    @EventHandler
    public static void OnPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("FireNow")) {
            event.getPlayer().removeScoreboardTag("FireNow");
        }
        if (event.getPlayer().getScoreboardTags().contains("reload")) {
            event.getPlayer().removeScoreboardTag("reload");
        }
        if (event.getPlayer().getScoreboardTags().contains("keeping")) {
            event.getPlayer().removeScoreboardTag("keeping");
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!event.getPlayer().isOnline()) {
                    cancel();
                }
                if (!event.getPlayer().getScoreboardTags().contains("FireNow")) {

                    if (event.getPlayer().getInventory().getItemInOffHand().getItemMeta() != null && gun.getgun(event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName()) != null && !event.getPlayer().getScoreboardTags().contains("reload")) {
                        Damageable damageable = (Damageable) event.getPlayer().getInventory().getItemInOffHand().getItemMeta();

                        event.getPlayer().addScoreboardTag("reload");
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                Damageable damageable1 = (Damageable) event.getPlayer().getInventory().getItemInOffHand().getItemMeta();
                                for (int i = 0; i < gun.getgun(event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName()).getReloadtime(); i++) {
                                    if (event.getPlayer().isOnline() && !event.getPlayer().getScoreboardTags().contains("FireNow") && event.getPlayer().getInventory().getItemInOffHand().getItemMeta() != null && gun.getgun(event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName()) != null && damageable1.getDamage() != 0) {

                                        damageable1.setDamage(damageable1.getDamage() - 1);
                                        ItemStack itemInOffHand = event.getPlayer().getInventory().getItemInOffHand();
                                        itemInOffHand.setItemMeta(damageable1);
                                        event.getPlayer().getInventory().setItemInOffHand(itemInOffHand);
                                    } else {
                                        event.getPlayer().removeScoreboardTag("reload");
                                        cancel();
                                    }
                                }
                            }
                        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 1);

                    }
                    if (event.getPlayer().getInventory().getItemInOffHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9§l火箭弹§r发射器") && !event.getPlayer().getScoreboardTags().contains("reload")) {
                        Damageable damageable = (Damageable) event.getPlayer().getInventory().getItemInOffHand().getItemMeta();

                        event.getPlayer().addScoreboardTag("reload");
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                Damageable damageable1 = (Damageable) event.getPlayer().getInventory().getItemInOffHand().getItemMeta();
                                for (int i = 0; i < 6; i++) {
                                    if (event.getPlayer().isOnline() && !event.getPlayer().getScoreboardTags().contains("FireNow") && event.getPlayer().getInventory().getItemInOffHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9§l火箭弹§r发射器") && damageable1.getDamage() != 0) {
                                        damageable1.setDamage(damageable1.getDamage() - 1);
                                        ItemStack itemInOffHand = event.getPlayer().getInventory().getItemInOffHand();
                                        itemInOffHand.setItemMeta(damageable1);
                                        event.getPlayer().getInventory().setItemInOffHand(itemInOffHand);
                                    } else {
                                        event.getPlayer().removeScoreboardTag("reload");
                                        cancel();
                                    }
                                }
                            }
                        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 1);

                    }
                }

            }
        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 1);
    }
}
