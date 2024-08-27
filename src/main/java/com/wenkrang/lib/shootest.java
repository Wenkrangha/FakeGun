package com.wenkrang.lib;

import com.wenkrang.fakegun.FakeGun;
import com.wenkrang.fakegun.gun;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

import static com.wenkrang.fakegun.event.fire.applyRecoil;

public class shootest {
    public static void run (Player player, gun gun) {
        Item item = player.getWorld().spawn(player.getEyeLocation(), Item.class);

        ItemStack itemStack2 = new ItemStack(Material.IRON_NUGGET);

        item.setItemStack(itemStack2);

        item.setPickupDelay(1145141919);

        // 设置箭的速度（力度），这里假设是常规速度的两倍
        item.setVelocity(player.getLocation().getDirection().multiply(5));
        new BukkitRunnable() {

            @Override
            public void run() {
                if (true) {
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
                            damageable.damage(new Random().nextInt(2) + gun.getDamage(), player);
                            applyRecoil((LivingEntity) damageable, 0.3, 0.1);
                            Location location1 = damageable.getLocation();
                            location1.setY(location1.getBlockY() + 1);
                            damageable.getWorld().spawnParticle(Particle.BLOCK_CRACK, location1, gun.getDamage() * 12, Bukkit.createBlockData(Material.REDSTONE_BLOCK));
                            damageable.getWorld().playSound(damageable.getLocation(), Sound.ENTITY_ARROW_HIT, 1.0F, 1.0F);

                            item.remove();
                            cancel();
                        }
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
