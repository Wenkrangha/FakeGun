package com.wenkrang.fakegun.event.book;

import com.wenkrang.fakegun.item.RecipeBookUtil;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerClickE implements Listener {
    @EventHandler
    public static void onclick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("寄枪配方主页")) {
            ItemStack itemStack0 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
            ItemMeta itemMeta0 = itemStack0.getItemMeta();
            itemMeta0.setDisplayName(" ");
            itemStack0.setItemMeta(itemMeta0);
            ItemStack itemStack7 = new ItemStack(Material.COMPASS);
            ItemMeta itemMeta7 = itemStack7.getItemMeta();
            Objects.requireNonNull(itemMeta7).setDisplayName("§9§l寄枪§r配方");
            ArrayList<String> lore1 = new ArrayList<>();
            lore1.add("§7这里是§7§l寄枪配方§7的§7§l主页§7你可以在这里");
            lore1.add("§7查看所有§7§n寄枪§7的配方");
            itemMeta7.setLore(lore1);
            itemStack7.setItemMeta(itemMeta7);

            RecipeBookUtil.pages.forEach(page -> {
                if (Objects.equals(event.getCurrentItem(), page.getItem(16))) {
                    event.getWhoClicked().openInventory(page);
                }
            });

            if (event.isRightClick() &&
                    event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE) &&
                    event.getCurrentItem() != null &&
                    event.getSlot() > 8) {
                event.getWhoClicked().getInventory().addItem(event.getCurrentItem());
                event.getWhoClicked().closeInventory();
            }


            //3   4   5
            //12  13  14
            //21  22  23
            event.setCancelled(true);
        }
        if (event.getView().getTitle().equalsIgnoreCase("寄枪配方")) {
            if (event.getRawSlot() == 1) {
                event.getView().getPlayer().openInventory(RecipeBookUtil.mainPage);
                event.setCancelled(true);
            }
            event.setCancelled(true);
        }

    }
}
