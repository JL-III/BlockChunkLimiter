package com.jliii.blockchunklimiter.commands;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

//public class ListCommand implements CommandExecutor {
//
//    private final Material spawnerMaterial;
//    private final int maxSpawners;
//
//    public ListCommand(Material spawnerMaterial, int maxSpawners) {
//        this.spawnerMaterial = spawnerMaterial;
//        this.maxSpawners = maxSpawners;
//    }
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//        Bukkit.getWorlds().forEach((w) -> {
//            for (Chunk chunk : w.getLoadedChunks()) {
//                int spawnerCount = 0;
//                for (BlockState block : chunk.getTileEntities()) {
//                    if (block.getType() == spawnerMaterial) {
//                        spawnerCount++;
//                        if (spawnerCount > maxSpawners) {
//                            Location loc = block.getLocation();
//                            sender.sendMessage("Over Limit Spawner: " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " " + Objects.requireNonNull(loc.getWorld()).getName());
//                        }
//                    }
//                }
//            }
//        });
//        return true;
//    }
//}
