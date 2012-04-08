/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.windwarrior.bukkit.PixelWorld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author lennart
 */
public class PixelWorldConfig {
    private Map<Integer, Integer> materialList = new HashMap<Integer,Integer>();
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
            
            for(int i = miny; i <= maxy; i++){
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
    
    public Map<Integer, Integer> getSections(){
        return materialList;
    }
    
    public Map<Integer, Integer> getGridMap(){
        return gridMap;
    }
    
    public int getMaxGridSize(){
        return maxGridSize;
    }
    
    public int getGridHeight(){
        return gridHeight;
    }
    
    public boolean getGridEnabled(){
        return gridEnabled;
    }
    
    public int getGridInnerType(){
        return gridInsideType;
    }
    
    public int getGridBorderType(){
        return gridBorderType;
    }
}
