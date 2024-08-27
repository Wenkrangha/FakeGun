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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
    public static void OnPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) throws IOException {
        if (event.getPlayer().getScoreboardTags().contains("FireNow")) {
            event.getPlayer().removeScoreboardTag("FireNow");
        }
        if (event.getPlayer().getScoreboardTags().contains("reload")) {
            event.getPlayer().removeScoreboardTag("reload");
        }
        if (event.getPlayer().getScoreboardTags().contains("keeping")) {
            event.getPlayer().removeScoreboardTag("keeping");
        }
//
//        File file = new File("./plugins/FakeGun/Players/");
//        file.mkdirs();
//        String[] list = file.list();
//        boolean is = false;
//        for (String s : list) {
//            if (s == event.getPlayer().getName()) {
//                is = true;
//            }
//        }
//
//        if (!is) {
//            new File("./plugins/FakeGun/Players/" + event.getPlayer().getName()).createNewFile();
//            event.getPlayer().chat("/fg guide");
//        }
//
//        event.getPlayer().sendMessage(Arrays.toString(list));
//


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
                                if (event.getPlayer().getInventory().getItemInOffHand().getItemMeta() != null) {
                                    Damageable damageable1 = (Damageable) event.getPlayer().getInventory().getItemInOffHand().getItemMeta();
                                    if (damageable1.getDamage() == 0) {
                                        event.getPlayer().removeScoreboardTag("reload");
                                        cancel();
                                    }
                                }
                                if (event.getPlayer().getInventory().getItemInOffHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {
                                    Damageable damageable1 = (Damageable) event.getPlayer().getInventory().getItemInOffHand().getItemMeta();
                                    if (event.getPlayer().isOnline() && !event.getPlayer().getScoreboardTags().contains("FireNow") && event.getPlayer().getInventory().getItemInOffHand().getItemMeta() != null && gun.getgun(event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName()) != null && damageable1.getDamage() != 0) {
                                        gun getgun = gun.getgun(event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName());
                                        ItemStack itemInOffHand1 = event.getPlayer().getInventory().getItemInOffHand();
                                        ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
                                        if (getgun.getGuneed() == 1) {
                                            if (itemInMainHand.getItemMeta().getDisplayName().equalsIgnoreCase("§9§l小口径§r子弹")) {
                                                if (itemInMainHand.getAmount() >= 0) {
                                                    if (new Random().nextInt(100) >= 80) {
                                                        itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);
                                                        event.getPlayer().getInventory().setItemInMainHand(itemInMainHand);
                                                        if (itemInMainHand.getAmount() == 0) {
                                                            event.getPlayer().getInventory().setItemInMainHand(null);
                                                        }
                                                    }

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
                                            }
                                        } else {
                                            event.getPlayer().removeScoreboardTag("reload");
                                            cancel();
                                        }
                                        if (getgun.getGuneed() == 2) {
                                            if (itemInMainHand.getItemMeta().getDisplayName().equalsIgnoreCase("§9§l大口径§r子弹")) {
                                                if (itemInMainHand.getAmount() >= 0) {
                                                    if (new Random().nextInt(100) >= 80) {
                                                        itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);
                                                        event.getPlayer().getInventory().setItemInMainHand(itemInMainHand);
                                                        if (itemInMainHand.getAmount() == 0) {
                                                            event.getPlayer().getInventory().setItemInMainHand(null);
                                                        }
                                                    }

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
                                            }
                                        } else {
                                            event.getPlayer().removeScoreboardTag("reload");
                                            cancel();
                                        }


                                    }

                                }


                            }
                        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 1);

                    }
                    if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInOffHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9§l火箭弹§r发射器") && !event.getPlayer().getScoreboardTags().contains("reload")) {
                        Damageable damageable = (Damageable) event.getPlayer().getInventory().getItemInOffHand().getItemMeta();
                        ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
                        if (itemInMainHand.getItemMeta().getDisplayName().equalsIgnoreCase("§9§l火箭§r弹") && itemInMainHand.getAmount() >= 0 && damageable.getDamage() != 0) {
                            itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);
                            if (itemInMainHand.getAmount() == 0){
                                event.getPlayer().getInventory().setItemInMainHand(null);
                            }
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

            }
        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 1);
    }
}
