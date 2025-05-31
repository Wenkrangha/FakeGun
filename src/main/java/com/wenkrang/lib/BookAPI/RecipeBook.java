package com.wenkrang.lib.BookAPI;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RecipeBook {
    public static ItemStack theRecipeBook;
    static {
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

        theRecipeBook = itemStack0;
    }
}
