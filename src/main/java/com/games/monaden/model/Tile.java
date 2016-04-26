package com.games.monaden.model;


import java.io.File;

/**
 * Created by Philip on 2016-04-21.
 */
public class Tile {

    private int id;
    private String name;
    private File filepath;
    private boolean solidness;

//    public Tile () {
//
//    }

    /**
     * Initialize a tile with all its values.
     * @param id
     * @param name
     * @param solidness
     * @param filepath
     */
    public Tile (int id, String name, boolean solidness, File filepath) {
        this.id = id;
        this.name = name;
        this.solidness = solidness;
        this.filepath = filepath;
    }

    /**
     * Returns assigned tile ID
     * @return tile ID
     */
    public int getId () {
        return id;
    }

    /**
     * Returns assigned tile name
     * @return tile name
     */
    public String getName () {
        return name;
    }

    /**
     * Returns assigned solidness
     * @return true if tile is solid, false if not
     */
    public boolean getSolidness () {
        return solidness;
    }

    /**
     * Returns the tile's graphic file destination
     * @return file destination of graphics
     */
    public File getFilepath() {
        return filepath;
    }
}
