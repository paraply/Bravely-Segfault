package View;

import com.games.monaden.model.gameObjects.GameObject;
import com.games.monaden.model.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by mike on 2016-04-21.
 */
public class RenderObject {
    private GameObject gameObject;
    private GraphicsContext context;
    private Image image;
    private final int IMAGE_HEIGHT = 32, IMAGE_WIDTH = 32;
    private int animation_part = 0;

    public RenderObject(GameObject gameObject, GraphicsContext graphicsContext, String imageSection, String imageName){
        this.gameObject = gameObject;
        context = graphicsContext;
        image = new Image( imageSection + "/" + imageName + ".png");
    }

    public void draw(){
        if (animation_part == 30){
            animation_part = 0;
        }

        int image_x_src, image_y_src ;
        switch (gameObject.getDirection()){
            case UP:
                image_y_src = IMAGE_HEIGHT * 3;
                break;
            case LEFT:
                image_y_src = IMAGE_HEIGHT;
                break;
            case RIGHT:
                image_y_src = IMAGE_HEIGHT * 2;
                break;
            default: //FRONT
                image_y_src = 0;
        }

        image_x_src = (int) Math.floor(animation_part / 10) * IMAGE_WIDTH;

        context.drawImage(image, image_x_src,image_y_src, IMAGE_HEIGHT, IMAGE_HEIGHT, gameObject.getPosition().getX() * World.tileSize , gameObject.getPosition().getY() * World.tileSize, IMAGE_HEIGHT, IMAGE_HEIGHT);
        animation_part++;
    }
}
