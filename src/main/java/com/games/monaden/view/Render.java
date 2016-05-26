package com.games.monaden.view;

import com.games.monaden.model.World;
import com.games.monaden.model.gameObjects.GameObject;
import javafx.animation.FadeTransition;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by paraply on 2016-04-19.
 * This class is the main view class that handles everything you see on the screen:
 * - Shows the start screen
 * - Draws the world
 * - Keeps a list of objects and redraws them continuously.
 * - Displays dialogs
 */
public class Render implements Observer{
    private static Render render; // Used by getInstance
    private GraphicsContext context;
    private World world; // World model provides information about what should be drawn
    private AnimatedObject player;
    private List<RenderObject> objects = new ArrayList<>();
    private List<RenderObject> interactables = new ArrayList<>();
    private RenderDialog renderDialog;
    private Pane overlay;


    // graphics context = main-canvas context
    // this is set by Window in its initialization
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

//    This is the dialog pane which labels are added to.
    public void setDialogObjects(HBox dialog){
        renderDialog = new RenderDialog(dialog);
    }

    public void setPlayerCharacter(GameObject player){
        this.player = new AnimatedObject(player,context);
    }

    //This class is currently singleton, since its instance needs to be accessed by different parts of the application
    public static synchronized Render getInstance(){
        if (render == null){
            render = new Render();
        }
        return render;
    }

    public void hideOverlay(){
        if (overlay == null){
            System.err.println("Render : hideOverlay got null value");
            return;
        }
        overlay.setVisible(false);
        overlay.getChildren().clear();
        Rectangle r = new Rectangle();
        r.setX(0);
        r.setY(0);
        r.setWidth(512);
        r.setHeight(512);
        overlay.getChildren().add(r);
        overlay.setOpacity(1);
    }

    public void setOverlay(Pane overlay){
        this.overlay = overlay;
    }


    public void overlayFade(){
        overlay.setVisible(true);
        FadeTransition ft = new FadeTransition(Duration.millis(500));
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setNode(overlay);
        ft.play();
    }


//    This is called by the game loop continuously
    public void redraw(){
//        First draw the objects that should be behind the character
            objects.stream().filter(ro -> ro.zOrder() == 0).forEach(RenderObject::draw);
//        Then draw everything you can interact with
            interactables.forEach(RenderObject::draw);
//        Draw the player at a layer above the previously drawn objects
            player.draw();
//        And at last draw the objects that should be at a layer above the player.
//        This allows the player to walk behind objects such as trees.
            objects.stream().filter(ro -> ro.zOrder() == 1).forEach(RenderObject::draw);
    }

    private void addWorldObjects(){
        if (world.getObjects() == null){
            System.err.println("Render: addWorldObjects gets null from world.getObjects");
            return;
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
            return;
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
     *  Delete all objects and create new when we transition to a new world
     */
    private void transition(){
        player.startTransition();
        objects.clear();
        interactables.clear();
        addWorldObjects();
        addInteractables();
        redraw();
    }


//  Called by the observer
    @Override
    public void update(Observable observable, Object arg) {
        if(observable == world) {
            transition();
        }
    }
}
