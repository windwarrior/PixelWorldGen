/*
 * Copyright (C) 2012 windwarrior
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

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
        saveConfig();
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return generator;
    }


}
