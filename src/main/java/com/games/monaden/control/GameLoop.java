package com.games.monaden.control;

import com.games.monaden.model.*;
import com.games.monaden.model.events.DialogEvent;
import com.games.monaden.model.gameObjects.Character;
import com.games.monaden.model.gameObjects.GameObject;
import com.games.monaden.view.Render;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

import java.util.*;

/**
 * Created by paraply on 2016-04-13.
 */
public class GameLoop extends AnimationTimer implements Observer {

    public final static int FREQUENCY = 16;
    private int countDown = FREQUENCY;
    private double volume = 0.0;

    private World world;
    private CharacterController playerCharacter;
    private AudioController audioController;
    private HashMap<Integer, Tile> tileMap;

    private DialogController dialogController;

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof DialogEvent) {
            DialogEvent de = (DialogEvent)arg;
            startDialog((Dialog)de.getEventContent());
        } else {
            setLevel((String) arg);
        }
    }

    private enum InputState { MOVEMENT, DIALOG, STARTSCREEN }
    private InputState inputState = InputState.STARTSCREEN;

    public GameLoop () {
        tileMap = new HashMap<>();
        playerCharacter = new CharacterController();
        playerCharacter.addObserver(this);
        dialogController = new DialogController();
    }

    public void initializeGame(){
        tileMap = new TileLoader().loadTiles();
        world = new World();
        setLevel("ea.xml");
        Render.getInstance().setWorld(world);
        audioController = new AudioController();
        audioController.playMusic(0);
    }

    /**
     * Sets the world's current level to be what the given file is.
     * @param levelName File path to XML
     */
    private void setLevel (String levelName) {
        LevelLoader levelLoader = new LevelLoader();
        levelLoader.loadLevel(levelName);
        int [][] primTileMap = levelLoader.getTileMap();

        List<GameObject> gameObjects = new ArrayList<>();

        outerloop:
        for (int y = 0; y < World.MAP_SIZE; y++) {
            for (int x = 0; x < World.MAP_SIZE; x++) {
                Tile currentTile =  findTile(primTileMap[y][x]);
                if (currentTile == null){
                    System.err.println("Bad tile @ X" + x + " Y:" + y);
                    break outerloop;
                }
                GameObject newGameObject = new GameObject(new Point(x, y), currentTile.getFilepath().toString(), currentTile.isSolid());
                newGameObject.setContinuousAnimation(currentTile.isAnimated());
                gameObjects.add(newGameObject);
            }
        }

        gameObjects.addAll(levelLoader.getGameObjects());

        List<Character> interactables = levelLoader.getInteractables();

        DialogLoader dialogLoader = new DialogLoader();

        //Add dialog to each character
        for (Character c : interactables) {
            c.setDialog(dialogLoader.parseDialog(c.getDialogFile().getPath()));
        }

        //Handle events in the level
        List<DialogEvent> events = levelLoader.getEvents();
        for (DialogEvent de : events) {
            Dialog dialog = dialogLoader.parseDialog(de.getFilepath().getPath());
            de.setEventContent(dialog);
        }

        world.setCurrentLevel(gameObjects, interactables, levelLoader.getTransitions(), events);
    }



    private Tile findTile (int tileNr) {
        return tileMap.get(tileNr);
    }

    private void handleEvents (Event event) {
        if (event instanceof DialogEvent) {
            Dialog dialog = (Dialog)event.getEventContent();
            startDialog(dialog);
        }
    }

    @Override
    public void handle(long now) {
        if (inputState == InputState.STARTSCREEN){
            if (UserInput.getInstance().getLatestFunctionKey() == null){
                return;
            }else{
                Render.getInstance().hideStartScreen();
                inputState = InputState.MOVEMENT;
            }
        }

        Render.getInstance().redraw();
        if (countDown > 0){  // used to add a delay (better than sleep) to user movement
            countDown--;
        }
        else if(inputState == InputState.MOVEMENT) {
            UserInput userInput = UserInput.getInstance();
            KeyCode moveReq = userInput.getLatestMovementKey();
            if (moveReq != null) {
                playerCharacter.handleMovement(moveReq, world);
                countDown = FREQUENCY;
            }

            KeyCode funcReq = userInput.getLatestFunctionKey();

            if (funcReq != null) {
                Dialog dialog = playerCharacter.handleInteractions(funcReq, world);
                if (dialog != null) {
                    startDialog(dialog);
                }else if (funcReq == KeyCode.PLUS) {

                    volume = audioController.volumeUp();

                } else if (funcReq == KeyCode.MINUS) {

                    volume = audioController.volumeDown();

                } else if (funcReq == KeyCode.N) {
                    
                    audioController.stopMusic();
                    audioController.playMusic(1);
                }
            }
        }
        else if(inputState == InputState.DIALOG){
            UserInput userInput = UserInput.getInstance();
            KeyCode moveReq = userInput.getLatestMovementKey();
            if(dialogController.handleMovement(moveReq)){
                countDown = FREQUENCY;
            }

            KeyCode funcReq = userInput.getLatestFunctionKey();
            if(dialogController.handleSpecial(funcReq)){
                inputState = InputState.MOVEMENT;
            }
        }
    }

    private void startDialog(Dialog dialog) {
        inputState = InputState.DIALOG;
        dialogController.startDialog(dialog);
    }
}
