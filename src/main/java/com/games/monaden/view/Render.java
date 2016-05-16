package com.games.monaden.view;

import com.games.monaden.model.gameObjects.GameObject;
import com.games.monaden.model.World;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Observable;

/**
 * Created by paraply on 2016-04-19.
 */
public class Render implements Observer{
    private static Render render; // Used by getInstance
    private GraphicsContext context;
    private World world; // World model provides information about what should be drawn
    private AnimatedObject player;
    private List<RenderObject> objects = new ArrayList<>();


    // graphics context = main-canvas context
    // this is set by WindowController in its initialization
    public void setGraphicsContext(GraphicsContext context){
        this.context = context;
    }

    public void setWorld(World world){
        this.world = world;
        world.addObserver(this);
        addWorldObjects();
    }

    public void setPlayerCharacter(GameObject player){
        this.player = new AnimatedObject(player,context);
    }

    //This class is currently singleton, since its instance needs to be accessed by WindowController, RenderObjects. May change...
    public static synchronized Render getInstance(){
        if (render == null){
            render = new Render();
        }
        return render;
    }

    public void redraw(){
        for (RenderObject ro : objects){
            ro.draw();
        }
        player.draw();
    }

    private void addWorldObjects(){
        for (GameObject go : world.getObjects()){
            if (go.hasContinuousAnimation()){
                objects.add(new AnimatedObject(go,context));
            }else{
                objects.add(new RenderObject(go, context));
            }
        }
    }

    private void transition(){
        objects.clear();
        addWorldObjects();
        player.hasTransitioned();
        redraw();
    }

    @Override
    public void update(Observable observable, Object arg) {
        //Should probably be refactored later
        if(observable == world) {
            System.out.println("UPDATE: " + arg);
            if(arg == "transition"){
                player.startTransition();
                objects.clear();
                for (GameObject go : world.getObjects()){
                    if (go.hasContinuousAnimation()){
                        objects.add(new AnimatedObject(go,context));
                    }else{
                        objects.add(new RenderObject(go,context));
                    }
                }
                transition();
            }
        }
    }
}
