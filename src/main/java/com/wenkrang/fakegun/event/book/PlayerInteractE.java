package com.wenkrang.fakegun.event.book;

import com.wenkrang.fakegun.FakeGun;
import com.wenkrang.fakegun.item.RecipeBookUtil;
import com.wenkrang.lib.SpigotConsoleColors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PlayerInteractE implements Listener {
    @EventHandler
    public static void onOpen(org.bukkit.event.player.PlayerInteractEvent event){
        if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9§lFakeGun§r-寄枪配方")) {

            event.getPlayer().openInventory(RecipeBookUtil.mainPage);
            event.setCancelled(true);
        }
    }
}
