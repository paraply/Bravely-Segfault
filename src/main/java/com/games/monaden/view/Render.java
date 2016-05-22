package com.games.monaden.view;

import com.games.monaden.model.World;
import com.games.monaden.model.gameObjects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private RenderDialog renderDialog;
    private int showStartScreen = 160;
    private Image startScreen;


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

//  The dialog needs to be accessed by the controller
    public RenderDialog getDialog(){
        return renderDialog;
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

    private boolean showStartScreen(){
        if (showStartScreen > 0){
            if (startScreen == null){
                startScreen = new Image("start/startscreen.png");
            }
            if (context != null) {
                context.drawImage(startScreen, 0, 0, 512, 512, 0, 0, 512, 512);
                showStartScreen--;
            }
            return true;
        }
        return false;
    }

    public void redraw(){
        if (!showStartScreen()) {
            objects.stream().filter(ro -> ro.zOrder() == 0).forEach(RenderObject::draw);
            interactables.forEach(RenderObject::draw);
            player.draw();

            objects.stream().filter(ro -> ro.zOrder() == 1).forEach(RenderObject::draw);
        }
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
        redraw();
    }



    @Override
    public void update(Observable observable, Object arg) {
        if(observable == world) {
//            if(arg == "transition"){
            transition();
//            }
        }
    }
}
