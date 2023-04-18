package com.jliii.blockchunklimiter.commands;

import com.jliii.blockchunklimiter.managers.BlockManager;
import com.jliii.blockchunklimiter.utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

//public class DeleteCommand implements CommandExecutor {
//
//    private final BlockManager blockManager;
//
//    public DeleteCommand(BlockManager blockManager) {
//        this.blockManager = blockManager;
//    }
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//        Bukkit.getWorlds().forEach((world) -> {
//            for (Chunk chunk : world.getLoadedChunks()) {
//                int spawnercount = 0;
//                for (BlockState block : chunk.getTileEntities()) {
//                    if (blockManager.isLimitedBlockMaterial(block.getType())) {
//                        spawnercount++;
//                        if (spawnercount > blockManager.getBlockLimit(block.getType())) {
//                            sender.sendMessage("Deleting Spawner: " + block.getLocation().getBlockX() + " " + block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ() + " " + block.getLocation().getWorld().getName());
//                            ItemStack drop = new ItemStack(block.getType());
//                            CreatureSpawner existing = (CreatureSpawner) block;
//                            Utilities.setSpawnerMob(drop, existing.getSpawnedType());
//                            ItemMeta itemMeta = drop.getItemMeta();
//                            String displayname = existing.getSpawnedType().name();
//                            itemMeta.setDisplayName(ChatColor.RESET + displayname.substring(0, 1).toUpperCase() + displayname.substring(1).toLowerCase() + " Spawner");
//                            drop.setItemMeta(itemMeta);
//                            world.dropItem(block.getLocation().add(0.5, 0.5, 0.5), drop);
//                            blockManager.removeSpawner(block);
//                        }
//                    }
//                }
//            }
//        });
//        return true;
//    }
//}
