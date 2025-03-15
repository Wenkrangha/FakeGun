package com.wenkrang.fakegun.command;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;


import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;


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

        }

        return true;
    }
}
