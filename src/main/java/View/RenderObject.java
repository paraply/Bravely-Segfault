package View;

import Model.GameObjects.GameObject;
import Model.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by mike on 2016-04-21.
 */
public class RenderObject {
    private GameObject gameObject;
    private GraphicsContext context;
    private Image image;
    private final int IMAGE_SIZE = 32;


    public RenderObject(GameObject gameObject, GraphicsContext graphicsContext, String imageSection, String imageName){
        this.gameObject = gameObject;
        context = graphicsContext;
        image = new Image( imageSection + "/" + imageName + ".png");
    }

    public void draw(){
        int image_x_src, image_y_src ;
        switch (gameObject.getDirection()){
            case BACK:
                image_x_src = 0;
                image_y_src = IMAGE_SIZE * 3;
                break;
            case LEFT:
                image_x_src = 0;
                image_y_src = IMAGE_SIZE;
                break;
            case RIGHT:
                image_x_src = 0;
                image_y_src = IMAGE_SIZE * 2;
                break;
            default: //FRONT
                image_x_src = 0;
                image_y_src = 0;
        }

        context.drawImage(image, image_x_src,image_y_src, IMAGE_SIZE,IMAGE_SIZE, gameObject.getX() * World.tileSize , gameObject.getY() * World.tileSize,IMAGE_SIZE,IMAGE_SIZE);
    }
}
