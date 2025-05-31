package com.wenkrang.fakegun.loader;

import com.wenkrang.fakegun.FakeGun;
import com.wenkrang.fakegun.Gun;
import com.wenkrang.fakegun.item.ItemSystem;
import com.wenkrang.lib.SpigotConsoleColors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class LoadGun {
    public static void load() {
        if (true) {
            ItemStack itemStack = new ItemStack(Material.CROSSBOW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l突击§r步枪");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "正儿八经的突击步枪，速度不是\"很快\"");
            lore.add(SpigotConsoleColors.WHITE + "需要子弹  [小口径子弹]");
            lore.add(" ");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "副手弹药 + 主手枪  " + SpigotConsoleColors.RESET + " 换弹");
            itemMeta.setLore(lore);
            // 获取弩的元数据
            CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;

// 设置弩的属性
            crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW)); // 设置弩的射出物为烟花火箭

// 应用元数据
            itemStack.setItemMeta(crossbowMeta);

            Gun minimachinegun = new Gun("gun_0", itemStack);
            minimachinegun.setAtBack(1);
            minimachinegun.setKeeps(0);
            minimachinegun.setTicks(5);
            minimachinegun.setDamage(5);
            minimachinegun.setReloadtime(6);
            minimachinegun.setSpeed(1);
            minimachinegun.setGuneed(1);
            minimachinegun.load();
            minimachinegun.setGunMaterial(Material.IRON_INGOT);
            minimachinegun.loadRecipe();
        }
        if (true) {
            ItemStack itemStack = new ItemStack(Material.CROSSBOW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l全自动§r轻机枪");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "你爷用的老年机枪，速度不是\"很快\"");
            lore.add(SpigotConsoleColors.WHITE + "需要子弹  [小口径子弹]");
            lore.add(" ");

            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "副手弹药 + 主手枪  " + SpigotConsoleColors.RESET + " 换弹");
            itemMeta.setLore(lore);
            // 获取弩的元数据
            CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;

// 设置弩的属性
            crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW)); // 设置弩的射出物为烟花火箭

// 应用元数据
            itemStack.setItemMeta(crossbowMeta);

            Gun minimachinegun = new Gun("gun_1", itemStack);
            minimachinegun.setAtBack(2);
            minimachinegun.setKeeps(0);
            minimachinegun.setTicks(15);
            minimachinegun.setReloadtime(3);
            minimachinegun.setGunMaterial(Material.GOLD_INGOT);
            minimachinegun.setGuneed(1);
            minimachinegun.setSpeed(4);
            minimachinegun.setDamage(7);
            minimachinegun.load();
            minimachinegun.loadRecipe();
        }
        if (true) {
            ItemStack itemStack = new ItemStack(Material.CROSSBOW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l步§r枪");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "这绝对不是什么燧发枪，也绝对不会夹手指（");
            lore.add(SpigotConsoleColors.WHITE + "需要子弹  [大口径子弹]");
            lore.add(" ");

            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "副手弹药 + 主手枪  " + SpigotConsoleColors.RESET + " 换弹");

            itemMeta.setLore(lore);
            // 获取弩的元数据
            CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;

// 设置弩的属性
            crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW)); // 设置弩的射出物为烟花火箭

// 应用元数据
            itemStack.setItemMeta(crossbowMeta);

            Gun minimachinegun = new Gun("gun_2", itemStack);
            minimachinegun.setAtBack(3);
            minimachinegun.setKeeps(20);
            minimachinegun.setTicks(15);
            minimachinegun.setGuneed(2);
            minimachinegun.setReloadtime(3);
            minimachinegun.setSpeed(6);
            minimachinegun.setDamage(12);
            minimachinegun.setGunMaterial(Material.DIAMOND);
            minimachinegun.load();
            minimachinegun.loadRecipe();
        }
        if (true) {
            ItemStack itemStack = new ItemStack(Material.CROSSBOW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l重型§r步枪");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "也 许 是 把 大 狙，一枪一个XPY（");
            lore.add(SpigotConsoleColors.WHITE + "需要子弹  [大口径子弹]");

            lore.add(" ");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "副手弹药 + 主手枪  " + SpigotConsoleColors.RESET + " 换弹");
            itemMeta.setLore(lore);
            // 获取弩的元数据
            CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;

// 设置弩的属性
            crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW)); // 设置弩的射出物为烟花火箭

// 应用元数据
            itemStack.setItemMeta(crossbowMeta);

            Gun minimachinegun = new Gun("gun_3", itemStack);
            minimachinegun.setAtBack(4);
            minimachinegun.setKeeps(40);
            minimachinegun.setGunMaterial(Material.TNT_MINECART);
            minimachinegun.setTicks(93);
            minimachinegun.setReloadtime(4);
            minimachinegun.setSpeed(3);
            minimachinegun.setGuneed(2);
            minimachinegun.setDamage(17);
            minimachinegun.load();
            minimachinegun.loadRecipe();
        }
        if (true) {
            ItemStack itemStack = new ItemStack(Material.CROSSBOW);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l火箭弹§r发射器");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "普普通通的火箭筒，可以发射火箭弹，造成");
            lore.add(SpigotConsoleColors.WHITE + "大爆炸但愿你喜欢吧");
            lore.add(SpigotConsoleColors.WHITE + "需要子弹  [火箭弹]");
            lore.add(" ");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "副手枪 + 主手弹药  " + SpigotConsoleColors.RESET + " 换弹");
            itemMeta.setLore(lore);
            // 获取弩的元数据
            CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;

// 设置弩的属性
            crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW));

// 应用元数据
            itemStack.setItemMeta(crossbowMeta);

            Gun fireGun = new Gun("fire_gun", itemStack);
            fireGun.setAtBack(0);
            fireGun.setKeeps(0);
            fireGun.setTicks(0);
            fireGun.setGunMaterial(Material.FIRE_CHARGE);
            fireGun.setReloadtime(0);
            fireGun.setSpeed(0);
            fireGun.setGuneed(1);
            fireGun.setDamage(0);
            fireGun.load();
            fireGun.loadRecipe();
        }

        if (true) {
            NamespacedKey namespacedKey = new NamespacedKey(FakeGun.PLUGIN, "SBullet");
            ItemStack itemStack = new ItemStack(Material.IRON_NUGGET, 64);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l小口径§r子弹");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "这是小口径子弹，适用于射速较快的枪械");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);


            ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack)
                    .shape("   ", "rt ", "   ")
                    .setIngredient('r', new RecipeChoice.ExactChoice(new ItemStack(Material.GUNPOWDER)))
                    .setIngredient('t', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
            ItemSystem.FakeGunItem fakeGunItem = new ItemSystem.FakeGunItem("SBUllet", itemStack);
            fakeGunItem.setRecipe(shapedRecipe);
            fakeGunItem.load();
            fakeGunItem.loadRecipe();
        }
        if (true) {
            NamespacedKey namespacedKey = new NamespacedKey(FakeGun.PLUGIN, "BBullet");
            ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET, 64);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l大口径§r子弹");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "这是大口径子弹，适用于威力较大的枪械");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack)
                    .shape("   ", "rt ", "   ")
                    .setIngredient('r', new RecipeChoice.ExactChoice(new ItemStack(Material.GUNPOWDER)))
                    .setIngredient('t', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
            ItemSystem.FakeGunItem fakeGunItem = new ItemSystem.FakeGunItem("BBUllet", itemStack);
            fakeGunItem.setRecipe(shapedRecipe);
            fakeGunItem.load();
            fakeGunItem.loadRecipe();
        }if (true) {
            NamespacedKey namespacedKey = new NamespacedKey(FakeGun.PLUGIN, "FBullet");
            ItemStack itemStack = new ItemStack(Material.FIRE_CHARGE);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l火箭§r弹");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "这是火箭弹，适用于火箭弹发射器");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack)
                    .shape("   ", "rt ", "   ")
                    .setIngredient('r', new RecipeChoice.ExactChoice(new ItemStack(Material.GUNPOWDER)))
                    .setIngredient('t', new RecipeChoice.ExactChoice(new ItemStack(Material.FIRE_CHARGE)));
            ItemSystem.FakeGunItem fakeGunItem = new ItemSystem.FakeGunItem("FBUllet", itemStack);
            fakeGunItem.setRecipe(shapedRecipe);
            fakeGunItem.load();
            fakeGunItem.loadRecipe();
        }

        if (true) {
            NamespacedKey namespacedKey = new NamespacedKey(FakeGun.PLUGIN, "SMBUllet");
            ItemStack itemStack = new ItemStack(Material.FIREWORK_STAR);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("§9§l烟雾§r弹");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(SpigotConsoleColors.WHITE + "你 看 得 见 吗？，一阵烟雾蒙蔽了你的双眼（");
            lore.add(" ");
            lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "投掷");
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack)
                    .shape("   ", "rt ", "   ")
                    .setIngredient('r', new RecipeChoice.ExactChoice(new ItemStack(Material.GUNPOWDER)))
                    .setIngredient('t', new RecipeChoice.ExactChoice(new ItemStack(Material.FIRE_CHARGE)));
            ItemSystem.FakeGunItem fakeGunItem = new ItemSystem.FakeGunItem("SMBUllet", itemStack);
            fakeGunItem.setRecipe(shapedRecipe);
            fakeGunItem.load();
            fakeGunItem.loadRecipe();
        }
    }
}
