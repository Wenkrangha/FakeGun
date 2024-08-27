package com.wenkrang.fakegun.event.book;

import com.wenkrang.fakegun.FakeGun;
import com.wenkrang.fakegun.gun;
import com.wenkrang.lib.SpigotConsoleColors;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PlayerClick implements Listener {
    @EventHandler
    public static void onclick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("寄枪配方主页")) {
            if (event.getInventory().getItem(event.getRawSlot()).getItemMeta() != null && gun.getgun(event.getInventory().getItem(event.getRawSlot()).getItemMeta().getDisplayName()) != null) {
                gun getgun = gun.getgun(event.getInventory().getItem(event.getRawSlot()).getItemMeta().getDisplayName());
                Inventory inventory = Bukkit.createInventory(null, 27, "寄枪配方");
                ItemStack itemStack0 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
                ItemMeta itemMeta0 = itemStack0.getItemMeta();
                itemMeta0.setDisplayName(" ");
                itemStack0.setItemMeta(itemMeta0);
                ItemStack itemStack1 = new ItemStack(Material.OAK_SIGN);
                ItemMeta itemMeta1 = itemStack1.getItemMeta();
                itemMeta1.setDisplayName("§9§l返回§r主页");
                itemStack1.setItemMeta(itemMeta1);
                ItemStack itemStack2 = new ItemStack(Material.CONDUIT);
                ItemMeta itemMeta2 = itemStack2.getItemMeta();
                itemStack2.setItemMeta(itemMeta2);
                ItemStack itemStack3 = new ItemStack(Material.BEACON);
                ItemMeta itemMeta3 = itemStack3.getItemMeta();
                itemStack3.setItemMeta(itemMeta3);
                ItemStack itemStack4 = new ItemStack(Material.END_CRYSTAL);
                ItemMeta itemMeta4 = itemStack4.getItemMeta();
                itemStack4.setItemMeta(itemMeta4);
                ItemStack itemStack5 = new ItemStack(Material.CRAFTING_TABLE);
                ItemMeta itemMeta5 = itemStack5.getItemMeta();
                itemMeta5.setDisplayName("§9§l工作台§r合成");
                ArrayList<String> lore5 = new ArrayList<>();
                lore5.add("§7该物品使用§7§l工作台§7合成，请");
                lore5.add("§7按照配方§7§l§n有序合成");
                itemMeta5.setLore(lore5);
                itemStack5.setItemMeta(itemMeta5);
                ItemStack itemStack6 = new ItemStack(Material.IRON_NUGGET);
                //16
                inventory.setItem(0, itemStack0);
                inventory.setItem(1, itemStack1);
                inventory.setItem(2, itemStack0);
                inventory.setItem(6, itemStack0);
                inventory.setItem(7, itemStack0);
                inventory.setItem(8, itemStack0);
                inventory.setItem(10, itemStack5);
                inventory.setItem(22, itemStack6);

                inventory.setItem(12, getgun.getRepice());
                inventory.setItem(13, getgun.getRepice());
                inventory.setItem(14, getgun.getRepice());

                inventory.setItem(16, getgun.getItemStack());

                event.getWhoClicked().openInventory(inventory);

                if (event.isRightClick() && event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) {
                    event.getWhoClicked().getInventory().addItem(getgun.getItemStack());
                    event.getWhoClicked().closeInventory();
                }

            }
            if (event.getInventory().getItem(event.getRawSlot()).getItemMeta() != null && event.getInventory().getItem(event.getRawSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§9§l火箭弹§r发射器")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "寄枪配方");
                ItemStack itemStack0 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
                ItemMeta itemMeta0 = itemStack0.getItemMeta();
                itemMeta0.setDisplayName(" ");
                itemStack0.setItemMeta(itemMeta0);
                ItemStack itemStack1 = new ItemStack(Material.OAK_SIGN);
                ItemMeta itemMeta1 = itemStack1.getItemMeta();
                itemMeta1.setDisplayName("§9§l返回§r主页");
                itemStack1.setItemMeta(itemMeta1);
                ItemStack itemStack2 = new ItemStack(Material.CONDUIT);
                ItemMeta itemMeta2 = itemStack2.getItemMeta();
                itemStack2.setItemMeta(itemMeta2);
                ItemStack itemStack3 = new ItemStack(Material.BEACON);
                ItemMeta itemMeta3 = itemStack3.getItemMeta();
                itemStack3.setItemMeta(itemMeta3);
                ItemStack itemStack4 = new ItemStack(Material.END_CRYSTAL);
                ItemMeta itemMeta4 = itemStack4.getItemMeta();
                itemStack4.setItemMeta(itemMeta4);
                ItemStack itemStack5 = new ItemStack(Material.CRAFTING_TABLE);
                ItemMeta itemMeta5 = itemStack5.getItemMeta();
                itemMeta5.setDisplayName("§9§l工作台§r合成");
                ArrayList<String> lore5 = new ArrayList<>();
                lore5.add("§7该物品使用§7§l工作台§7合成，请");
                lore5.add("§7按照配方§7§l§n有序合成");
                itemMeta5.setLore(lore5);
                itemStack5.setItemMeta(itemMeta5);
                ItemStack itemStack6 = new ItemStack(Material.IRON_NUGGET);
                ItemStack itemStack = new ItemStack(Material.CROSSBOW);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§9§l火箭弹§r发射器");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(SpigotConsoleColors.WHITE + "普普通通的火箭筒，可以发射火箭弹，造成");
                lore.add(SpigotConsoleColors.WHITE + "大爆炸但愿你喜欢吧");
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



                //16
                inventory.setItem(0, itemStack0);
                inventory.setItem(1, itemStack1);
                inventory.setItem(2, itemStack0);
                inventory.setItem(6, itemStack0);
                inventory.setItem(7, itemStack0);
                inventory.setItem(8, itemStack0);
                inventory.setItem(10, itemStack5);
                inventory.setItem(22, itemStack6);

                inventory.setItem(12, new ItemStack(Material.FIRE_CHARGE));
                inventory.setItem(13, new ItemStack(Material.FIRE_CHARGE));
                inventory.setItem(14, new ItemStack(Material.FIRE_CHARGE));

                inventory.setItem(16, itemStack);

                event.getWhoClicked().openInventory(inventory);

                if (event.isRightClick() && event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) {
                    event.getWhoClicked().getInventory().addItem(itemStack);
                    event.getWhoClicked().closeInventory();
                }

            }
            if (event.getInventory().getItem(event.getRawSlot()).getItemMeta() != null && event.getInventory().getItem(event.getRawSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§9§l小口径§r子弹")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "寄枪配方");
                ItemStack itemStack0 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
                ItemMeta itemMeta0 = itemStack0.getItemMeta();
                itemMeta0.setDisplayName(" ");
                itemStack0.setItemMeta(itemMeta0);
                ItemStack itemStack1 = new ItemStack(Material.OAK_SIGN);
                ItemMeta itemMeta1 = itemStack1.getItemMeta();
                itemMeta1.setDisplayName("§9§l返回§r主页");
                itemStack1.setItemMeta(itemMeta1);
                ItemStack itemStack2 = new ItemStack(Material.CONDUIT);
                ItemMeta itemMeta2 = itemStack2.getItemMeta();
                itemStack2.setItemMeta(itemMeta2);
                ItemStack itemStack3 = new ItemStack(Material.BEACON);
                ItemMeta itemMeta3 = itemStack3.getItemMeta();
                itemStack3.setItemMeta(itemMeta3);
                ItemStack itemStack4 = new ItemStack(Material.END_CRYSTAL);
                ItemMeta itemMeta4 = itemStack4.getItemMeta();
                itemStack4.setItemMeta(itemMeta4);
                ItemStack itemStack5 = new ItemStack(Material.CRAFTING_TABLE);
                ItemMeta itemMeta5 = itemStack5.getItemMeta();
                itemMeta5.setDisplayName("§9§l工作台§r合成");
                ArrayList<String> lore5 = new ArrayList<>();
                lore5.add("§7该物品使用§7§l工作台§7合成，请");
                lore5.add("§7按照配方§7§l§n有序合成");
                itemMeta5.setLore(lore5);
                itemStack5.setItemMeta(itemMeta5);
                ItemStack itemStack6 = new ItemStack(Material.IRON_NUGGET);
                ItemStack itemStack = new ItemStack(Material.IRON_NUGGET, 64);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§9§l小口径§r子弹");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(SpigotConsoleColors.WHITE + "这是小口径子弹，适用于射速较快的枪械");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);

                //16
                inventory.setItem(0, itemStack0);
                inventory.setItem(1, itemStack1);
                inventory.setItem(2, itemStack0);
                inventory.setItem(6, itemStack0);
                inventory.setItem(7, itemStack0);
                inventory.setItem(8, itemStack0);
                inventory.setItem(10, itemStack5);


                inventory.setItem(12, new ItemStack(Material.GUNPOWDER));
                inventory.setItem(13, new ItemStack(Material.IRON_INGOT));

                inventory.setItem(16, itemStack);

                event.getWhoClicked().openInventory(inventory);

                if (event.isRightClick() && event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) {
                    event.getWhoClicked().getInventory().addItem(itemStack);
                    event.getWhoClicked().closeInventory();
                }

            }
            if (event.getInventory().getItem(event.getRawSlot()).getItemMeta() != null && event.getInventory().getItem(event.getRawSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§9§l大口径§r子弹")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "寄枪配方");
                ItemStack itemStack0 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
                ItemMeta itemMeta0 = itemStack0.getItemMeta();
                itemMeta0.setDisplayName(" ");
                itemStack0.setItemMeta(itemMeta0);
                ItemStack itemStack1 = new ItemStack(Material.OAK_SIGN);
                ItemMeta itemMeta1 = itemStack1.getItemMeta();
                itemMeta1.setDisplayName("§9§l返回§r主页");
                itemStack1.setItemMeta(itemMeta1);
                ItemStack itemStack2 = new ItemStack(Material.CONDUIT);
                ItemMeta itemMeta2 = itemStack2.getItemMeta();
                itemStack2.setItemMeta(itemMeta2);
                ItemStack itemStack3 = new ItemStack(Material.BEACON);
                ItemMeta itemMeta3 = itemStack3.getItemMeta();
                itemStack3.setItemMeta(itemMeta3);
                ItemStack itemStack4 = new ItemStack(Material.END_CRYSTAL);
                ItemMeta itemMeta4 = itemStack4.getItemMeta();
                itemStack4.setItemMeta(itemMeta4);
                ItemStack itemStack5 = new ItemStack(Material.CRAFTING_TABLE);
                ItemMeta itemMeta5 = itemStack5.getItemMeta();
                itemMeta5.setDisplayName("§9§l工作台§r合成");
                ArrayList<String> lore5 = new ArrayList<>();
                lore5.add("§7该物品使用§7§l工作台§7合成，请");
                lore5.add("§7按照配方§7§l§n有序合成");
                itemMeta5.setLore(lore5);
                itemStack5.setItemMeta(itemMeta5);
                ItemStack itemStack6 = new ItemStack(Material.IRON_NUGGET);
                ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET, 64);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§9§l大口径§r子弹");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(SpigotConsoleColors.WHITE + "这是大口径子弹，适用于威力较大的枪械");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);

                //16
                inventory.setItem(0, itemStack0);
                inventory.setItem(1, itemStack1);
                inventory.setItem(2, itemStack0);
                inventory.setItem(6, itemStack0);
                inventory.setItem(7, itemStack0);
                inventory.setItem(8, itemStack0);
                inventory.setItem(10, itemStack5);


                inventory.setItem(12, new ItemStack(Material.GUNPOWDER));
                inventory.setItem(13, new ItemStack(Material.GOLD_INGOT));

                inventory.setItem(16, itemStack);

                event.getWhoClicked().openInventory(inventory);

                if (event.isRightClick() && event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) {
                    event.getWhoClicked().getInventory().addItem(itemStack);
                    event.getWhoClicked().closeInventory();
                }

            }

            if (event.getInventory().getItem(event.getRawSlot()).getItemMeta() != null && event.getInventory().getItem(event.getRawSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§9§l火箭§r弹")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "寄枪配方");
                ItemStack itemStack0 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
                ItemMeta itemMeta0 = itemStack0.getItemMeta();
                itemMeta0.setDisplayName(" ");
                itemStack0.setItemMeta(itemMeta0);
                ItemStack itemStack1 = new ItemStack(Material.OAK_SIGN);
                ItemMeta itemMeta1 = itemStack1.getItemMeta();
                itemMeta1.setDisplayName("§9§l返回§r主页");
                itemStack1.setItemMeta(itemMeta1);
                ItemStack itemStack2 = new ItemStack(Material.CONDUIT);
                ItemMeta itemMeta2 = itemStack2.getItemMeta();
                itemStack2.setItemMeta(itemMeta2);
                ItemStack itemStack3 = new ItemStack(Material.BEACON);
                ItemMeta itemMeta3 = itemStack3.getItemMeta();
                itemStack3.setItemMeta(itemMeta3);
                ItemStack itemStack4 = new ItemStack(Material.END_CRYSTAL);
                ItemMeta itemMeta4 = itemStack4.getItemMeta();
                itemStack4.setItemMeta(itemMeta4);
                ItemStack itemStack5 = new ItemStack(Material.CRAFTING_TABLE);
                ItemMeta itemMeta5 = itemStack5.getItemMeta();
                itemMeta5.setDisplayName("§9§l工作台§r合成");
                ArrayList<String> lore5 = new ArrayList<>();
                lore5.add("§7该物品使用§7§l工作台§7合成，请");
                lore5.add("§7按照配方§7§l§n有序合成");
                itemMeta5.setLore(lore5);
                itemStack5.setItemMeta(itemMeta5);
                ItemStack itemStack6 = new ItemStack(Material.IRON_NUGGET);
                ItemStack itemStack = new ItemStack(Material.FIRE_CHARGE);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§9§l火箭§r弹");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(SpigotConsoleColors.WHITE + "这是火箭弹，适用于火箭弹发射器");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);

                //16
                inventory.setItem(0, itemStack0);
                inventory.setItem(1, itemStack1);
                inventory.setItem(2, itemStack0);
                inventory.setItem(6, itemStack0);
                inventory.setItem(7, itemStack0);
                inventory.setItem(8, itemStack0);
                inventory.setItem(10, itemStack5);


                inventory.setItem(12, new ItemStack(Material.GUNPOWDER));
                inventory.setItem(13, new ItemStack(Material.FIRE_CHARGE));

                inventory.setItem(16, itemStack);

                event.getWhoClicked().openInventory(inventory);

                if (event.isRightClick() && event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) {
                    event.getWhoClicked().getInventory().addItem(itemStack);
                    event.getWhoClicked().closeInventory();
                }

            }

            if (event.getInventory().getItem(event.getRawSlot()).getItemMeta() != null && event.getInventory().getItem(event.getRawSlot()).getItemMeta().getDisplayName().equalsIgnoreCase("§9§l烟雾§r弹")) {
                Inventory inventory = Bukkit.createInventory(null, 27, "寄枪配方");
                ItemStack itemStack0 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
                ItemMeta itemMeta0 = itemStack0.getItemMeta();
                itemMeta0.setDisplayName(" ");
                itemStack0.setItemMeta(itemMeta0);
                ItemStack itemStack1 = new ItemStack(Material.OAK_SIGN);
                ItemMeta itemMeta1 = itemStack1.getItemMeta();
                itemMeta1.setDisplayName("§9§l返回§r主页");
                itemStack1.setItemMeta(itemMeta1);
                ItemStack itemStack2 = new ItemStack(Material.CONDUIT);
                ItemMeta itemMeta2 = itemStack2.getItemMeta();
                itemStack2.setItemMeta(itemMeta2);
                ItemStack itemStack3 = new ItemStack(Material.BEACON);
                ItemMeta itemMeta3 = itemStack3.getItemMeta();
                itemStack3.setItemMeta(itemMeta3);
                ItemStack itemStack4 = new ItemStack(Material.END_CRYSTAL);
                ItemMeta itemMeta4 = itemStack4.getItemMeta();
                itemStack4.setItemMeta(itemMeta4);
                ItemStack itemStack5 = new ItemStack(Material.CRAFTING_TABLE);
                ItemMeta itemMeta5 = itemStack5.getItemMeta();
                itemMeta5.setDisplayName("§9§l工作台§r合成");
                ArrayList<String> lore5 = new ArrayList<>();
                lore5.add("§7该物品使用§7§l工作台§7合成，请");
                lore5.add("§7按照配方§7§l§n有序合成");
                itemMeta5.setLore(lore5);
                itemStack5.setItemMeta(itemMeta5);
                ItemStack itemStack = new ItemStack(Material.FIREWORK_STAR);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§9§l烟雾§r弹");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(SpigotConsoleColors.WHITE + "你 看 得 见 吗？，一阵烟雾蒙蔽了你的双眼（");
                lore.add(" ");
                lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "投掷");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);

                //16
                inventory.setItem(0, itemStack0);
                inventory.setItem(1, itemStack1);
                inventory.setItem(2, itemStack0);
                inventory.setItem(6, itemStack0);
                inventory.setItem(7, itemStack0);
                inventory.setItem(8, itemStack0);
                inventory.setItem(10, itemStack5);

                inventory.setItem(12, new ItemStack(Material.GUNPOWDER));
                inventory.setItem(13, new ItemStack(Material.FIREWORK_STAR));

                inventory.setItem(16, itemStack);

                event.getWhoClicked().openInventory(inventory);

                if (event.isRightClick() && event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) {
                    event.getWhoClicked().getInventory().addItem(itemStack);
                    event.getWhoClicked().closeInventory();
                }

            }


            //3   4   5
            //12  13  14
            //21  22  23
            event.setCancelled(true);
        }
        if (event.getView().getTitle().equalsIgnoreCase("寄枪配方")) {
            if (event.getRawSlot() == 1) {
                Inventory inventory = Bukkit.createInventory(null, 27, "寄枪配方主页");
                ItemStack itemStack0 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
                ItemMeta itemMeta0 = itemStack0.getItemMeta();
                itemMeta0.setDisplayName(" ");
                itemStack0.setItemMeta(itemMeta0);
                ItemStack itemStack1 = new ItemStack(Material.COMPASS);
                ItemMeta itemMeta1 = itemStack1.getItemMeta();
                itemMeta1.setDisplayName("§9§l寄枪§r配方");
                ArrayList<String> lore1 = new ArrayList<>();
                lore1.add("§7这里是§7§l寄枪配方§7的§7§l主页§7你可以在这里");
                lore1.add("§7查看所有§7§n寄枪§7的配方");
                itemMeta1.setLore(lore1);
                itemStack1.setItemMeta(itemMeta1);
                for (int i = 0; i < FakeGun.Guns.size(); i++) {
                    inventory.setItem(i + 9, FakeGun.Guns.get(i).getItemStack());
                }

                if (true){
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
                    inventory.setItem(13, itemStack);
                }

                if (true) {
                    ItemStack itemStack = new ItemStack(Material.IRON_NUGGET, 64);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName("§9§l小口径§r子弹");
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(SpigotConsoleColors.WHITE + "这是小口径子弹，适用于射速较快的枪械");
                    itemMeta.setLore(lore);
                    itemStack.setItemMeta(itemMeta);

                    inventory.setItem(14, itemStack);
                }
                if (true) {
                    ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET, 64);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName("§9§l大口径§r子弹");
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(SpigotConsoleColors.WHITE + "这是大口径子弹，适用于威力较大的枪械");
                    itemMeta.setLore(lore);
                    itemStack.setItemMeta(itemMeta);

                    inventory.setItem(15, itemStack);
                }
                if (true) {
                    ItemStack itemStack = new ItemStack(Material.FIRE_CHARGE);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName("§9§l火箭§r弹");
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(SpigotConsoleColors.WHITE + "这是火箭弹，适用于火箭弹发射器");
                    itemMeta.setLore(lore);
                    itemStack.setItemMeta(itemMeta);

                    inventory.setItem(16, itemStack);
                }

                if (true) {
                    ItemStack itemStack = new ItemStack(Material.FIREWORK_STAR);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName("§9§l烟雾§r弹");
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(SpigotConsoleColors.WHITE + "你 看 得 见 吗？，一阵烟雾蒙蔽了你的双眼（");
                    lore.add(" ");
                    lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "投掷");
                    itemMeta.setLore(lore);
                    itemStack.setItemMeta(itemMeta);

                    inventory.setItem(17, itemStack);
                }

                inventory.setItem(0, itemStack0);
                inventory.setItem(1, itemStack1);
                inventory.setItem(2, itemStack0);
                inventory.setItem(3, itemStack0);
                inventory.setItem(4, itemStack0);
                inventory.setItem(5, itemStack0);
                inventory.setItem(6, itemStack0);
                inventory.setItem(7, itemStack0);
                inventory.setItem(8, itemStack0);

                event.getView().getPlayer().openInventory(inventory);
                event.setCancelled(true);
            }
            event.setCancelled(true);
        }

    }
}
