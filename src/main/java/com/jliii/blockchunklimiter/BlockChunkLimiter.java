package com.jliii.blockchunklimiter;

import com.jliii.blockchunklimiter.listeners.BlockPlace;
import com.jliii.blockchunklimiter.listeners.ChunkLoad;
import com.jliii.blockchunklimiter.managers.BlockManager;
import com.jliii.blockchunklimiter.managers.ConfigManager;
import com.jliii.blockchunklimiter.utils.PlayerNotifier;
import com.jliii.blockchunklimiter.utils.TaskScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockChunkLimiter extends JavaPlugin {

    private ConfigManager configManager;
    private BlockManager blockManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        configManager = new ConfigManager(this);
        blockManager = new BlockManager();
        blockManager.setBlockLimits(configManager.getBlockLimits());

        PlayerNotifier playerNotifier = new PlayerNotifier(new TaskScheduler(this));

        if (configManager.shouldCleanOnChunkLoad() || configManager.shouldNotifyOnChunkLoad()) {
            Bukkit.getPluginManager().registerEvents(new ChunkLoad(blockManager, playerNotifier), this);
        }
        Bukkit.getPluginManager().registerEvents(new BlockPlace(blockManager), this);
        registerCommands();
    }

    private void registerCommands() {
//        Objects.requireNonNull(getServer().getPluginCommand("spawnerlist")).setExecutor(new ListCommand(spawnerMaterial, blockManager));
//        Objects.requireNonNull(getServer().getPluginCommand("spawnerdelete")).setExecutor(new DeleteCommand(spawnerMaterial, blockManager));
    }

}