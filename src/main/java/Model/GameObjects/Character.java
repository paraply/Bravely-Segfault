package Model.GameObjects;

import Model.World;

/**
 * Created by Anton on 2016-04-19.
 */
public class Character extends GameObject {
    private int screenX;
    private int screenY;

    private int targetX;
    private int targetY;
    private float speed;


    public int getScreenX(){return screenX;}
    public int getScreenY(){return screenY;}

    public Character(int x, int y) {
        super(x, y);
        screenX = x * World.tileSize;
        screenY = y * World.tileSize;
        targetX = screenX;
        targetY = screenY;
    }

    public void Update()
    {
        
    }
}
