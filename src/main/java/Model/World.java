package Model;

/**
 * Created by Anton on 2016-04-17.
 * There should only be a single instance of this class, but it should not have a global access point.
 * For now only sending a message to the console, should be handled in a better way
 */
public class World{

    public static final int tileSize = 32;
    public static final int mapSize = 16;

    //Temporarily hardcoded here, should always load a map from a file
    //Currently hardcoded to have size 8*10 with magic numbers, should be changed ASAP!
    private int[][] tileMap = new int[mapSize][mapSize];

    private static boolean instantiated = false;

    public int[][] getTileMap(){
        return tileMap;
    }

    public World() {
        if(instantiated) {
            System.out.println("A world object has already been instantiated!");
        }
        instantiated = true;

        tileMap = new int[][]{
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
    }

    public enum MovementDirection {
        UP, DOWN, LEFT, RIGHT
    }
    /** Checks if a movement in one direction in the tilemap is possible or not
     *  Returns the new position after movement, and also handles potential new screen
     */
    public boolean Move(int y, int x, MovementDirection direction) {
        switch(direction){
            case UP:
                if(y == 0)
                    return false;
                return !CheckSolidTile(tileMap[y-1][x]);
            case DOWN:
                if(y == mapSize - 1)
                    return false;
                return !CheckSolidTile(tileMap[y+1][x]);
            case LEFT:
                if(x == 0)
                    return false;
                return !CheckSolidTile(tileMap[y][x-1]);
            case RIGHT:
                if(x == mapSize - 1)
                    return false;
                return !CheckSolidTile(tileMap[y][x+1]);
            default:
                return false;
        }
    }

    /** Temporary helper function for tilemap solidity
     *  Will be refactored to some kind of data structure later
     */
    private boolean CheckSolidTile(int value)
    {
        return value == 1;
    }

}
