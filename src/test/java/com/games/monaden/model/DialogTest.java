/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.games.monaden.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Stefan
 */
public class DialogTest {
    
    static Dialog frontDoor;
    
    @BeforeClass
    public static void initTest(){
        //create dialogs: id, dialog, choses
        //create dialog text
        String frontDoorDialog = "This is the front door";
        String hallDialog = "This is the hall";
        String kitchenDialog = "This is the kitchen";
        String bedRoomDialog = "This is the bedroom";
        String bathRoomDialog = "This is the bathroom";
        String livingRoomDialog = "This is the livingroom";

        //create Dialog
        frontDoor = new Dialog(frontDoorDialog);
        Dialog hall = new Dialog(hallDialog);
        Dialog kitchen = new Dialog(kitchenDialog);
        Dialog bathRoom = new Dialog(bathRoomDialog);
        Dialog bedRoom = new Dialog(bedRoomDialog);
        Dialog livingRoom = new Dialog(livingRoomDialog);
        
        //create choice text
        String goToFrontDoor = "1 Go to the front door";
        String goToHall = "11 Go to the hall";
        String goToKitchen = "111 Go to the kitchen";
        String goToBathRoom = "112 Go to the bathroom";
        String goToBedRoom = "113 Go to the bedroom";
        String goToLivingRoom = "114 Go to the living room";
        
        //Reading in choices
        frontDoor.readInChoices(goToHall, hall);
        hall.readInChoices(goToKitchen, kitchen);
        hall.readInChoices(goToBathRoom, bathRoom);    
        hall.readInChoices(goToBedRoom, bedRoom);
        hall.readInChoices(goToLivingRoom, livingRoom);
        
    }
        
    @Test
    public void testMovingToHall(){
        
    }
    
    @Test
    public void testMovingToBedRoom(){
        
    }
    
    @Test
    public void testMovingToKitchen(){
        
    }
    
    @Test
    public void testChangeValues(){
        
    }

}
