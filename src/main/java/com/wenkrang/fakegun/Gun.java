package com.wenkrang.fakegun;

import com.wenkrang.fakegun.item.ItemSystem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Objects;

@lombok.Getter
@lombok.Setter
public class Gun extends ItemSystem.FakeGunItem {
    public Gun(String name, ItemStack item) {
        super(name, item);
    }

    int ticks;
    int keeps;
    int reloadtime;
    float atBack;
    int speed;
    int damage;
    int guneed;
    Material gunMaterial;

    @Override
    public void load() {
        super.load();
        FakeGun.Guns.add(this);
    }

    @Override
    public void loadRecipe() {
        NamespacedKey namespacedKey = new NamespacedKey(FakeGun.PLUGIN, "Gun" + getName());
        ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, getItemStack())
                .shape("   ", "rrr", " i ")
                .setIngredient('r', new RecipeChoice.ExactChoice(new ItemStack(gunMaterial)))
                .setIngredient('i', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_NUGGET)));
        setRecipe(shapedRecipe);
        super.loadRecipe();
    }

    public static Gun getgun(String name) {
        return FakeGun.Guns.stream()
                .filter(gun -> Objects.requireNonNull(gun.getItemStack().getItemMeta())
                        .getDisplayName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
