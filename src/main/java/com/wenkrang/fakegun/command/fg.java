package com.wenkrang.fakegun.command;

import com.wenkrang.fakegun.FakeGun;
import com.wenkrang.fakegun.gun;
import com.wenkrang.lib.SpigotConsoleColors;
import com.wenkrang.lib.shootest;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;


import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

public class fg implements CommandExecutor {


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

        }

        return true;
    }
}
