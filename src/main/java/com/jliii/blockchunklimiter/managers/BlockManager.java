package com.jliii.blockchunklimiter.managers;

import org.bukkit.Material;
import org.bukkit.block.BlockState;

import java.util.Map;

public class BlockManager {

    private Map<Material, Integer> blockLimits;

    public void setBlockLimits(Map<Material, Integer> blockLimits) {
        this.blockLimits = blockLimits;
    }

    public boolean isLimitedBlockMaterial(Material blockType) {
        return blockLimits.containsKey(blockType);
    }

    public int getBlockLimit(Material blockType) {
        return blockLimits.getOrDefault(blockType, 0);
    }

    public void removeBlock(BlockState block) {
        block.setType(Material.AIR);
        block.update(true);
    }
}

