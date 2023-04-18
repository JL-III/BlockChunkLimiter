package com.jliii.blockchunklimiter.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    private final Material spawnerMaterial;
    private final int spawnerLimit;

    public BlockPlace(Material spawnerMaterial, int spawnerLimit) {
        this.spawnerMaterial = spawnerMaterial;
        this.spawnerLimit = spawnerLimit;
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() != spawnerMaterial) {
            return;
        }
        Chunk chunk = event.getBlock().getLocation().getChunk();
        int spawnercount = 0;
        for (BlockState block : chunk.getTileEntities()) {
            if (block.getType() == spawnerMaterial) {
                spawnercount++;
                if (spawnercount > spawnerLimit) {
                    event.getPlayer().sendMessage("Too many Spawners in this chunk, " + spawnerLimit + " is the max!");
                    event.setCancelled(true);
                    break;
                }
            }
        }
    }
}
