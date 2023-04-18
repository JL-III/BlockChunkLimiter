package com.jliii.blockchunklimiter;

import com.jliii.blockchunklimiter.commands.DeleteCommand;
import com.jliii.blockchunklimiter.commands.ListCommand;
import com.jliii.blockchunklimiter.listeners.BlockPlace;
import com.jliii.blockchunklimiter.listeners.ChunkLoad;
import com.jliii.blockchunklimiter.utils.PlayerNotifier;
import com.jliii.blockchunklimiter.utils.TaskScheduler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class BlockChunkLimiter extends JavaPlugin {

    public static int maxSpawners;
    public static boolean cleanOnChunkLoad;
    public static boolean notifyOnChunkLoad;
    public static Material spawnerMaterial;

    @Override
    public void onEnable() {
        setSpawnerMaterial();
        TaskScheduler taskScheduler = new TaskScheduler(this);
        FileConfiguration config = getConfig();
        PlayerNotifier playerNotifier = new PlayerNotifier(taskScheduler);


        if (!config.contains("limit")) {
            config.addDefault("limit", 4);
        }
        if (!config.contains("cleanOnChunkLoad")) {
            config.addDefault("cleanOnChunkLoad", false);
        }
        if (!config.contains("notifyOnChunkLoad")) {
            config.addDefault("notifyOnChunkLoad", false);
        }
        config.options().copyDefaults(true);
        saveConfig();

        maxSpawners = config.getInt("limit");
        cleanOnChunkLoad = config.getBoolean("cleanOnChunkLoad");
        notifyOnChunkLoad = config.getBoolean("notifyOnChunkLoad");

        if (cleanOnChunkLoad || notifyOnChunkLoad) {
            Bukkit.getPluginManager().registerEvents(new ChunkLoad(playerNotifier, maxSpawners), this);
        }
        Bukkit.getPluginManager().registerEvents(new BlockPlace(spawnerMaterial, maxSpawners), this);
        Objects.requireNonNull(getServer().getPluginCommand("spawnerlist")).setExecutor(new ListCommand(spawnerMaterial, maxSpawners));
        Objects.requireNonNull(getServer().getPluginCommand("spawnerdelete")).setExecutor(new DeleteCommand(spawnerMaterial, maxSpawners));
    }

    private void setSpawnerMaterial() {
        for (Material material : Material.values()) {
            if (material.toString().endsWith("SPAWNER")) {
                spawnerMaterial = material;
                break;
            }
        }
    }
}
