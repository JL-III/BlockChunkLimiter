package com.jliii.blockchunklimiter.utils;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerNotifier {

    private final TaskScheduler taskScheduler;

    public PlayerNotifier(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    public void notifyPlayers(World world, Chunk chunk, int maxSpawners) {
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Location chunkLocation = new Location(world, chunk.getX() * 16, 64, chunk.getZ() * 16);
                for (Entity entity : world.getNearbyEntities(chunkLocation, 100, 100, 100)) {
                    if (entity instanceof Player) {
                        Player player = (Player) entity;
                        player.sendMessage("Too many Spawners in this chunk, " + maxSpawners + " is the max! x:" + chunk.getX() * 16 + " y:64 z:" + chunk.getZ() * 16);
                    }
                }
            }
        };
        taskScheduler.scheduleTask(runnable, 20L);
    }
}
