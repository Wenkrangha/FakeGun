package com.wenkrang.fakegun.event;

import com.wenkrang.fakegun.FakeGun;
import com.wenkrang.fakegun.gun;
import com.wenkrang.lib.CollisionChecker;
import com.wenkrang.lib.NearestBlockFinder;
import com.wenkrang.lib.SpigotConsoleColors;
import org.bukkit.*;
import org.bukkit.block.Block;
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
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

public class fire implements Listener {

    private static final Random RANDOM = new Random();


    /**
     * 对玩家施加向其视角后方的随机速度以模拟后坐力
     * @param player 目标玩家
     * @param baseRecoil 后坐力的基本力度（可以调整）
     * @param randomness 随机性系数（越大则波动越大）
     */
    public static void applyRecoil(LivingEntity player, double baseRecoil, double randomness) {
        Vector viewDirection = player.getEyeLocation().getDirection(); // 获取玩家视角方向
        Vector baseVector = viewDirection.multiply(-baseRecoil); // 反转方向并乘以后坐力基本力度

        Vector randomVector = new Vector(
                RANDOM.nextGaussian() * randomness,
                RANDOM.nextGaussian() * randomness,
                RANDOM.nextGaussian() * randomness
        );
        Vector totalRecoil = baseVector.add(randomVector);

        player.setVelocity(player.getVelocity().add(totalRecoil)); // 添加后坐力到玩家当前速度
    }
    /**
     * 对玩家施加向其视角后方的随机速度以模拟后坐力
     * @param player 目标玩家
     * @param baseRecoil 后坐力的基本力度（可以调整）
     * @param randomness 随机性系数（越大则波动越大）
     */
    public static void applyRecoilNotY(LivingEntity player, double baseRecoil, double randomness) {
        Vector viewDirection = player.getEyeLocation().getDirection(); // 获取玩家视角方向
        Vector baseVector = viewDirection.multiply(-baseRecoil); // 反转方向并乘以后坐力基本力度

        Vector randomVector = new Vector(
                RANDOM.nextGaussian() * randomness,
                0,
                RANDOM.nextGaussian() * randomness
        );
        Vector totalRecoil = baseVector.add(randomVector);

        player.setVelocity(player.getVelocity().add(totalRecoil)); // 添加后坐力到玩家当前速度
    }

    /**
     * 对玩家视角进行随机抖动
     * @param player 目标玩家
     * @param pitchRandomness 上下视角抖动的最大幅度
     * @param yawRandomness 左右视角抖动的最大幅度
     */
    public static void applyViewShake(Player player, float pitchRandomness, float yawRandomness) {
        float pitchChange = RANDOM.nextInt(3) * pitchRandomness;
//        float yawChange = (RANDOM.nextFloat() * 2 - 1) * yawRandomness;
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
    public static double getDistance(Location loc1, Location loc2) {
        // 获取两者之间的XYZ差值
        double dx = loc1.getX() - loc2.getX();
        double dy = loc1.getY() - loc2.getY();
        double dz = loc1.getZ() - loc2.getZ();

        // 使用三维空间中两点间的欧几里得距离公式计算距离
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
    /**
     * 根据玩家的视角生成弹道粒子效果。
     *
     * @param player 玩家实体
     * @param particleType 粒子类型
     * @param distanceBetweenParticles 每两个粒子间的距离
     * @param maxDistance 最大弹道距离
     */
    public static void generateParticleTrajectory(Player player, Particle particleType, double distanceBetweenParticles, double maxDistance) {
        // 获取玩家当前位置
        Location eyeLocation = player.getEyeLocation();

        // 获取玩家视线方向的单位向量
        Vector direction = eyeLocation.getDirection().normalize();

        // 射程内每隔一定距离生成粒子
        for (double i = distanceBetweenParticles; i <= maxDistance; i += distanceBetweenParticles) {
            Location particleLocation = eyeLocation.clone().add(direction.clone().multiply(i));

            // 在计算的位置生成粒子
            particleLocation.getWorld().spawnParticle(Particle.SMOKE_NORMAL, particleLocation, 3, 0.1, 0.1, 0.1, 0);
        }
    }




    @EventHandler
    public static void OnFire(PlayerInteractEvent event) {
//
//        ItemStack itemStack = new ItemStack(Material.CROSSBOW);
//        ItemMeta itemMeta = itemStack.getItemMeta();
//        itemMeta.setDisplayName("§9§l突击§r步枪");
//        ArrayList<String> lore = new ArrayList<>();
//        lore.add(SpigotConsoleColors.WHITE + "你爷用的老年机枪，速度不是\"很快\"");
//        lore.add(" ");
//        lore.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
//        itemMeta.setLore(lore);
//        // 获取弩的元数据
//        CrossbowMeta crossbowMeta = (CrossbowMeta) itemMeta;
//
//// 设置弩的属性
//        crossbowMeta.addChargedProjectile(new ItemStack(Material.ARROW)); // 设置弩的射出物为烟花火箭
//
//// 应用元数据
//        itemStack.setItemMeta(crossbowMeta);
//
//        ItemStack itemStack1 = new ItemStack(Material.CROSSBOW);
//        ItemMeta itemMeta1 = itemStack1.getItemMeta();
//        itemMeta1.setDisplayName(SpigotConsoleColors.DARK_YELLOW + "不自动" + SpigotConsoleColors.BOLD + "防空炮");
//        ArrayList<String> lore1 = new ArrayList<>();
//        lore1.add(SpigotConsoleColors.WHITE + "十防九空");//你改一下（a awa
//        //9
//        lore1.add(" ");
//        lore1.add(SpigotConsoleColors.DARK_YELLOW + SpigotConsoleColors.BOLD + "右键 " + SpigotConsoleColors.RESET + "开枪");
//        itemMeta1.setLore(lore1);
//        itemStack1.setItemMeta(itemMeta1);

        if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && event.getHand().equals(EquipmentSlot.HAND)) {
            if (event.getPlayer().getInventory().getItemInOffHand().getItemMeta() != null && gun.getgun(event.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName()) != null) {
                event.setCancelled(true);
            }
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && gun.getgun(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()) != null) {

                Set<String> scoreboardTags = event.getPlayer().getScoreboardTags();
                //检查这个Set<String>里面有没有叫FireNow的标签
                if (scoreboardTags.contains("FireNow") && !event.getPlayer().getScoreboardTags().contains("reload")) {
                    event.getPlayer().removeScoreboardTag("FireNow");
                } else {


                    org.bukkit.inventory.meta.Damageable damageable = (org.bukkit.inventory.meta.Damageable) event.getPlayer().getInventory().getItemInMainHand().getItemMeta();

                    if (damageable.getDamage() <= 465 - gun.getgun(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()).getTicks()) {
                        //添加这个标签
                        event.getPlayer().addScoreboardTag("FireNow");
                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {
                                    if (event.getPlayer().getScoreboardTags().contains("keeping")) {
                                        cancel();
                                    }
                                    gun getgun = gun.getgun(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                                    org.bukkit.inventory.meta.Damageable damageable1 = (org.bukkit.inventory.meta.Damageable) event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
                                    if (event.getPlayer().isOnline() && event.getPlayer().getScoreboardTags().contains("FireNow") && gun.getgun(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()) != null && !event.getPlayer().getScoreboardTags().contains("reload") && !event.getPlayer().getScoreboardTags().contains("keeping")) {
                                        if (damageable1.getDamage() <= 465 - gun.getgun(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()).getTicks()) {
                                            damageable1.setDamage(damageable1.getDamage() + getgun.getTicks());
                                            ItemStack itemInMainHand = event.getPlayer().getInventory().getItemInMainHand();
                                            itemInMainHand.setItemMeta(damageable1);
                                            event.getPlayer().getInventory().setItemInMainHand(itemInMainHand);
                                            Player player = event.getPlayer();
                                            player.getWorld().playEffect(player.getLocation(), Effect.ANVIL_LAND, 1, 50);
                                            player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1, 50);
                                            player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 3);
                                            Sound arrowHitSound = Sound.ENTITY_ARROW_HIT;
//                                    applyRecoilNotY(player, 0.01, 0.1);
                                            applyViewShake(player, getgun.getAtBack(), 0F);

                                            new BukkitRunnable() {

                                                @Override
                                                public void run() {
                                                    Item item = player.getWorld().spawn(player.getEyeLocation(), Item.class);

                                                    ItemStack itemStack2 = new ItemStack(Material.IRON_NUGGET);

                                                    item.setItemStack(itemStack2);

                                                    item.setPickupDelay(1145141919);

                                                    // 设置箭的速度（力度），这里假设是常规速度的两倍
                                                    item.setVelocity(player.getLocation().getDirection().multiply(12));

                                                    Item item2 = player.getWorld().spawn(player.getEyeLocation(), Item.class);

                                                    ItemStack itemStack3 = new ItemStack(Material.GOLD_NUGGET);

                                                    item2.setItemStack(itemStack3);

                                                    item2.setPickupDelay(1145141919);

                                                    // 设置箭的速度（力度），这里假设是常规速度的两倍
                                                    item2.setVelocity(player.getLocation().getDirection().multiply(RANDOM.nextFloat(0F, 0.3F)));
                                                    new BukkitRunnable() {

                                                        @Override
                                                        public void run() {
                                                            if (true) {
                                                                Vector velocity = item.getVelocity();

                                                                // 检查速度是否为零
                                                                if (velocity.equals(new Vector(0, 0, 0))) {
                                                                    // 物品正在移动
                                                                    item.remove();
                                                                }
                                                            }
                                                            if (true) {
                                                                Vector velocity = item2.getVelocity();

                                                                // 检查速度是否为零
                                                                if (velocity.equals(new Vector(0, 0, 0))) {
                                                                    // 物品正在移动
                                                                    item2.remove();
                                                                }
                                                            }
                                                        }
                                                    }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 3);
                                                    new BukkitRunnable() {
                                                        @Override
                                                        public void run() {
                                                            item.remove();
                                                            item2.remove();
                                                        }
                                                    }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 100);
                                                }
                                            }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 0);


                                            // 创建一个箭实体
                                            Predicate<Entity> entityFilter = entity -> !(entity.equals(player));
                                            RayTraceResult rayTraceResult = null;
                                            rayTraceResult = player.getWorld().rayTrace(player.getEyeLocation(), player.getEyeLocation().getDirection(), 128, FluidCollisionMode.ALWAYS, true, 1, entityFilter);
                                            generateParticleTrajectory(player, Particle.FLAME, 2, 64);
                                            if (rayTraceResult != null) {

                                                if (rayTraceResult.getHitBlock() != null) {


                                                    Location location5 = calculateParticleLocation(rayTraceResult.getHitBlock().getLocation(), rayTraceResult.getHitBlockFace());

                                                    rayTraceResult.getHitBlock().getWorld().spawnParticle(Particle.BLOCK_CRACK, location5, 10, rayTraceResult.getHitBlock().getBlockData());


                                                } else {
                                                    if (rayTraceResult.getHitEntity() != null && rayTraceResult.getHitEntity() instanceof Damageable) {
                                                        try {


                                                            Damageable hitEntity = (Damageable) rayTraceResult.getHitEntity();
                                                            hitEntity.damage(new Random().nextInt(5) + 2);

                                                            // 获取玩家当前朝向的方向向量


                                                            // 给实体添加后坐力速度
                                                            // 给方向向量添加一个垂直于玩家视角的小幅反向速度
                                                            applyRecoil((LivingEntity) hitEntity, 0.3, 0.1);


                                                            Location location1 = hitEntity.getLocation();
                                                            location1.setY(location1.getBlockY() + 1);

                                                            hitEntity.getWorld().spawnParticle(Particle.BLOCK_CRACK, location1, getgun.getDamage() * 12, Bukkit.createBlockData(Material.REDSTONE_BLOCK));
                                                            hitEntity.getWorld().playSound(hitEntity.getLocation(), arrowHitSound, 1.0F, 1.0F);
                                                        } catch (Exception e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    }
                                                }
                                            }
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
                                            }.runTaskLater(FakeGun.getPlugin(FakeGun.class), getgun.Keeps);
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
                        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, gun.getgun(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName()).getSpeed());
                    }

                }


                event.setCancelled(true);

            }

////防空炮
//            if (event.getPlayer().getInventory().getItemInMainHand().equals(itemStack1)) {
//                if (!event.getPlayer().getScoreboardTags().contains("FireNow")) {
//                    event.getPlayer().addScoreboardTag("FireNow");
//                    int time = 20;
//                    for (int i = 0; i < 5; i++) {
//                        new BukkitRunnable() {
//                            @Override
//                            public void run() {
//                                Player player = event.getPlayer();
//                                player.getWorld().playEffect(player.getLocation(), Effect.ANVIL_LAND, 1, 50);
//                                player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 1, 50);
//                                player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 3);
//                                Arrow arrow = player.getWorld().spawn(player.getEyeLocation(), Arrow.class);
//
//                                // 设置箭的发射源为当前玩家
//                                arrow.setShooter(player);
//
//                                // 设置箭的速度（力度）
//                                arrow.setVelocity(player.getLocation().getDirection().multiply(5));
//
//                                //烟花？e饭
//                                //先试试这个
//                                arrow.addScoreboardTag("AntiAir");
//                                new BukkitRunnable() {
//
//                                    @Override
//                                    public void run() {
//                                        new BukkitRunnable() {
//
//                                            @Override
//                                            public void run() {
//                                                //爆炸引线，超时删除
//                                                arrow.remove();
//                                            }
//                                        }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 0);
//
//                                    }
//                                }.runTaskLaterAsynchronously(FakeGun.getPlugin(FakeGun.class), 100);
//                                new BukkitRunnable() {
//
//                                    @Override
//                                    public void run() {
//                                        new BukkitRunnable() {
//
//                                            @Override
//                                            public void run() {
//                                                List<Entity> nearbyEntities = arrow.getNearbyEntities(5, 5, 5);
//                                                if (!nearbyEntities.isEmpty()) {
//                                                    arrow.getWorld().spawnParticle(Particle.FLAME, arrow.getLocation(), 50);
//                                                    arrow.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, arrow.getLocation(), 60);
//                                                    arrow.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, arrow.getLocation(), 60);
//                                                    arrow.getWorld().createExplosion(arrow.getLocation(), 4F);
//                                                    arrow.remove();
//                                                    //ok上机实验
//                                                    cancel();
//                                                }
//                                            }
//                                        }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 0);
//                                        if (arrow.isDead()) {
//                                            cancel();
//                                        }
//                                    }
//                                }.runTaskTimerAsynchronously(FakeGun.getPlugin(FakeGun.class), 20, 4);
//                            }
//                        }.runTaskLater(FakeGun.getPlugin(FakeGun.class), time);
//                        time += 20;
//                    }
//                } else {
//                    new BukkitRunnable() {
//
//                        @Override
//                        public void run() {
//                            event.getPlayer().removeScoreboardTag("FireNow");
//                        }
//                    }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 100);
//
//                }
//                event.setCancelled(true);
//            }





        }
    }
}
