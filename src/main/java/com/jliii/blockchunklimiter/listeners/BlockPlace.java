package com.jliii.blockchunklimiter.listeners;

import com.jliii.blockchunklimiter.managers.BlockManager;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    private final BlockManager blockManager;

    public BlockPlace(BlockManager blockManager) {
        this.blockManager = blockManager;
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Material placedBlockType = event.getBlockPlaced().getType();

        if (!blockManager.isLimitedBlockMaterial(placedBlockType)) {
            return;
        }

        Chunk chunk = event.getBlock().getLocation().getChunk();
        int blockCount = 0;
        int blockLimit = blockManager.getBlockLimit(placedBlockType);

        for (int x = 0; x < 16; x++) {
            for (int y = -64; y < 320; y++) {
                for (int z = 0; z < 16; z++) {
                    Block block = chunk.getBlock(x, y, z);
                    if (block.getType() == placedBlockType) {
                        blockCount++;
                        if (blockCount > blockLimit) {
                            event.getPlayer().sendMessage("Too many " + placedBlockType.toString().toLowerCase() + "s in this chunk, " + blockLimit + " is the max!");
                            event.setCancelled(true);
                            return;
                        }
                    }
                }
            }
        }
    }
}

