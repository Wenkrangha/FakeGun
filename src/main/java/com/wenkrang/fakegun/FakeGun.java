package com.wenkrang.fakegun;

import com.wenkrang.fakegun.command.fg;
import com.wenkrang.fakegun.command.fgTabComplete;
import com.wenkrang.fakegun.event.*;
import com.wenkrang.fakegun.event.book.FirstSendBookE;
import com.wenkrang.fakegun.event.book.PlayerClickE;
import com.wenkrang.fakegun.event.book.PlayerInteractE;
import com.wenkrang.fakegun.item.RecipeBookUtil;
import com.wenkrang.fakegun.loader.LoadGun;
import com.wenkrang.lib.ConsoleLoger;
import com.wenkrang.lib.Loader;
import com.wenkrang.lib.SpigotConsoleColors;
import com.wenkrang.lib.VersionChecker;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class FakeGun extends JavaPlugin {
    public static ArrayList<Gun> Guns = new ArrayList<>();

    public static JavaPlugin PLUGIN;

    @Override
    public void onEnable() {
        // Plugin startup logic
        PLUGIN = this;

        getServer().getPluginManager().registerEvents(new FireE(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemHeldE(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinE(), this);
        getServer().getPluginManager().registerEvents(new ArrowStopDetectorE(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractE(), this);
        getServer().getPluginManager().registerEvents(new PlayerClickE(), this);
        getServer().getPluginManager().registerEvents(new FirstSendBookE(), this);

        this.getCommand("fg").setExecutor(new fg());
        this.getCommand("fg").setTabCompleter(new fgTabComplete());

        LoadGun.load();
        Loader.run();
        Bukkit.getServer().getConsoleSender().sendMessage("    ______      __        ______          ");
        Bukkit.getServer().getConsoleSender().sendMessage("   / ____/___ _/ /_____  / ____/_  ______ ");
        Bukkit.getServer().getConsoleSender().sendMessage("  / /_  / __ `/ //_/ _ \\/ / __/ / / / __ \\");
        Bukkit.getServer().getConsoleSender().sendMessage(" / __/ / /_/ / ,< /  __/ /_/ / /_/ / / / /");
        Bukkit.getServer().getConsoleSender().sendMessage("/_/    \\__,_/_/|_|\\___/\\____/\\__,_/_/ /_/ ");
        Bukkit.getServer().getConsoleSender().sendMessage("");
        ConsoleLoger.info("当前服务器版本：" + VersionChecker.getVersion());

        try {
            RecipeBookUtil.load();

            if (!getServer().getOnlinePlayers().isEmpty())
                getServer().getOnlinePlayers().forEach(PlayerCheck::StartCheck);
        } catch (Exception ignored) {

        }

        getServer().getConsoleSender().sendMessage("§9§l[*] §r加载完毕,当前版本 : 1.1c");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
