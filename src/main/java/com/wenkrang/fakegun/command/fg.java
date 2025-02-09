package com.wenkrang.fakegun.command;

import com.google.common.base.Function;
import com.wenkrang.fakegun.FakeGun;
import com.wenkrang.fakegun.gun;
import com.wenkrang.lib.SpigotConsoleColors;
import com.wenkrang.lib.shootest;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;


import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.wenkrang.fakegun.command.data.bar1;
import static com.wenkrang.fakegun.command.data.set1;
import static com.wenkrang.fakegun.command.data.bar2;
import static com.wenkrang.fakegun.command.data.set2;


public class fg implements CommandExecutor {

    /**
     * 给玩家装备指定颜色的皮革帽子
     * @param player 目标玩家
     * @param color 颜色（使用 org.bukkit.Color）
     */
    public static void giveColoredLeatherHat(Player player, Color color) {
        // 创建皮革头盔
        ItemStack hat = new ItemStack(Material.LEATHER_HELMET);

        // 获取并转换物品元数据
        LeatherArmorMeta meta = (LeatherArmorMeta) hat.getItemMeta();


        // 设置颜色
        if (meta != null) {
            meta.setUnbreakable(true);
            meta.setColor(color);
            hat.setItemMeta(meta);
        }
        // 给玩家装备头盔
        player.getInventory().setHelmet(hat);
        player.updateInventory(); // 更新玩家库存
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (strings.length == 0) {
            commandSender.sendMessage("§7[!]  §4寄枪 - FakeGun §7正在运行");
            commandSender.sendMessage(" §4| §7help  帮助列表");
//            commandSender.sendMessage(" §4| §7getgun  获取枪");
            commandSender.sendMessage(" §4| §7guide  获取指南");
        } else {
            if (strings[0].equalsIgnoreCase("help")) {
                commandSender.sendMessage("§7[!]  §4寄枪 - FakeGun §7正在运行");
                commandSender.sendMessage(" §4| §7help  帮助列表");
//                commandSender.sendMessage(" §4| §7getgun  获取枪");
                commandSender.sendMessage(" §4| §7guide  获取指南");
                commandSender.sendMessage(" §4| §7- 创造下，右键配方可以将直接获取物品");
            }
            if  (strings[0].equalsIgnoreCase("guide")) {
                if (commandSender instanceof Player) {
                    Player player = (Player) commandSender;
                    ItemStack itemStack0 = new ItemStack(Material.WRITABLE_BOOK);
                    ItemMeta itemMeta0 = itemStack0.getItemMeta();
                    itemMeta0.setDisplayName("§9§lFakeGun§r-寄枪配方");
                    ArrayList<String> lore0 = new ArrayList<>();
                    lore0.add("§7这是关于寄枪们的配方，§7§m寄枪可以让你D炸天");
                    lore0.add("§7里面似乎蕴含着强大的力量♂");
                    lore0.add("");
                    lore0.add("§6§l右键§6打开");
                    itemMeta0.setLore(lore0);
                    itemStack0.setItemMeta(itemMeta0);
                    player.getInventory().addItem(itemStack0);
                }
            }
//            if (strings[0].equalsIgnoreCase("getgun")) {
//                Player player = (Player) commandSender;
//
////                if (true) {
////                    ItemStack itemStack = new ItemStack(Material.CROSSBOW);
////                    ItemMeta itemMeta = itemStack.getItemMeta();
////                    itemMeta.setDisplayName("§9§l突击§r步枪");
////                    ArrayList<String> lore = new ArrayList<>();
////                    lore.add(SpigotConsoleColors.WHITE + "你爷用的老年机枪，速度不是\"很快\"");
////                    lore.add(" ");
////                    lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
////                    itemMeta.setLore(lore);
////                    // 获取弩的元数据
////                    CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;
////
////// 设置弩的属性
////                    crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW)); // 设置弩的射出物为烟花火箭
////
////// 应用元数据
////                    itemStack.setItemMeta(crossbowMeta);
////
////                    Player player = (Player) commandSender;
////                    player.getWorld().dropItem(player.getLocation(), itemStack);
////                }
////
////                ItemStack itemStack = new ItemStack(Material.CROSSBOW);
////                ItemMeta itemMeta = itemStack.getItemMeta();
////                itemMeta.setDisplayName(SpigotConsoleColors.DARK_YELLOW + "不自动" + SpigotConsoleColors.BOLD + "防空炮");
////                ArrayList<String> lore = new ArrayList<>();
////                lore.add(SpigotConsoleColors.WHITE + "十防九空");//你改一下（a awa
////                //9
////                lore.add(" ");
////                lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
////                itemMeta.setLore(lore);
////                itemStack.setItemMeta(itemMeta);
////                Player player = (Player) commandSender;
////                player.getWorld().dropItem(player.getLocation(), itemStack);
//
//
//
//                for (gun agun : FakeGun.Guns) {
//                    player.getInventory().addItem(agun.getItemStack());
//                }
//                if (true) {
//                    ItemStack itemStack = new ItemStack(Material.CROSSBOW);
//                    ItemMeta itemMeta = itemStack.getItemMeta();
//                    itemMeta.setDisplayName("§9§l火箭弹§r发射器");
//                    ArrayList<String> lore = new ArrayList<>();
//                    lore.add(SpigotConsoleColors.WHITE + "普普通通的火箭筒，可以发射火箭弹，造成");
//                    lore.add(SpigotConsoleColors.WHITE + "大爆炸但愿你喜欢吧");
//                    lore.add(" ");
//                    lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
//                    lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "副手枪 + 主手弹药  " + SpigotConsoleColors.RESET + " 换弹");
//                    itemMeta.setLore(lore);
//                    // 获取弩的元数据
//                    CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;
//
//// 设置弩的属性
//                    crossbowMeta.addChargedProjectile(new ItemStack(Material.FIREWORK_ROCKET)); // 设置弩的射出物为烟花火箭
//
//// 应用元数据
//                    itemStack.setItemMeta(crossbowMeta);
//
//
//
//                    player.getInventory().addItem(itemStack);
//                }
//
//                if (true) {
//                    ItemStack itemStack = new ItemStack(Material.FIREWORK_STAR);
//                    ItemMeta itemMeta = itemStack.getItemMeta();
//                    itemMeta.setDisplayName("§9§l烟雾§r弹");
//                    ArrayList<String> lore = new ArrayList<>();
//                    lore.add(SpigotConsoleColors.WHITE + "你 看 得 见 吗？，一阵烟雾蒙蔽了你的双眼（");
//                    lore.add(" ");
//                    lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "投掷");
//                    itemMeta.setLore(lore);
//                    itemStack.setItemMeta(itemMeta);
//
//                    player.getInventory().addItem(itemStack);
//
//                }
//
//                for (int i = 0;i < 64;i++) {
//                    ItemStack itemStack = new ItemStack(Material.IRON_NUGGET);
//                    ItemMeta itemMeta = itemStack.getItemMeta();
//                    itemMeta.setDisplayName("§9§l小口径§r子弹");
//                    ArrayList<String> lore = new ArrayList<>();
//                    lore.add(SpigotConsoleColors.WHITE + "这是小口径子弹，适用于射速较快的枪械");
//                    itemMeta.setLore(lore);
//                    itemStack.setItemMeta(itemMeta);
//
//                    player.getInventory().addItem(itemStack);
//                }
//                for (int i = 0;i < 64;i++) {
//                    ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET);
//                    ItemMeta itemMeta = itemStack.getItemMeta();
//                    itemMeta.setDisplayName("§9§l大口径§r子弹");
//                    ArrayList<String> lore = new ArrayList<>();
//                    lore.add(SpigotConsoleColors.WHITE + "这是大口径子弹，适用于威力较大的枪械");
//                    itemMeta.setLore(lore);
//                    itemStack.setItemMeta(itemMeta);
//
//                    player.getInventory().addItem(itemStack);
//                }
//                for (int i = 0;i < 64;i++) {
//                    ItemStack itemStack = new ItemStack(Material.FIRE_CHARGE);
//                    ItemMeta itemMeta = itemStack.getItemMeta();
//                    itemMeta.setDisplayName("§9§l火箭§r弹");
//                    ArrayList<String> lore = new ArrayList<>();
//                    lore.add(SpigotConsoleColors.WHITE + "这是火箭弹，适用于火箭弹发射器");
//                    itemMeta.setLore(lore);
//                    itemStack.setItemMeta(itemMeta);
//
//                    player.getInventory().addItem(itemStack);
//                }
//            }
//            if (strings[0].equalsIgnoreCase("red"))
//                if (commandSender instanceof Player)
//                    data.red.add((Player) commandSender);
//
//            if (strings[0].equalsIgnoreCase("blue"))
//                if (commandSender instanceof Player)
//                    data.blue.add((Player) commandSender);
//            Function<String, Villager> stringSheepFunction = (String name) -> {
//                // 假设 location 是生成羊的位置（例如玩家位置）
//                Player player = (Player) commandSender;
//                Location location = player.getLocation();
//                double targetHealth = 200.0; // 指定的目标血量
//
//                // 生成羊并设置血量
//                Villager sheep = (Villager) player.getNearbyEntities(5, 5, 5).stream().filter(i -> i instanceof Villager).findFirst().get();
//                sheep.setAI(false);
//
//                AttributeInstance maxHealth = sheep.getAttribute(Attribute.GENERIC_MAX_HEALTH);
//                // 设置最大血量（使用属性系统，兼容新版）
//                maxHealth.setBaseValue(targetHealth);
//
//                // 设置当前血量
//                sheep.setHealth(targetHealth);
//
//                sheep.setCustomName(name); // 使用§c红色
//                sheep.setCustomNameVisible(true); // 始终显示名称
//                return sheep;
//            };
//            Function<String, Zombie> stringSheepFunction2 = (String name) -> {
//                // 假设 location 是生成羊的位置（例如玩家位置）
//                Player player = (Player) commandSender;
//                Location location = player.getLocation();
//                double targetHealth = 500.0; // 指定的目标血量
//
//                // 生成羊并设置血量
//                Zombie sheep = (Zombie) player.getNearbyEntities(5, 5, 5).stream().filter(i -> i instanceof Zombie).findFirst().get();
//                sheep.setAI(false);
//
//                AttributeInstance maxHealth = sheep.getAttribute(Attribute.GENERIC_MAX_HEALTH);
//                // 设置最大血量（使用属性系统，兼容新版）
//                maxHealth.setBaseValue(targetHealth);
//
//                // 设置当前血量
//                sheep.setHealth(targetHealth);
//
//                sheep.setCustomName(name); // 使用§c红色
//                sheep.setCustomNameVisible(true); // 始终显示名称
//                return sheep;
//            };
//
//            if (strings[0].equalsIgnoreCase("set1")) {
//                set1 = stringSheepFunction.apply("村民");
//                bar1 = Bukkit.createBossBar("村民的血量", BarColor.RED, BarStyle.SEGMENTED_10);
//                new BukkitRunnable() {
//                    @Override
//                    public void run() {
//                        Bukkit.getOnlinePlayers().forEach(i -> {
//                            if (!bar1.getPlayers().contains(i)) bar1.addPlayer(i);
//                        });
//                        bar1.setProgress(set1.getHealth() * 0.005);
//                        if (set1.getHealth() == 0) {
//                            bar1.removeAll();
//                        }
//                    }
//                }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 5);
//            }
//            if (strings[0].equalsIgnoreCase("set2")) {
//                set2 = stringSheepFunction2.apply("僵尸");
//            }
//            if (strings[0].equalsIgnoreCase("part2")) {
//                bar2 = Bukkit.createBossBar("僵尸的血量", BarColor.BLUE, BarStyle.SEGMENTED_10);
//                new BukkitRunnable() {
//                    @Override
//                    public void run() {
//                        Bukkit.getOnlinePlayers().forEach(i -> {
//                            if (!bar2.getPlayers().contains(i)) bar2.addPlayer(i);
//                        });
//                        bar2.setProgress(set2.getHealth() * 0.005);
//                        if (set2.getHealth() == 0) {
//                            bar2.removeAll();
//                        }
//                    }
//                }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 5);
//            }
//            if  (strings[0].equalsIgnoreCase("start")) {
//                if (commandSender instanceof Player) {
//                    new BukkitRunnable() {
//                        @Override
//                        public void run() {
//                            if (set2.isDead()) cancel();
//                            Location location = new Location(set2.getWorld(),0, 100 ,0);
//                            for (int i = 0;i < 5;i++){
//                                location.getWorld().spawn(location, Zombie.class);
//                            }
//                        }
//                    }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0 ,200);
//                }
//            }
        }

        return true;
    }
}
