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

    public RenderObject(GameObject gameObject, GraphicsContext graphicsContext, String imageSection, String imageName){
        this.gameObject = gameObject;
        context = graphicsContext;
        image = new Image( imageSection + "/" + imageName + ".png");
    }

    public void draw(){
        context.drawImage(image, gameObject.getX() * World.tileSize , gameObject.getY() * World.tileSize);
    }
}
