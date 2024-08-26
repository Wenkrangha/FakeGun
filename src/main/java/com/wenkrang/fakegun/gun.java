package com.wenkrang.fakegun;

import org.bukkit.inventory.ItemStack;

public class gun {
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

    public ItemStack getRepice() {
        return repice;
    }

    public void setRepice(ItemStack repice) {
        this.repice = repice;
    }

    public int getGuneed() {
        return guneed;
    }

    public void setGuneed(int guneed) {
        this.guneed = guneed;
    }

    public void setAtBack(float atBack) {
        AtBack = atBack;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setKeeps(int keeps) {
        Keeps = keeps;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public void setReloadtime(int reloadtime) {
        this.reloadtime = reloadtime;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getName() {
        return name;
    }

    public float getAtBack() {
        return AtBack;
    }

    public int getKeeps() {
        return Keeps;
    }

    public int getReloadtime() {
        return reloadtime;
    }

    public int getTicks() {
        return ticks;
    }

    public void load() {
        FakeGun.Guns.add(this);
    }

    public static gun getgun(String name) {
        for (gun agun : FakeGun.Guns) {
            if (agun.getName().equalsIgnoreCase(name)) {
                return agun;
            }
        }
        return null;
    }
}
