package com.jliii.blockchunklimiter.utils;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskScheduler {

    private final Plugin plugin;

    public TaskScheduler(Plugin plugin) {
        this.plugin = plugin;
    }

    public void scheduleTask(BukkitRunnable runnable, long delay) {
        runnable.runTaskLater(plugin, delay);
    }
}
