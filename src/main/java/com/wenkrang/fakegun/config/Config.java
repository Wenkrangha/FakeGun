package com.wenkrang.fakegun.config;

import com.wenkrang.fakegun.FakeGun;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.AbstractMap;
import java.util.Set;

public class Config extends AbstractMap<String, Object> {
    private Config() {
        FakeGun.PLUGIN.saveDefaultConfig();
        FakeGun.PLUGIN.reloadConfig();
        config = FakeGun.PLUGIN.getConfig();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return config.getValues(true).entrySet();
    }

    @Override
    public Object get(Object key) {
        return config.get(key.toString()) != null ? config.get(key.toString()) : super.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        config.set(key, value);
        FakeGun.PLUGIN.saveConfig();
        return value;
    }

    private final FileConfiguration config;

    public static Config INSTANCE = new Config();

}
