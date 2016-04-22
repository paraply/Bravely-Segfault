package View;

import Model.GameObjects.GameObject;
import Model.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Created by paraply on 2016-04-21.
 */
public class RenderObject {
    protected GameObject gameObject;
    protected GraphicsContext context;
    protected Image image;

    protected final int IMAGE_HEIGHT = 32, IMAGE_WIDTH = 32; // Currently hardcoded. Could possibly be specified in XML.
    protected int x,y; // Objects position in the world
    protected int image_src_X, image_src_Y; // Where from the the tileset should be used as source.


    public RenderObject(GameObject gameObject, GraphicsContext graphicsContext, String imageSection, String imageName){
        this.gameObject = gameObject;
        context = graphicsContext;
        image = new Image( imageSection + "/" + imageName + ".png");
    }


    private void calculateX(){
        image_src_X = 0;
        x = gameObject.getX() * World.tileSize;
        y =  gameObject.getY() * World.tileSize;
    }

    protected void calculateY(){
        switch (gameObject.getDirection()){
            case UP:
                image_src_Y = IMAGE_HEIGHT * 3;
                break;
            case LEFT:
                image_src_Y = IMAGE_HEIGHT;
                break;
            case RIGHT:
                image_src_Y = IMAGE_HEIGHT * 2;
                break;
            default: //DOWN
                image_src_Y = 0;
        }
    }

    public void draw(){
        calculateX();
        calculateY();
        context.drawImage(image, image_src_X,image_src_Y, IMAGE_HEIGHT, IMAGE_HEIGHT, x, y, IMAGE_HEIGHT, IMAGE_HEIGHT);
    }
}
