package Model.GameObjects;

/**
 * Created by Anton on 2016-04-19.
 */
public class Character extends GameObject {
    private int screenX;
    private int screenY;

    public int getScreenX(){return screenX;}
    public int getScreenY(){return screenY;}

    public Character(int x, int y) {
        super(x, y);
    }
}
