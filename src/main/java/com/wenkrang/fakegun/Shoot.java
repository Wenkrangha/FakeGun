package com.wenkrang.fakegun;

import com.wenkrang.fakegun.config.Config;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.wenkrang.fakegun.event.FireE.applyRecoil;

public class Shoot {

    private static final float SPEED =
            Float.parseFloat(Config.INSTANCE.get("bullet-speed").toString());

    private static final float DAMAGE_ADDITION =
            Float.parseFloat(Config.INSTANCE.get("bullet-damage-addition").toString());

    private static final float BASE_RECOIL =
            Float.parseFloat(Config.INSTANCE.get("base-recoil").toString());

    private static final float RANDOMNESS =
            Float.parseFloat(Config.INSTANCE.get("recoil-randomness").toString());

    public static void run (Player player, Gun gun) {
        Item item = player.getWorld().spawn(player.getEyeLocation(), Item.class);

        ItemStack itemStack2 = new ItemStack(Material.IRON_NUGGET);

        item.setItemStack(itemStack2);

        item.setPickupDelay(1145141919);

        // 设置箭的速度（力度）
        item.setVelocity(player.getLocation().getDirection().multiply(SPEED));
        new BukkitRunnable() {

            @Override
            public void run() {
                Vector velocity = item.getVelocity();

                // 检查速度是否为零
                if (velocity.equals(new Vector(0, 0, 0))) {
                    // 物品正在移动
                    item.remove();
                    cancel();
                }
                item.setVelocity(player.getLocation().getDirection().multiply(5));
                Location location = item.getLocation();
                location.add(0, 1, 0);
                location.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location, 3, 0.1, 0.1, 0.1, 0);
                List<Entity> nearbyEntities = item.getNearbyEntities(1.2, 1.2, 1.2);
                for (Entity entity : nearbyEntities) {
                    if (entity instanceof Damageable && !entity.equals(player)) {
                        Damageable damageable = (Damageable) entity;
                        damageable.damage((ThreadLocalRandom.current()
                                .nextFloat() * DAMAGE_ADDITION) + gun.getDamage(), player);
                        applyRecoil((LivingEntity) damageable, player.getLocation().getDirection().multiply(-2)
                        );
                        Location location1 = damageable.getLocation();
                        location1.setY(location1.getBlockY() + 1);
                        damageable.getWorld().spawnParticle(Particle.BLOCK_CRACK, location1, gun.getDamage() * 12, Bukkit.createBlockData(Material.REDSTONE_BLOCK));
                        damageable.getWorld().playSound(damageable.getLocation(), Sound.ENTITY_ARROW_HIT, 1.0F, 1.0F);

                        item.remove();
                        cancel();
                    }
                }
            }
        }.runTaskTimer(FakeGun.getPlugin(FakeGun.class), 0, 1);
        new BukkitRunnable() {
            @Override
            public void run() {
                item.remove();
            }
        }.runTaskLater(FakeGun.getPlugin(FakeGun.class), 100);
    }
}
