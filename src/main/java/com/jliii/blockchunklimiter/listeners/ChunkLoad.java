package com.jliii.blockchunklimiter.listeners;

import com.jliii.blockchunklimiter.managers.BlockManager;
import com.jliii.blockchunklimiter.utils.PlayerNotifier;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.HashMap;
import java.util.Map;

public class ChunkLoad implements Listener {

    private final BlockManager blockManager;
    private final PlayerNotifier playerNotifier;

    public ChunkLoad(BlockManager blockManager, PlayerNotifier playerNotifier) {
        this.blockManager = blockManager;
        this.playerNotifier = playerNotifier;
    }

    // This method is called when a chunk is loaded
    @EventHandler(ignoreCancelled = true)
    public void onChunkLoad(ChunkLoadEvent event) {
        if (event.isNewChunk()) {
            return;
        }

        Chunk chunk = event.getChunk();
        Map<Material, Integer> blockCounts = new HashMap<>();

        for (BlockState block : chunk.getTileEntities()) {
            Material blockType = block.getType();
            if (blockManager.isLimitedBlockMaterial(blockType)) {
                int currentCount = blockCounts.getOrDefault(blockType, 0);
                blockCounts.put(blockType, currentCount + 1);

                int blockLimit = blockManager.getBlockLimit(blockType);
                if (currentCount + 1 > blockLimit) {
                    blockManager.removeBlock(block);
                    playerNotifier.notifyPlayers(chunk.getWorld(), chunk, blockType, blockLimit);
                }
            }
        }
    }
}


