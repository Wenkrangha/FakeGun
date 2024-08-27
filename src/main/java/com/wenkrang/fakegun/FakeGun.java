package com.wenkrang.fakegun;

import com.wenkrang.fakegun.command.fg;
import com.wenkrang.fakegun.command.fgTabComplete;
import com.wenkrang.fakegun.event.ArrowStopDetector;
import com.wenkrang.fakegun.event.PlayerItemHeld;
import com.wenkrang.fakegun.event.PlayerJoin;
import com.wenkrang.fakegun.event.book.PlayerClick;
import com.wenkrang.fakegun.event.book.PlayerInteract;
import com.wenkrang.fakegun.event.fire;
import com.wenkrang.fakegun.loader.loadgun;
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

import static org.bukkit.Bukkit.getServer;

public final class FakeGun extends JavaPlugin {
    public static ArrayList<gun> Guns = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new fire(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemHeld(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new ArrowStopDetector(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new PlayerClick(), this);
        this.getCommand("fg").setExecutor(new fg());
        this.getCommand("fg").setTabCompleter(new fgTabComplete());
        loadgun.load();
        Loader.run();

        Bukkit.getServer().getConsoleSender().sendMessage("\n" +
                "    ______      __        ______          \n" +
                "   / ____/___ _/ /_____  / ____/_  ______ \n" +
                "  / /_  / __ `/ //_/ _ \\/ / __/ / / / __ \\\n" +
                " / __/ / /_/ / ,< /  __/ /_/ / /_/ / / / /\n" +
                "/_/    \\__,_/_/|_|\\___/\\____/\\__,_/_/ /_/ \n" +
                "                                          \n");
        ConsoleLoger.info("当前服务器版本：" + VersionChecker.getVersion());
        getServer().getConsoleSender().sendMessage("§9§l[*] §r加载完毕,当前版本 : 1.0");

        try {for (int i = 0;i < Guns.size();i++) {
            gun gun = Guns.get(i);
            NamespacedKey namespacedKey = new NamespacedKey(this, "Gun" + String.valueOf(i));
            ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, gun.getItemStack())
                    .shape("   ", "rty", " i ")
                    .setIngredient('r', new RecipeChoice.ExactChoice(gun.getRepice()))
                    .setIngredient('t', new RecipeChoice.ExactChoice(gun.getRepice()))
                    .setIngredient('y', new RecipeChoice.ExactChoice(gun.getRepice()))
                    .setIngredient('i', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_NUGGET)));
            getServer().addRecipe(shapedRecipe);
        }
            if (true) {
                NamespacedKey namespacedKey = new NamespacedKey(this, "FireGun");
                ItemStack itemStack = new ItemStack(Material.CROSSBOW);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§9§l火箭弹§r发射器");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(SpigotConsoleColors.WHITE + "普普通通的火箭筒，可以发射火箭弹，造成");
                lore.add(SpigotConsoleColors.WHITE + "大爆炸但愿你喜欢吧");
                lore.add(SpigotConsoleColors.WHITE + "需要子弹  [火箭弹]");
                lore.add(" ");
                lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
                lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "副手枪 + 主手弹药  " + SpigotConsoleColors.RESET + " 换弹");
                itemMeta.setLore(lore);
                // 获取弩的元数据
                CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;

// 设置弩的属性
                crossbowMeta.addChargedProjectile(new ItemStack(Material.FIREWORK_ROCKET)); // 设置弩的射出物为烟花火箭

// 应用元数据
                itemStack.setItemMeta(crossbowMeta);

                ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack)
                        .shape("   ", "rty", " i ")
                        .setIngredient('r', new RecipeChoice.ExactChoice(new ItemStack(Material.FIRE_CHARGE)))
                        .setIngredient('t', new RecipeChoice.ExactChoice(new ItemStack(Material.FIRE_CHARGE)))
                        .setIngredient('y', new RecipeChoice.ExactChoice(new ItemStack(Material.FIRE_CHARGE)))
                        .setIngredient('i', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_NUGGET)));
                getServer().addRecipe(shapedRecipe);
            }

            if (true) {
                NamespacedKey namespacedKey = new NamespacedKey(this, "SBullet");
                ItemStack itemStack = new ItemStack(Material.IRON_NUGGET, 64);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§9§l小口径§r子弹");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(SpigotConsoleColors.WHITE + "这是小口径子弹，适用于射速较快的枪械");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);


                ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack)
                        .shape("   ", "rt ", "   ")
                        .setIngredient('r', new RecipeChoice.ExactChoice(new ItemStack(Material.GUNPOWDER)))
                        .setIngredient('t', new RecipeChoice.ExactChoice(new ItemStack(Material.IRON_INGOT)));
                getServer().addRecipe(shapedRecipe);
            }
            if (true) {
                NamespacedKey namespacedKey = new NamespacedKey(this, "BBullet");
                ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET, 64);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§9§l大口径§r子弹");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(SpigotConsoleColors.WHITE + "这是大口径子弹，适用于威力较大的枪械");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack)
                        .shape("   ", "rt ", "   ")
                        .setIngredient('r', new RecipeChoice.ExactChoice(new ItemStack(Material.GUNPOWDER)))
                        .setIngredient('t', new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
                getServer().addRecipe(shapedRecipe);
            }if (true) {
                NamespacedKey namespacedKey = new NamespacedKey(this, "FBullet");
                ItemStack itemStack = new ItemStack(Material.FIRE_CHARGE);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§9§l火箭§r弹");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(SpigotConsoleColors.WHITE + "这是火箭弹，适用于火箭弹发射器");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack)
                        .shape("   ", "rt ", "   ")
                        .setIngredient('r', new RecipeChoice.ExactChoice(new ItemStack(Material.GUNPOWDER)))
                        .setIngredient('t', new RecipeChoice.ExactChoice(new ItemStack(Material.FIRE_CHARGE)));
                getServer().addRecipe(shapedRecipe);
            }

            if (true) {
                NamespacedKey namespacedKey = new NamespacedKey(this, "SMBUllet");
                ItemStack itemStack = new ItemStack(Material.FIREWORK_STAR);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§9§l烟雾§r弹");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(SpigotConsoleColors.WHITE + "你 看 得 见 吗？，一阵烟雾蒙蔽了你的双眼（");
                lore.add(" ");
                lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "投掷");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack)
                        .shape("   ", "rt ", "   ")
                        .setIngredient('r', new RecipeChoice.ExactChoice(new ItemStack(Material.GUNPOWDER)))
                        .setIngredient('t', new RecipeChoice.ExactChoice(new ItemStack(Material.FIRE_CHARGE)));
                getServer().addRecipe(shapedRecipe);
            }
        }catch (Exception e) {

        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
