package com.jliii.blockchunklimiter.utils;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerNotifier {

    private final TaskScheduler taskScheduler;

    public PlayerNotifier(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    public void notifyPlayers(World world, Chunk chunk, Material blockType, int blockLimit) {
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                Location chunkLocation = new Location(world, chunk.getX() * 16, 64, chunk.getZ() * 16);
                for (Entity ent : world.getNearbyEntities(chunkLocation, 100, 100, 100)) {
                    if (ent instanceof Player) {
                        Player player = (Player) ent;
                        player.sendMessage("Too many " + blockType.toString() + "s in this chunk, " + blockLimit + " is the max!");
                    }
                }
            }
        };
        taskScheduler.scheduleTask(runnable, 20L);
    }
}
