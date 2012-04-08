package com.windwarrior.bukkit.PixelWorld;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * @author lennart
 */
public class PixelWorld extends JavaPlugin {
    private static final Logger log = Logger.getLogger("minecraft");
    private PixelWorldConfig conf;
    private PixelWorldGen generator;

    @Override
    public void onEnable() {
        log.info(String.format("[PixelWorldGen] enabling PixelWorldGen version [%s]", this.getDescription().getVersion()));
        conf = new PixelWorldConfig(this.getConfig());
        generator = new PixelWorldGen(conf);
        this.getConfig().options().copyDefaults(true);
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return generator;
    }


}
