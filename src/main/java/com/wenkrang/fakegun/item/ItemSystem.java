package com.wenkrang.fakegun.item;

import com.wenkrang.fakegun.FakeGun;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ItemSystem {

    public static final HashMap<NamespacedKey, FakeGunItem> items = new HashMap<>();

    @lombok.Data
    public static class FakeGunItem {
        public FakeGunItem(/*数字，字母，点，下划线的组合*/String name, ItemStack itemStack) {
            this.name = name;
            this.itemStack = itemStack;
        }

        private String name;
        private ItemStack itemStack;
        private Recipe recipe;

        public void load() {
            items.put(new NamespacedKey(FakeGun.PLUGIN, name), this);
        }

        public void loadRecipe() {
            FakeGun.PLUGIN.getServer().addRecipe(Optional.ofNullable(recipe)
                    .orElseThrow(NullPointerException::new));
        }
    }

}
