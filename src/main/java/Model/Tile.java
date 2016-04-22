package Model;


import java.io.File;

/**
 * Created by Philip on 2016-04-21.
 */
public class Tile {

    private String name;
    private File filepath;
    private boolean solidness;

//    public Tile () {
//
//    }

    public Tile (String name, boolean solidness, File filepath) {
        this.name = name;
        this.solidness = solidness;
        this.filepath = filepath;
    }

    public void setName (String name) {
        this.name = name;
    }
    public void setSolidness (boolean solidness) {
        this.solidness = solidness;
    }
    public void setFilepath (File filepath) {
        this.filepath = filepath;
    }

    public String toString () {
        return this.name;
    }
}
