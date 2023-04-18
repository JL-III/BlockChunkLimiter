package com.jliii.blockchunklimiter.listeners;

import com.jliii.blockchunklimiter.managers.SpawnerManager;
import com.jliii.blockchunklimiter.utils.PlayerNotifier;
import org.bukkit.Chunk;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoad implements Listener {

    private final SpawnerManager spawnerManager;
    private final PlayerNotifier playerNotifier;
    private final int maxSpawners;

    public ChunkLoad(PlayerNotifier playerNotifier, int maxSpawners) {
        spawnerManager = new SpawnerManager();
        this.playerNotifier = playerNotifier;
        this.maxSpawners = maxSpawners;
    }

    // This method is called when a chunk is loaded
    @EventHandler(ignoreCancelled = true)
    public void onChunkLoad(ChunkLoadEvent event) {
        if (event.isNewChunk()) {
            return;
        }

        Chunk chunk = event.getChunk();
        int spawnerCount = 0;

        for (BlockState block : chunk.getTileEntities()) {
            if (spawnerManager.isSpawner(block.getType())) {
                spawnerCount++;
                if (spawnerCount > maxSpawners) {
                    spawnerManager.removeSpawner(block);
                    playerNotifier.notifyPlayers(chunk.getWorld(), chunk, maxSpawners);
                }
            }
        }
    }
}

