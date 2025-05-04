package com.wenkrang.fakegun;

import org.bukkit.inventory.ItemStack;

@lombok.Data
public class Gun {
    public String name;
    public int ticks;
    public int Keeps;
    public int reloadtime;
    public float AtBack;
    public ItemStack itemStack;
    public int speed;
    public int damage;
    public int guneed;
    public ItemStack repice;

    public void load() {
        FakeGun.Guns.add(this);
    }

    public static Gun getgun(String name) {
        for (Gun agun : FakeGun.Guns) {
            if (agun.getName().equalsIgnoreCase(name)) {
                return agun;
            }
        }
        return null;
    }
}
