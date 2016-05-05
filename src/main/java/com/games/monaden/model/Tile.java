package com.games.monaden.model;


import java.io.File;

/**
 * Created by Philip on 2016-04-21.
 * Tiles consist of an ID, name, filepath to its graphical image, and its solidness.
 * Tiles are comparable by their ID.
 */
public class Tile implements Comparable<Tile>{

    private int id;
    private String name;
    private File filepath;
    private boolean solidness;
    private boolean animated;

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
//    public Tile (int id, String name, boolean solidness, File filepath) {
//        this.id = id;
//        this.name = name;
//        this.solidness = solidness;
//        this.filepath = filepath;
//    }

    public Tile (int id, String name, boolean solidness, File filepath, boolean animated) {
        this.id = id;
        this.name = name;
        this.solidness = solidness;
        this.filepath = filepath;
        this.animated = animated;
    }

    public boolean isAnimated(){
        return animated;
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
    public boolean isSolid() {
        return solidness;
    }

    /**
     * Returns the tile's graphic file destination
     * @return file destination of graphics
     */
    public File getFilepath() {
        return filepath;
    }

    @Override
    public int compareTo(Tile t) {
        int otherId = t.getId();
        if (id == otherId) {
            return 0;
        } else if (id > otherId) {
            return 1;
        } else {
            return -1;
        }
    }
}
