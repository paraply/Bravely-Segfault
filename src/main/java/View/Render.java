package View;

import Model.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by paraply on 2016-04-19.
 */
public class Render {
    private static Render render; // Used by getInstance
    private GraphicsContext context;
    private World world; // World model provides information about what should be drawn

    private boolean has_loaded_images;
    Image images[] = new Image[100];

    // graphics context = main-canvas context
    // this is set by WindowController in its initialization
    public void setGraphicsContext(GraphicsContext context){
        this.context = context;
    }

    public void setWorld(World world){
        this.world = world;
    }

    //This class is currently singleton, since its instance needs to be accessed by both WindowController and the Model. May change...
    public static synchronized Render getInstance(){
        if (render == null){
            render = new Render();
        }
        return render;
    }

    private void load_images(){
        if (!has_loaded_images){
            images[0] = new Image("grass.png");
            images[1] = new Image("border.png");
            has_loaded_images = true;
        }
    }

    public void redraw(){
        load_images();
        for (int y = 1; y <=16; y++){
            for (int x = 1; x <= 16; x++){
                if (x == 1 || y == 1 || x == 16 || y == 16){
                    draw_tile(1,x,y);
                }else{
                    draw_tile(0,x,y);
                }
            }
        }

    }

    private void draw_tile(int index, int tile_x, int tile_y){
        context.drawImage(images[index], (tile_x - 1) * 32 , (tile_y - 1) * 32);
    }

}
//