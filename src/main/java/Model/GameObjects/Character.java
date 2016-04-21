package Model.GameObjects;

import Model.World;

/**
 * Created by Anton on 2016-04-19.
 */
public class Character extends GameObject {
    private World world;

    private int screenX;
    private int screenY;

    //Variables for potential movement animation
    private int targetX;
    private int targetY;
    private float speed;

    public int getScreenX(){return screenX;}
    public int getScreenY(){return screenY;}

    public Character(int x, int y, World _world) {
        super(x, y);
        screenX = x * World.tileSize;
        screenY = y * World.tileSize;
        world = _world;
    }

    public void Update() {

    }

    public void Move(World.MovementDirection direction) {
        if(world.Move(getY(), getX(), direction)) {
            switch(direction) {
                case UP:
                    setY(getY() - 1);
                    setDirection(Direction.BACK);
                    break;
                case DOWN:
                    setY(getY() + 1);
                    setDirection(Direction.FRONT);
                    break;
                case LEFT:
                    setX(getX() - 1);
                    setDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    setX(getX() + 1);
                    setDirection(Direction.RIGHT);
                    break;
            }
        }
    }
}
