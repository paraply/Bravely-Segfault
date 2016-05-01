package com.games.monaden.view;

import com.games.monaden.model.gameObjects.GameObject;
import com.games.monaden.model.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paraply on 2016-04-19.
 */
public class Render {
    private static Render render; // Used by getInstance
    private GraphicsContext context;
    private World world; // World model provides information about what should be drawn
    private AnimatedObject player;
    private List<RenderObject> objects = new ArrayList<>();
//
//    private boolean has_loaded_images;
//    Image images[] = new Image[100];

    // graphics context = main-canvas context
    // this is set by WindowController in its initialization
    public void setGraphicsContext(GraphicsContext context){
        this.context = context;
    }

    public void setWorld(World world){
        this.world = world;
        for (GameObject go : world.getObjects()){
                if (go.hasContinuousAnimation()){
                    objects.add(new AnimatedObject(go));
                }else{
                    objects.add(new RenderObject(go));
                }
        }
    }

    public void setPlayerCharacter(GameObject player){
        this.player = new AnimatedObject(player);
    }

    //This class is currently singleton, since its instance needs to be accessed by WindowController, RenderObjects. May change...
    public static synchronized Render getInstance(){
        if (render == null){
            render = new Render();
        }
        return render;
    }

//    private void load_images(){
////        if (!has_loaded_images){
////            images[0] = new Image("grass.png");
////            has_loaded_images = true;
////        }
//    }

    public void redraw(){
//        load_images();
//        draw_ground_tiles();

        for (RenderObject ro : objects){
            ro.draw();
        }
        player.draw();
    }

//    private void draw_ground_tiles(){
//        for (int y = 0; y < World.mapSize; y++){
//            for (int x = 0; x < World.mapSize; x++){
//                if (world.getTileMap()[y][x] == 0){
//                    draw_tile(0,x,y);
//                }
//            }
//        }
//    }

    // Used by renderObjects to draw on screen
    void drawImage(Image img, double sx, double sy, double sw, double sh, double dx, double dy, double dw, double dh){
        context.drawImage(img, sx,sy,sw,sh,dx,dy,dw,dh);
    }

//    private void draw_tile(int index, int tile_x, int tile_y){
//        context.drawImage(images[index], tile_x * World.tileSize , tile_y * World.tileSize);
//    }

}
