package com.wenkrang.fakegun;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class PlayerCheck {
    public static void StartCheck(Player player) {

        if (player.getScoreboardTags().contains("FireNow")) {
            player.removeScoreboardTag("FireNow");
        }
        if (player.getScoreboardTags().contains("reload")) {
            player.removeScoreboardTag("reload");
        }
        if (player.getScoreboardTags().contains("keeping")) {
            player.removeScoreboardTag("keeping");
        }if (player.getScoreboardTags().contains("ClickNow")) {
            player.removeScoreboardTag("ClickNow");
        }

//        File file = new File("./plugins/FakeGun/Players/");
//        file.mkdirs();
//        String[] list = file.list();
//        boolean is = false;
//        for (String s : list) {
//            if (s == player.getName()) {
//                is = true;
//            }
//        }
//
//        if (!is) {
//            new File("./plugins/FakeGun/Players/" + player.getName()).createNewFile();
//            player.chat("/fg guide");
//        }
//
//        player.sendMessage(Arrays.toString(list));
//


        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                }
                if (!player.getScoreboardTags().contains("FireNow")) {

                    if (player.getInventory().getItemInMainHand().getItemMeta() != null && Gun.getgun(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()) != null && !player.getScoreboardTags().contains("reload")) {
                        Damageable damageable = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();

//                        player.addScoreboardTag("reload");
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                if (player.getInventory().getItemInMainHand().getItemMeta() != null) {
                                    Damageable damageable1 = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
                                    if (damageable1.getDamage() == 0) {
                                        player.removeScoreboardTag("reload");
                                        cancel();
                                    }
                                }

                                if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInOffHand().getItemMeta() != null) {
                                    Damageable damageable1 = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
                                    if (player.isOnline() && !player.getScoreboardTags().contains("FireNow") && player.getInventory().getItemInMainHand().getItemMeta() != null && Gun.getgun(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()) != null && damageable1.getDamage() != 0) {

                                        Gun getgun = Gun.getgun(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                                        ItemStack itemInOFFHand = player.getInventory().getItemInOffHand();

                                        if (getgun.getGuneed() == 1) {
                                            if (itemInOFFHand.getItemMeta().getDisplayName().equalsIgnoreCase("§9§l小口径§r子弹")) {

                                                if (itemInOFFHand.getAmount() >= 0) {

                                                    if (new Random().nextInt(100) >= 80) {
                                                        itemInOFFHand.setAmount(itemInOFFHand.getAmount() - 1);
                                                        player.getInventory().setItemInOffHand(itemInOFFHand);
                                                        if (itemInOFFHand.getAmount() == 0) {
                                                            player.getInventory().setItemInOffHand(null);
                                                        }
                                                    }

                                                    for (int i = 0; i < Gun.getgun(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()).getReloadtime(); i++) {
                                                        if (player.isOnline() && !player.getScoreboardTags().contains("FireNow") && player.getInventory().getItemInMainHand().getItemMeta() != null && Gun.getgun(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()) != null && damageable1.getDamage() != 0) {

                                                            damageable1.setDamage(damageable1.getDamage() - 1);
                                                            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
                                                            itemInMainHand.setItemMeta((ItemMeta) damageable1);
                                                            player.getInventory().setItemInMainHand(itemInMainHand);
                                                        } else {

                                                            player.removeScoreboardTag("reload");
                                                            cancel();
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            player.removeScoreboardTag("reload");
                                            cancel();
                                        }
                                        if (getgun.getGuneed() == 2) {
                                            if (itemInOFFHand.getItemMeta().getDisplayName().equalsIgnoreCase("§9§l大口径§r子弹")) {
                                                if (itemInOFFHand.getAmount() >= 0) {
                                                    if (new Random().nextInt(100) >= 80) {
                                                        itemInOFFHand.setAmount(itemInOFFHand.getAmount() - 1);
                                                        player.getInventory().setItemInOffHand(itemInOFFHand);
                                                        if (itemInOFFHand.getAmount() == 0) {
                                                            player.getInventory().setItemInOffHand(null);
                                                        }
                                                    }

                                                    for (int i = 0; i < Gun.getgun(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()).getReloadtime(); i++) {
                                                        if (player.isOnline() && !player.getScoreboardTags().contains("FireNow") && player.getInventory().getItemInMainHand().getItemMeta() != null && Gun.getgun(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()) != null && damageable1.getDamage() != 0) {

                                                            damageable1.setDamage(damageable1.getDamage() - 1);
                                                            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
                                                            itemInMainHand.setItemMeta((ItemMeta) damageable1);
                                                            player.getInventory().setItemInMainHand(itemInMainHand);
                                                        } else {
                                                            player.removeScoreboardTag("reload");
                                                            cancel();
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            player.removeScoreboardTag("reload");
                                            cancel();
                                        }


                                    }

                                }


                            }
                        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 1);

                    }
                    if (player.getInventory().getItemInOffHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9§l火箭弹§r发射器") && !player.getScoreboardTags().contains("reload")) {
                        Damageable damageable = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
                        ItemStack itemInOFFHand = player.getInventory().getItemInOffHand();
                        if (itemInOFFHand.getItemMeta().getDisplayName().equalsIgnoreCase("§9§l火箭§r弹") && itemInOFFHand.getAmount() >= 0 && damageable.getDamage() != 0) {
                            itemInOFFHand.setAmount(itemInOFFHand.getAmount() - 1);
                            if (itemInOFFHand.getAmount() == 0){
                                player.getInventory().setItemInOffHand(null);
                            }
                            player.addScoreboardTag("reload");
                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    Damageable damageable1 = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
                                    for (int i = 0; i < 6; i++) {
                                        if (player.isOnline() && !player.getScoreboardTags().contains("FireNow") && player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9§l火箭弹§r发射器") && damageable1.getDamage() != 0) {
                                            damageable1.setDamage(damageable1.getDamage() - 1);
                                            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
                                            itemInMainHand.setItemMeta((ItemMeta) damageable1);
                                            player.getInventory().setItemInMainHand(itemInMainHand);
                                        } else {
                                            player.removeScoreboardTag("reload");
                                            cancel();
                                        }
                                    }
                                }
                            }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 1);
                        }


                    }
                }

//                if (player.getInventory().getItemInMainHand().getItemMeta() != null && gun.getgun(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()) != null) {
//                    if (player.isSneaking()) {
//                        if (player.getInventory().getItemInOffHand().getType().equals(Material.AIR)) {
//                            player.getInventory().setItemInOffHand(player.getInventory().getItemInMainHand());
//                            player.getInventory().setItemInMainHand(null);
//                        }
//                    }
//                }
//                if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9§l火箭弹§r发射器")) {
//                    if (player.isSneaking()) {
//                        if (player.getInventory().getItemInOffHand().getType().equals(Material.AIR)) {
//                            player.getInventory().setItemInOffHand(player.getInventory().getItemInMainHand());
//                            player.getInventory().setItemInMainHand(null);
//                        }
//                    }
//                }
            }
        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 1);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (player.getScoreboardTags().contains("FireNow")) {
                    player.removeScoreboardTag("FireNow");
                }
            }
        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 10);

    }
}
