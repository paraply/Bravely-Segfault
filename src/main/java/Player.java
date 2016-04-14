/**
 * Created by paraply on 2016-04-14.
 */
public class Player {
    private static Player player;
    private Player(){}

    private final static int MAX_WIDTH = 100;
    private final static int MAX_HEIGHT = 100;
    private int playerX = 50, playerY = 50;

    public void moveLeft(){
        playerX--;
        System.out.println("PLAYER LOCATION: X=" + playerX + " Y=" + playerY);
    }

    public void moveRight(){
        playerX++;
        System.out.println("PLAYER LOCATION: X=" + playerX + " Y=" + playerY);
    }

    public void moveDown(){
        playerY--;
        System.out.println("PLAYER LOCATION: X=" + playerX + " Y=" + playerY);
    }

    public void moveUp(){
        playerY++;
        System.out.println("PLAYER LOCATION: X=" + playerX + " Y=" + playerY);
    }

    public static synchronized Player getInstance(){
        if (player == null){
            player  = new Player();
        }
        return player;
    }
}
