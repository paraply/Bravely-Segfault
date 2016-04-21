package Model.GameObjects;

/**
 * Created by Anton on 2016-04-19.
 */
public class GameObject {
    private int x;
    private int y;
    //Should contain collision

    public static enum Direction {
        FRONT,
        BACK,
        RIGHT,
        LEFT
    }
    private Direction object_direction = Direction.FRONT;

    public Direction getDirection(){
        return object_direction;
    }

    public void setDirection(Direction direction){
        object_direction = direction;
    }


    public int getX(){return x;}
    public int getY(){return y;}

    protected void setX(int _x){
        x = _x;
    }

    protected void setY(int _y){
        y = _y;
    }

    public GameObject(int startX, int startY)
    {
        x = startX;
        y = startY;
    }
}
