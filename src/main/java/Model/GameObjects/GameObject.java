package Model.GameObjects;

/**
 * Created by Anton on 2016-04-19.
 */
public class GameObject {
    private int x;
    private int y;
    //Should contain collision

    private int transition_ticks = 0;


    private void start_transition(){
        transition_ticks = 32;
    }

    public int getTransitionTicks(){
        return transition_ticks;
    }

    public enum Direction {
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
        if (transition_ticks == 0) {
            x = _x;
            start_transition();
        }
    }

    protected void setY(int _y){
        if (transition_ticks == 0) {
            y = _y;
            start_transition();
        }
    }

    public void update(){
        if (transition_ticks > 0){
            transition_ticks--;
        }
    }

    public GameObject(int startX, int startY)
    {
        x = startX;
        y = startY;
    }
}
