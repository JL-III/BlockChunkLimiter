package com.jliii.blockchunklimiter.managers;

import org.bukkit.Material;
import org.bukkit.block.BlockState;

public class SpawnerManager {

    public boolean isSpawner(Material blockType) {
        return blockType == Material.SPAWNER;
    }

    public void removeSpawner(BlockState block) {
        // Your spawner removal logic goes here
    }
}
