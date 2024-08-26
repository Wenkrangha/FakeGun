package com.wenkrang.fakegun.loader;

import com.wenkrang.fakegun.gun;
import com.wenkrang.lib.SpigotConsoleColors;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class loadgun {
    public static void load() {
        if (true) {
            ItemStack itemStack = new ItemStack(Material.CROSSBOW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l突击§r步枪");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "正儿八经的突击步枪，速度不是\"很快\"");
            lore.add(" ");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "副手枪 + 主手弹药  " + SpigotConsoleColors.RESET + " 换弹");
            itemMeta.setLore(lore);
            // 获取弩的元数据
            CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;

// 设置弩的属性
            crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW)); // 设置弩的射出物为烟花火箭

// 应用元数据
            itemStack.setItemMeta(crossbowMeta);

            gun minimachinegun = new gun();
            minimachinegun.setName("§9§l突击§r步枪");
            minimachinegun.setItemStack(itemStack);
            minimachinegun.setAtBack(1);
            minimachinegun.setKeeps(0);
            minimachinegun.setTicks(5);
            minimachinegun.setDamage(5);
            minimachinegun.setReloadtime(6);
            minimachinegun.setSpeed(1);
            minimachinegun.setGuneed(1);
            minimachinegun.load();
            minimachinegun.setRepice(new ItemStack(Material.IRON_INGOT));
        }
        if (true) {
            ItemStack itemStack = new ItemStack(Material.CROSSBOW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l全自动§r轻机枪");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "你爷用的老年机枪，速度不是\"很快\"");
            lore.add(" ");

            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "副手枪 + 主手弹药  " + SpigotConsoleColors.RESET + " 换弹");
            itemMeta.setLore(lore);
            // 获取弩的元数据
            CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;

// 设置弩的属性
            crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW)); // 设置弩的射出物为烟花火箭

// 应用元数据
            itemStack.setItemMeta(crossbowMeta);

            gun minimachinegun = new gun();
            minimachinegun.setName("§9§l全自动§r轻机枪");
            minimachinegun.setItemStack(itemStack);
            minimachinegun.setAtBack(3);
            minimachinegun.setKeeps(0);
            minimachinegun.setTicks(15);
            minimachinegun.setReloadtime(3);
            minimachinegun.setRepice(new ItemStack(Material.GOLD_INGOT));
            minimachinegun.setGuneed(1);
            minimachinegun.setSpeed(3);
            minimachinegun.setDamage(15);
            minimachinegun.load();
        }
        if (true) {
            ItemStack itemStack = new ItemStack(Material.CROSSBOW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l步§r枪");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "你爷用的老年机枪，速度不是\"很快\"");
            lore.add(" ");

            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "副手枪 + 主手弹药  " + SpigotConsoleColors.RESET + " 换弹");
            itemMeta.setLore(lore);
            // 获取弩的元数据
            CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;

// 设置弩的属性
            crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW)); // 设置弩的射出物为烟花火箭

// 应用元数据
            itemStack.setItemMeta(crossbowMeta);

            gun minimachinegun = new gun();
            minimachinegun.setName("§9§l步§r枪");
            minimachinegun.setItemStack(itemStack);
            minimachinegun.setAtBack(6);
            minimachinegun.setKeeps(20);
            minimachinegun.setTicks(15);
            minimachinegun.setGuneed(2);
            minimachinegun.setReloadtime(3);
            minimachinegun.setSpeed(3);
            minimachinegun.setDamage(15);
            minimachinegun.setRepice(new ItemStack(Material.DIAMOND));
            minimachinegun.load();
        }
        if (true) {
            ItemStack itemStack = new ItemStack(Material.CROSSBOW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l重型§r步枪");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "你爷用的老年机枪，速度不是\"很快\"");

            lore.add(" ");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "副手枪 + 主手弹药  " + SpigotConsoleColors.RESET + " 换弹");
            itemMeta.setLore(lore);
            // 获取弩的元数据
            CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;

// 设置弩的属性
            crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW)); // 设置弩的射出物为烟花火箭

// 应用元数据
            itemStack.setItemMeta(crossbowMeta);

            gun minimachinegun = new gun();
            minimachinegun.setName("§9§l重型§r步枪");
            minimachinegun.setItemStack(itemStack);
            minimachinegun.setAtBack(10);
            minimachinegun.setKeeps(40);
            minimachinegun.setRepice(new ItemStack(Material.TNT_MINECART));
            minimachinegun.setTicks(93);
            minimachinegun.setReloadtime(4);
            minimachinegun.setSpeed(3);
            minimachinegun.setGuneed(2);
            minimachinegun.setDamage(40);
            minimachinegun.load();
        }
    }
}
