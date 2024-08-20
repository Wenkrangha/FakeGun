package com.wenkrang.fakegun;

import com.wenkrang.fakegun.command.fg;
import com.wenkrang.fakegun.event.ArrowStopDetector;
import com.wenkrang.fakegun.event.PlayerItemHeld;
import com.wenkrang.fakegun.event.PlayerJoin;
import com.wenkrang.fakegun.event.fire;
import com.wenkrang.fakegun.loader.loadgun;
import com.wenkrang.lib.Loader;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class FakeGun extends JavaPlugin {
    public static ArrayList<gun> Guns = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new fire(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemHeld(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new ArrowStopDetector(), this);
        this.getCommand("fg").setExecutor(new fg());
        loadgun.load();
        Loader.run();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
