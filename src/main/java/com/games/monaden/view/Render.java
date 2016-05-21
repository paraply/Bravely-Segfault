package com.games.monaden.view;

import com.games.monaden.model.World;
import com.games.monaden.model.gameObjects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by paraply on 2016-04-19.
 */
public class Render implements Observer{
    private static Render render; // Used by getInstance
    private GraphicsContext context;
    private World world; // World model provides information about what should be drawn
    private AnimatedObject player;
    private List<RenderObject> objects = new ArrayList<>();
    private List<RenderObject> interactables = new ArrayList<>();
    public RenderDialog renderDialog; //TODO CHANGE TO PRIVATE AFTER TESTING


    // graphics context = main-canvas context
    // this is set by WindowController in its initialization
    public void setGraphicsContext(GraphicsContext context){
        this.context = context;
        if (context == null){
            System.err.println("Render: Got null as GraphicsContext");
        }
    }

    public void setWorld(World world){
        this.world = world;
        world.addObserver(this);
        addWorldObjects();
        addInteractables();
    }

    public void setDialogObjects(HBox dialog){
        renderDialog = new RenderDialog(dialog);
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
        objects.forEach(RenderObject::draw);
        interactables.forEach(RenderObject :: draw);
        player.draw();
    }

    private void addWorldObjects(){
        if (world.getObjects() == null){
            System.err.println("Render: addWorldObjects gets null from world.getObjects");
        }
        for (GameObject go : world.getObjects()){
            if (go.hasContinuousAnimation()){
                objects.add(new AnimatedObject(go,context));
            }else{
                objects.add(new RenderObject(go, context));
            }
        }
    }


    private void addInteractables(){
        if (world.getObjects() == null){
            System.err.println("Render: addInteractables gets null from world.getInteractables");
        }
        for (GameObject go : world.getInteractables()){
//            System.out.println("Adding interactable: " + go.getImagePath());
            if (go.hasContinuousAnimation()){
                interactables.add(new AnimatedObject(go,context));
            }else{
                interactables.add(new RenderObject(go, context));
            }
        }
    }

    /**
     *  Delete all objects and create new
     */
    private void transition(){
        player.startTransition();
        objects.clear();
        interactables.clear();
        addWorldObjects();
        addInteractables();
        player.hasTransitioned();
        redraw();
    }

    @Override
    public void update(Observable observable, Object arg) {
        //Should probably be refactored later
        if(observable == world) {
//            if(arg == "transition"){
                transition();
//            }
        }
    }
}
