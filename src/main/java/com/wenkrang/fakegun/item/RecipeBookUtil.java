package com.wenkrang.fakegun.item;

import com.wenkrang.fakegun.FakeGun;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class RecipeBookUtil {

    public static final List<Inventory> pages = new ArrayList<>();

    public static final Inventory mainPage =
            Bukkit.createInventory(null, 27, "寄枪配方主页");

    public static void load() {
        ItemStack itemStack0 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        ItemMeta itemMeta0 = itemStack0.getItemMeta();
        Objects.requireNonNull(itemMeta0).setDisplayName(" ");
        itemStack0.setItemMeta(itemMeta0);

        ItemStack itemStack1 = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta1 = itemStack1.getItemMeta();
        Objects.requireNonNull(itemMeta1).setDisplayName("§9§l寄枪§r配方");
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add("§7这里是§7§l寄枪配方§7的§7§l主页§7你可以在这里");
        lore1.add("§7查看所有§7§n寄枪§7的配方");
        itemMeta1.setLore(lore1);
        itemStack1.setItemMeta(itemMeta1);

        mainPage.setItem(0, itemStack0);
        mainPage.setItem(1, itemStack1);
        mainPage.setItem(2, itemStack0);
        mainPage.setItem(3, itemStack0);
        mainPage.setItem(4, itemStack0);
        mainPage.setItem(5, itemStack0);
        mainPage.setItem(6, itemStack0);
        mainPage.setItem(7, itemStack0);
        mainPage.setItem(8, itemStack0);

        ItemStack shapedIcon = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta itemMeta5 = shapedIcon.getItemMeta();
        Objects.requireNonNull(itemMeta5).setDisplayName("§9§l工作台§r合成");
        ArrayList<String> lore5 = new ArrayList<>();
        lore5.add("§7该物品使用§7§l工作台§7合成，请");
        lore5.add("§7按照配方§7§l§n有序合成");
        itemMeta5.setLore(lore5);
        shapedIcon.setItemMeta(itemMeta5);

        ItemStack shapelessIcon = new ItemStack(Material.CRAFTING_TABLE);
        itemMeta5 = shapelessIcon.getItemMeta();
        Objects.requireNonNull(itemMeta5).setDisplayName("§9§l工作台§r合成");
        lore5 = new ArrayList<>();
        lore5.add("§7该物品使用§7§l工作台§7合成，请");
        lore5.add("§7将配方中物品摆在工作台上§7§l§n无序合成");
        itemMeta5.setLore(lore5);
        shapelessIcon.setItemMeta(itemMeta5);

        ItemStack itemStack2 = new ItemStack(Material.OAK_SIGN);
        ItemMeta itemMeta2 = itemStack1.getItemMeta();
        itemMeta2.setDisplayName("§9§l返回§r主页");
        itemStack2.setItemMeta(itemMeta2);

        val a = new AtomicInteger(9);

        val b = new byte[]{3, 4, 5, 12, 13, 14, 21, 22, 23};

        ItemSystem.items.values().stream()
                .map(ItemSystem.FakeGunItem::getRecipe)
                .filter(Objects::nonNull)
                // 渲染主页
                .peek(i -> mainPage.setItem(a.getAndIncrement(), i.getResult()))
                .forEach(i -> {
                    List<ItemStack> list;

                    val inv =
                            Bukkit.createInventory(null, 27, "寄枪配方");

                    inv.setItem(0, itemStack0);
                    inv.setItem(1, itemStack2);
                    inv.setItem(2, itemStack0);
                    inv.setItem(6, itemStack0);
                    inv.setItem(7, itemStack0);
                    inv.setItem(8, itemStack0);

                    if (i instanceof ShapedRecipe) {
                        list = items((ShapedRecipe) i);
                        inv.setItem(10, shapedIcon);
                    } else if (i instanceof ShapelessRecipe) {
                        list = ((ShapelessRecipe) i).getIngredientList();
                        inv.setItem(10, shapelessIcon);
                    } else {
                        throw new UnsupportedOperationException("todo");
                    }

                    for (ItemStack j: list) {
                        inv.setItem(b[list.indexOf(j)], j);
                    }

                    // 手动修复bug
                    FakeGun.Guns.forEach(j -> {
                        if (i.getResult().equals(j.getItemStack())) {
                            val s = new ItemStack(j.getGunMaterial());
                            inv.setItem(12, s);
                            inv.setItem(13, s);
                            inv.setItem(14, s);
                        }
                    });


                    inv.setItem(10, shapedIcon);

                    inv.setItem(16, i.getResult());

                    pages.add(inv);
                });

    }

    private static List<ItemStack> items(ShapedRecipe recipe) {
        List<ItemStack> ret = new ArrayList<>();

        for (String i: recipe.getShape()) {
            for (char j: i.toCharArray()) {
                ret.add(j == ' ' ? new ItemStack(Material.AIR) : recipe.getIngredientMap().get(j));
            }
        }

        return ret;
    }
}
