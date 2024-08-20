package com.wenkrang.fakegun.command;

import com.wenkrang.fakegun.FakeGun;
import com.wenkrang.fakegun.gun;
import com.wenkrang.lib.SpigotConsoleColors;
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
            commandSender.sendMessage(" §4| §7getgun  获取枪");
        } else {
            if (strings[0].equalsIgnoreCase("help")) {
                commandSender.sendMessage("§7[!]  §4寄枪 - FakeGun §7正在运行");
                commandSender.sendMessage(" §4| §7help  帮助列表");
                commandSender.sendMessage(" §4| §7getgun  获取枪");
            }
            if (strings[0].equalsIgnoreCase("getgun")) {
//                if (true) {
//                    ItemStack itemStack = new ItemStack(Material.CROSSBOW);
//                    ItemMeta itemMeta = itemStack.getItemMeta();
//                    itemMeta.setDisplayName("§9§l突击§r步枪");
//                    ArrayList<String> lore = new ArrayList<>();
//                    lore.add(SpigotConsoleColors.WHITE + "你爷用的老年机枪，速度不是\"很快\"");
//                    lore.add(" ");
//                    lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
//                    itemMeta.setLore(lore);
//                    // 获取弩的元数据
//                    CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;
//
//// 设置弩的属性
//                    crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW)); // 设置弩的射出物为烟花火箭
//
//// 应用元数据
//                    itemStack.setItemMeta(crossbowMeta);
//
//                    Player player = (Player) commandSender;
//                    player.getWorld().dropItem(player.getLocation(), itemStack);
//                }
//
//                ItemStack itemStack = new ItemStack(Material.CROSSBOW);
//                ItemMeta itemMeta = itemStack.getItemMeta();
//                itemMeta.setDisplayName(SpigotConsoleColors.DARK_YELLOW + "不自动" + SpigotConsoleColors.BOLD + "防空炮");
//                ArrayList<String> lore = new ArrayList<>();
//                lore.add(SpigotConsoleColors.WHITE + "十防九空");//你改一下（a awa
//                //9
//                lore.add(" ");
//                lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
//                itemMeta.setLore(lore);
//                itemStack.setItemMeta(itemMeta);
//                Player player = (Player) commandSender;
//                player.getWorld().dropItem(player.getLocation(), itemStack);



                for (gun agun : FakeGun.Guns) {
                    Player player = (Player) commandSender;
                    player.getInventory().addItem(agun.getItemStack());
                }
           }

        }

        return true;
    }
}
