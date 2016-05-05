/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.games.monaden.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Stefan
 */
public class DialogTest {
    
    static Dialog frontDoor;
    static Dialog hall;
    static Dialog kitchen;
    static Dialog bedRoom;
    
    @BeforeClass
    public static void initTest(){
        //create dialogs: id, dialog, choses
        //create dialog text
        String frontDoorDialog = "This is the front door";
        String hallDialog = "This is the hall";
        String kitchenDialog = "This is the kitchen";
        String bedRoomDialog = "This is the bedroom";

        //create Dialog
        frontDoor = new Dialog(frontDoorDialog);
        hall = new Dialog(hallDialog);
        kitchen = new Dialog(kitchenDialog);
        bedRoom = new Dialog(bedRoomDialog);

        
        //create choice text

        String goToHall = "11 Go to the hall";
        String goToKitchen = "111 Go to the kitchen";
        String goToBedRoom = "113 Go to the bedroom";

        
        //Reading in choices
        frontDoor.readInChoices(goToHall, hall); // hall in index 0

        hall.readInChoices(goToKitchen, kitchen);// kitchen in index 0
        hall.readInChoices(goToBedRoom, bedRoom);// bedRoom in index 1

        
    }
        
    @Test
    public void testMovingToHall(){
        Dialog firstChoice = frontDoor.makeAChoice(0);
        assertTrue(firstChoice.getDialogText().equals(hall.getDialogText()));
    }
    
    @Test
    public void testMovingToKitchen(){
        Dialog firstChoice = frontDoor.makeAChoice(0);
        Dialog secondChoice = firstChoice.makeAChoice(0);
        assertTrue(secondChoice.getDialogText().equals(kitchen.getDialogText()));
    }

    
    @Test
    public void testMovingToBedRoom(){
        Dialog firstChoice = frontDoor.makeAChoice(0);
        Dialog secondChoice = firstChoice.makeAChoice(1);
        assertTrue(secondChoice.getDialogText().equals(bedRoom.getDialogText()));
    }
    
    
    @Test
    public void testChangeValues(){
        Dialog firstChoice = frontDoor.makeAChoice(0);
        String text = firstChoice.getDialogText();
        text = text + " [This have changed!]";
        assertTrue(firstChoice.getDialogText().equals("This is the hall"));
    }

}
