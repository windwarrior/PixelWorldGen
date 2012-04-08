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

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lennart
 */
public class PixelWorldConfig {
    private Map<Integer, Integer> materialList = new HashMap<Integer, Integer>();
    private int maxGridSize = 0;
    private int gridHeight = 0;
    private boolean gridEnabled = false;
    private Map<Integer, Integer> gridMap = new HashMap<Integer, Integer>();
    private int gridInsideType = 0;
    private int gridBorderType = 0;

    public PixelWorldConfig(FileConfiguration config) {
        List<Map<?, ?>> mapList = config.getMapList("layers");
        for (Map<?, ?> mapEntry : mapList) {
            int type = (Integer) mapEntry.get("type");
            int miny = (Integer) mapEntry.get("miny");
            int maxy = (Integer) mapEntry.get("maxy");

            for (int i = miny; i <= maxy; i++) {
                materialList.put(i, type);
            }

        }
        maxGridSize = config.getInt("grid.defaultSize");
        gridHeight = config.getInt("grid.height");
        gridEnabled = config.getBoolean(("grid.enabled"));
        List<Map<?, ?>> grids = config.getMapList("grid.othersizes");
        for (Map<?, ?> grid : grids) {
            int size = (Integer) grid.get("size");
            int chance = (Integer) grid.get("chance");
            gridMap.put(size, chance);
        }


    }

    public Map<Integer, Integer> getSections() {
        return materialList;
    }

    public Map<Integer, Integer> getGridMap() {
        return gridMap;
    }

    public int getMaxGridSize() {
        return maxGridSize;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public boolean getGridEnabled() {
        return gridEnabled;
    }

    public int getGridInnerType() {
        return gridInsideType;
    }

    public int getGridBorderType() {
        return gridBorderType;
    }
}
