package com.wenkrang.fakegun.event.book;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;

public class FirstSendBookE implements Listener {
    @EventHandler
    public static void OnFirstSendBook(PlayerJoinEvent event) {
        //检查玩家列表文件夹是否存在
        File PlayerListDir = new File("./plugins/FakeGun/PlayerList/");
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

        if (PlayerListDir.exists()) {
            File PlayerFile = new File("./plugins/FakeGun/PlayerList/" + event.getPlayer().getName());
            //检查玩家文件是否存在
            if (!PlayerFile.exists()) {
                try {
                    //创造文件
                    PlayerFile.createNewFile();
                    event.getPlayer().getInventory().addItem(itemStack0);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            //创造文件夹
            PlayerListDir.mkdirs();
            event.getPlayer().getInventory().addItem(itemStack0);
        }
    }
}
