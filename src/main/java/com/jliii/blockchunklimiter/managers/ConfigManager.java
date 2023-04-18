package com.jliii.blockchunklimiter.managers;

import com.jliii.blockchunklimiter.BlockChunkLimiter;
import com.jliii.blockchunklimiter.utils.PlayerNotifier;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    private final BlockChunkLimiter plugin;

    public ConfigManager(BlockChunkLimiter plugin) {
        this.plugin = plugin;
        setupConfig();
    }

    private void setupConfig() {
        FileConfiguration config = plugin.getConfig();
        config.addDefault("materials.observer", 4);
        config.addDefault("materials.spawner", 5);
        config.addDefault("cleanOnChunkLoad", false);
        config.addDefault("notifyOnChunkLoad", true);
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public boolean shouldCleanOnChunkLoad() {
        return plugin.getConfig().getBoolean("cleanOnChunkLoad");
    }

    public boolean shouldNotifyOnChunkLoad() {
        return plugin.getConfig().getBoolean("notifyOnChunkLoad");
    }

    public Map<Material, Integer> getBlockLimits() {
        ConfigurationSection materialsSection = plugin.getConfig().getConfigurationSection("materials");
        Map<Material, Integer> blockLimits = new HashMap<>();

        if (materialsSection != null) {
            for (String key : materialsSection.getKeys(false)) {
                Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(key));
                Material material = Material.matchMaterial(key.toUpperCase());
                if (material != null) {
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(material.toString()));
                    blockLimits.put(material, materialsSection.getInt(key));
                }
            }
        }
        return blockLimits;
    }
}


