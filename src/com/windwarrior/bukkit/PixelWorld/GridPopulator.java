/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.windwarrior.bukkit.PixelWorld;

import java.util.Map;
import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

/**
 *
 * @author lennart
 */
public class GridPopulator extends BlockPopulator {
    private Map<Integer, Integer> splitMap;
    private int height = 0;
    private int maxSize = 0;
    private final int maxSizeInChunks;
    public GridPopulator(PixelWorldConfig conf) {
        splitMap = conf.getGridMap();
        height = conf.getGridHeight();
        maxSize = conf.getMaxGridSize();
        maxSizeInChunks = maxSize / 16;
        
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int xChunk = chunk.getX();
        int zChunk = chunk.getZ();

        if (xChunk % maxSizeInChunks != 0 || zChunk % maxSizeInChunks != 0) {
            return;
        }

        int xBlock = xChunk << 4;
        int zBlock = zChunk << 4;

        generateSection(xBlock, zBlock, maxSize, random, world);
    }

    /**
     * @param xBlock
     * @param yBlock
     * @param size
     * @param random 
     */
    private void generateSection(int xBlock, int zBlock, int size, Random random, World world) {
        int newsize = size / 2;
        if (splitMap.get(newsize) != null && random.nextInt(100) < splitMap.get(newsize)) {
            for (int x = 0; x < 2; x++) {
                for (int z = 0; z < 2; z++) {
                    generateSection(xBlock + x * (newsize), zBlock + z * (newsize), newsize, random, world);
                }
            }
        } else {
            //just generate the section
            for (int x = 0; x < size; x++) {
                for (int z = 0; z < size; z++) {
                    if (isBorder(x, z, size)) {
                        world.getBlockAt(xBlock + x, height, zBlock + z).setType(Material.STONE);
                    } else {
                        world.getBlockAt(xBlock + x, height, zBlock + z).setType(Material.WOOD);
                    }

                }
            }
        }
    }

    public boolean isBorder(int x, int z, int size) {
        return (Math.abs(x) % size == 0
                || Math.abs(z) % size == 0
                || Math.abs(x) % size == size - 1
                || Math.abs(z) % size == size - 1);
    }
}