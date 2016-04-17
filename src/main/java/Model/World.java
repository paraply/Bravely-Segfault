package Model;

/**
 * Created by Anton on 2016-04-17.
 * There should only be a single instance of this class, but it should not have a global access point.
 * For now only sending a message to the console, should be handled in a better way
 */
public class World{
    //Temporarily hardcoded here, should always load a map from a file
    private int[][] tileMap =
                {{1,1,1,1,1,1,1,1,1,1},
                 {1,0,0,0,0,0,0,0,0,1},
                 {1,0,0,0,0,0,0,0,0,1},
                 {1,0,0,0,0,0,0,0,0,1},
                 {1,0,0,0,0,0,0,0,0,1},
                 {1,0,0,0,0,0,0,0,0,1},
                 {1,0,0,0,0,0,0,0,0,1},
                 {1,1,1,1,1,1,1,1,1,1}};

    private static boolean instantiated = false;
    public World() {
        if(instantiated)
        {
            System.out.println("A world object has already been instantiated!");
        }
        instantiated = true;
    }

    /** Checks if a movement in one direction in the tilemap is possible or not*/
    public boolean Move()
    {
        return true;
    }

}
