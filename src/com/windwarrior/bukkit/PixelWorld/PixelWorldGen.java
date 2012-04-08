/**
* Rechtstreekse kopie van de flatgen van @GuntherDW
* (die gpl is)
**/


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.windwarrior.bukkit.PixelWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

/**
 *
 * @author lennart && GuntherDW
 */
public class PixelWorldGen extends ChunkGenerator{
    private List<BlockPopulator> blockPopulators = new ArrayList<BlockPopulator>();
    private final PixelWorldConfig config;
    
    public PixelWorldGen(PixelWorldConfig config){
        this.config = config;
        if(config.getGridEnabled()){
            blockPopulators.add(new GridPopulator(config));
        }
        
    }
    
    @Override
    public short[][] generateExtBlockSections(World world, Random random, int x, int z, BiomeGrid biomes) {
        return null;
    }

    @Override
    public byte[][] generateBlockSections(World world, Random random, int cx, int cz, BiomeGrid biomes) {
        int maxHeight = world.getMaxHeight();
        byte[][] result = new byte[maxHeight / 16][];
        Map<Integer, Integer> sections = config.getSections();
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
                for(int y = 0; y < maxHeight; y++) {
                    int mat;
                    
                    if((mat = sections.get(y) == null ? 0 : sections.get(y)) != 0){
                        setBlock(result, x, y, z, (byte) mat);
                    }else{
                        setBlock(result, x, y, z, (byte) 0);
                    }                        
                }
            }
        }
        // return null; // Default - returns null, which drives call to generate()
        return result;
    }

    public void setBlock(byte[][] result, int x, int y, int z, byte blkid) {
        if (result[y >> 4] == null) {
            result[y >> 4] = new byte[4096];
        }
        result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;
    }

    public byte getBlock(byte[][] result, int x, int y, int z) {
        if (result[y >> 4] == null) {
            return (byte)0;
        }
        return result[y >> 4][((y & 0xF) << 8) | (z << 4) | x];
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        int x = 0;
        int z = 0;
        int y = world.getHighestBlockYAt(x, z);
        return new Location(world, x, y, z);
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world){
        return blockPopulators;
    }
}