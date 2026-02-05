package com.wenkrang.fakegun.event;

import com.wenkrang.fakegun.FakeGun;
import com.wenkrang.fakegun.Gun;
import com.wenkrang.fakegun.config.Config;
import com.wenkrang.lib.SpigotConsoleColors;
import com.wenkrang.fakegun.Shoot;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public class FireE implements Listener {

    private static final Random RANDOM = ThreadLocalRandom.current();

    private static final float BASE_RECOIL =
            Float.parseFloat(Config.INSTANCE.get("base-recoil").toString());

    private static final float RANDOMNESS =
            Float.parseFloat(Config.INSTANCE.get("recoil-randomness").toString());


    /**
     * 对玩家施加指定向量的随机速度以模拟后坐力
     * @param livingEntity 目标生物
     * @param direction 指定的向量
     */
    public static void applyRecoil(LivingEntity livingEntity, Vector direction) {
        Vector baseVector = direction.multiply(BASE_RECOIL * (-1)); // 反转方向并乘以后坐力基本力度

        Vector randomVector = new Vector(
                RANDOM.nextGaussian() * RANDOMNESS,
                RANDOM.nextGaussian() * RANDOMNESS,
                RANDOM.nextGaussian() * RANDOMNESS
        );
        Vector totalRecoil = baseVector.add(randomVector);
        livingEntity.setVelocity(livingEntity.getVelocity().add(totalRecoil)); // 添加后坐力到玩家当前速度
    }

    /**
     * 对玩家施加向其视角后方的随机速度以模拟后坐力
     * @param player 目标玩家
     */
    @Deprecated
    public static void applyRecoilNotY(LivingEntity player) {
        Vector viewDirection = player.getEyeLocation().getDirection(); // 获取玩家视角方向
        Vector baseVector = viewDirection.multiply(-BASE_RECOIL); // 反转方向并乘以后坐力基本力度

        Vector randomVector = new Vector(
                RANDOM.nextGaussian() * RANDOMNESS,
                0,
                RANDOM.nextGaussian() * RANDOMNESS
        );
        Vector totalRecoil = baseVector.add(randomVector);

        player.setVelocity(player.getVelocity().add(totalRecoil)); // 添加后坐力到玩家当前速度
    }

    /**
     * 对玩家视角进行随机抖动
     * @param player 目标玩家
     * @param pitchRandomness 上下视角抖动的最大幅度
     */
    public static void applyViewShake(Player player, float pitchRandomness) {
        float pitchChange = RANDOM.nextInt(3) * pitchRandomness;
        float yawChange = 0;
        player.setRotation(player.getLocation().getYaw() + yawChange, player.getLocation().getPitch() - pitchChange);
    }
    public static Location getOffsetForFace(BlockFace face) {
        double dx = 0.0, dy = 0.0, dz = 0.0;
        switch (face) {
            case NORTH_WEST:
                dx = -0.5;
                dz = -0.5;
                break;
            case NORTH_EAST:
                dx = 0.5;
                dz = -0.5;
                break;
            case SOUTH_WEST:
                dx = -0.5;
                dz = 0.5;
                break;
            case SOUTH_EAST:
                dx = 0.5;
                dz = 0.5;
                break;
            // ... 其他斜向面的处理 ...
        }
        return new Location(null, dx, dy, dz); // 返回一个只有偏移量的Location对象
    }
    public static Location calculateParticleLocation(Location blockLocation, BlockFace face) {
        switch (face) {
            case NORTH:
                return blockLocation.clone().add(0.5, 0.5, 0);
            case SOUTH:
                return blockLocation.clone().add(0.5, 0.5, 1);
            case WEST:
                return blockLocation.clone().add(0, 0.5, 0.5);
            case EAST:
                return blockLocation.clone().add(1, 0.5, 0.5);
            case UP:
                return blockLocation.clone().add(0.5, 1, 0.5);
            case DOWN:
                return blockLocation.clone().add(0.5, 0, 0.5);
            default:
                // 对于其他斜向面，需要额外计算偏移量
                // 例如对于NORTH_EAST等方向，可以通过向量运算获得准确位置
                // 这里假设你已经有一个名为getOffsetForFace的函数完成这项工作
                return blockLocation.clone().add(getOffsetForFace(face));
        }
    }

    public static void arrowboom (Arrow arrow, Player player) {
        arrow.getWorld().spawnParticle(Particle.FLAME, arrow.getLocation(), 50);
        arrow.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, arrow.getLocation(), 60);
        arrow.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, arrow.getLocation(), 60);
        arrow.getWorld().createExplosion(arrow.getLocation(), 4F);
        player.removeScoreboardTag("FireNow");
        arrow.remove();
    }
    @EventHandler
    public static void OnFire(PlayerInteractEvent event) {
        int MAX_DURABILITY = 465;
        ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
        if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && event.getHand().equals(EquipmentSlot.HAND)) {
            if (event.getPlayer().getInventory().getItemInOffHand().getItemMeta() != null && Gun.getgun(event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName()) != null) {
                event.setCancelled(true);
            }
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && Gun.getgun(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()) != null) {

                Set<String> scoreboardTags = event.getPlayer().getScoreboardTags();
                //检查这个Set<String>里面有没有叫FireNow的标签
                if (scoreboardTags.contains("FireNow") && !event.getPlayer().getScoreboardTags().contains("reload")) {
                    event.getPlayer().removeScoreboardTag("FireNow");
                } else {


                    org.bukkit.inventory.meta.Damageable damageable = (org.bukkit.inventory.meta.Damageable) itemInMainHand.getItemMeta();

                    if (damageable.getDamage() <= MAX_DURABILITY - Gun.getgun(itemInMainHand.getItemMeta().getDisplayName()).getTicks()) {
                        //添加这个标签
                        event.getPlayer().addScoreboardTag("FireNow");
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {
                                    if (event.getPlayer().getScoreboardTags().contains("keeping")) {
                                        cancel();
                                    }
                                    Gun getgun = Gun.getgun(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                                    org.bukkit.inventory.meta.Damageable damageable1 = (org.bukkit.inventory.meta.Damageable) event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                                    if (event.getPlayer().isOnline() && event.getPlayer().getScoreboardTags().contains("FireNow") && Gun.getgun(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()) != null && !event.getPlayer().getScoreboardTags().contains("reload") && !event.getPlayer().getScoreboardTags().contains("keeping")) {
                                        if (damageable1.getDamage() <= MAX_DURABILITY - Gun.getgun(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()).getTicks()) {
                                            Player player = event.getPlayer();
                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    Shoot.run(player, getgun);
                                                }
                                            }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 0);
                                            damageable1.setDamage(damageable1.getDamage() + getgun.getTicks());
                                            ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
                                            itemInMainHand.setItemMeta((ItemMeta) damageable1);
                                            event.getPlayer().getInventory().setItemInMainHand(itemInMainHand);

                                            player.getWorld().playEffect(player.getLocation(), Effect.ANVIL_LAND, 1, 50);
                                            player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1, 50);
                                            player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 3);
                                            Predicate<Entity> entityFilter = entity -> !(entity.equals(player));
                                            RayTraceResult rayTraceResult;
                                            rayTraceResult = player.getWorld().rayTrace(player.getEyeLocation(), player.getEyeLocation().getDirection(), 128, FluidCollisionMode.ALWAYS, true, 1.2, entityFilter);
                                            if (rayTraceResult != null) {
                                                if (rayTraceResult.getHitBlock() != null) {
                                                    Location location5 = calculateParticleLocation(rayTraceResult.getHitBlock().getLocation(), rayTraceResult.getHitBlockFace());
                                                    rayTraceResult.getHitBlock().getWorld().spawnParticle(Particle.BLOCK_CRACK, location5, 10, rayTraceResult.getHitBlock().getBlockData());
                                                }
                                            }
                                        }
                                        if (!event.getPlayer().isSneaking()) {
                                            applyViewShake(event.getPlayer(), getgun.getAtBack());
                                        }


                                        if (getgun.getKeeps() != 0) {
                                            BossBar bossBar = Bukkit.createBossBar("§9§l正在§r冷却...", BarColor.BLUE, BarStyle.SOLID);
                                            bossBar.setProgress(0);
                                            bossBar.addPlayer(event.getPlayer());
                                            new BukkitRunnable() {
                                                @Override
                                                public void run() {
                                                    if (bossBar.getProgress() + (double) 1 / getgun.getKeeps() >= 1) {
                                                        bossBar.removeAll();
                                                    } else {
                                                        bossBar.setProgress(bossBar.getProgress() + (double) 1 / getgun.getKeeps());
                                                    }
                                                }
                                            }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 1);
                                            event.getPlayer().addScoreboardTag("keeping");
                                            new BukkitRunnable() {
                                                @Override
                                                public void run() {
                                                    event.getPlayer().removeScoreboardTag("keeping");
                                                }
                                            }.runTaskLater(FakeGun.getPlugin(FakeGun.class), getgun.getKeeps());
                                        }
                                    } else {
                                        if (event.getPlayer().getScoreboardTags().contains("reload")) {
                                            event.getPlayer().sendTitle("", "§9§l正在§r装填...");
                                        }
                                        event.getPlayer().removeScoreboardTag("FireNow");
                                        cancel();
                                    }
                                }

                            }
                        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, Gun.getgun(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()).getSpeed());
                    }

                }


                event.setCancelled(true);

            }


            if (itemInMainHand.getItemMeta() != null && itemInMainHand.getItemMeta().getDisplayName().equalsIgnoreCase("§9§l火箭弹§r发射器"))  {
                if (!event.getPlayer().getScoreboardTags().contains("FireNow")) {
                    event.getPlayer().addScoreboardTag("FireNow");

                    org.bukkit.inventory.meta.Damageable damageable = (org.bukkit.inventory.meta.Damageable) itemInMainHand.getItemMeta();
                    if (damageable.getDamage() == 0) {
                        damageable.setDamage(MAX_DURABILITY);
                        itemInMainHand.setItemMeta((ItemMeta) damageable);
                        event.getPlayer().getInventory().setItemInMainHand(itemInMainHand);


                        Player player = event.getPlayer();
                        player.getWorld().playEffect(player.getLocation(), Effect.ANVIL_LAND, 1, 50);
                        player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1, 50);
                        player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 3);
                        Arrow arrow = player.getWorld().spawn(player.getEyeLocation(), Arrow.class);

                        // 设置箭的发射源为当前玩家
                        arrow.setShooter(player);

                        // 设置箭的速度（力度）
                        Vector direction = player.getLocation().getDirection();

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (arrow.isDead()) {
                                    cancel();
                                }
                                arrow.getWorld().spawnParticle(Particle.FLAME, arrow.getLocation(), 3);
                                arrow.setVelocity(direction.multiply(1));
                            }
                        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 3);


                        arrow.addScoreboardTag("AntiAir");
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                //爆炸引线，超时删除
                                if (arrow.isDead()) cancel();
                                arrowboom(arrow, event.getPlayer());
                            }
                        }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 100);

                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        List<Entity> nearbyEntities = arrow.getNearbyEntities(5, 5, 5);
                                        Location LastTimeLocation = arrow.getLocation();
                                        new BukkitRunnable() {

                                            @Override
                                            public void run() {
                                                Location NowLocation = arrow.getLocation();
                                                if (LastTimeLocation.getBlockX() == NowLocation.getBlockX() && LastTimeLocation.getBlockY() == NowLocation.getBlockY() && LastTimeLocation.getBlockZ() == NowLocation.getBlockZ()){
                                                    arrowboom(arrow, event.getPlayer());
                                                }
                                            }
                                        }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 5);
                                        if (!nearbyEntities.isEmpty()) {
                                            arrowboom(arrow, event.getPlayer());
                                        }
                                    }
                                }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 0);
                                if (arrow.isDead()) {
                                    cancel();
                                }
                            }
                        }.runTaskTimerAsynchronously(FakeGun.getPlugin(FakeGun.class), 20, 4);
                    }
                }






                event.setCancelled(true);
            }

            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§9§l烟雾§r弹"))  {
                ItemStack itemStack = new ItemStack(Material.FIREWORK_STAR);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName("§9§l烟雾§r弹");
                ArrayList<String> lore = new ArrayList<>();
                lore.add(SpigotConsoleColors.WHITE + "你 看 得 见 吗？，一阵烟雾蒙蔽了你的双眼（");
                lore.add(" ");
                lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "投掷");
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);

                Item item = event.getPlayer().getWorld().spawn(event.getPlayer().getEyeLocation(), Item.class);

                item.setItemStack(itemStack);

                item.setPickupDelay(1145141919);

                item.setVelocity(event.getPlayer().getLocation().getDirection().multiply(3));

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Location location = item.getLocation();
                        location.add(0, 1, 0);
                        BukkitTask bukkitTask = new BukkitRunnable() {
                            @Override
                            public void run() {
                                item.getWorld().spawnParticle(Particle.FLAME, location, 50);
                                item.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, 60);
                                item.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, location, 60);
                            }
                        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 5);
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                bukkitTask.cancel();
                            }
                        }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 100);


                        item.remove();
                    }
                }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 100);
                itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
                if (itemInMainHand.getAmount() > 0) {
                    itemInMainHand.setAmount(itemInMainHand.getAmount() - 1);
                } else if (itemInMainHand.getAmount() == 0){
                    event.getPlayer().getInventory().setItemInMainHand(null);
                }
                event.setCancelled(true);
            }
        }
    }
}
