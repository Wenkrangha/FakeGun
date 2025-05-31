package com.wenkrang.fakegun.event.book;

import com.wenkrang.lib.BookAPI.RecipeBook;
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

        if (PlayerListDir.exists()) {
            File PlayerFile = new File("./plugins/FakeGun/PlayerList/" + event.getPlayer().getName());
            //检查玩家文件是否存在
            if (!PlayerFile.exists()) {
                try {
                    //创造文件
                    PlayerFile.createNewFile();
                    event.getPlayer().getInventory().addItem(RecipeBook.theRecipeBook);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            //创造文件夹
            PlayerListDir.mkdirs();
            event.getPlayer().getInventory().addItem(RecipeBook.theRecipeBook);
        }
    }
}
