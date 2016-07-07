package com.games.monaden.model;

import com.games.monaden.model.dialog.Dialog;
import com.games.monaden.model.gameobject.Character;
import com.games.monaden.model.primitives.Point;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import static org.junit.Assert.assertTrue;

/**
 * Created by Anton on 2016-04-26.
 */
public class CharacterTest {

    private Character player;

    @Before
    public void init(){
        player = new Character(new Point(1,1), null);
        player.setName("Stefan");
        player.setDialog(new Dialog("Hi, you all"));
        player.setDialogFile(new File("src/main/resources/parseTests/DialogTest.xml"));
    }

    @Test
    public void testName(){
        assertTrue("Stefan" == player.getName());
    }

    @Test
    public void testDialog(){
        assertTrue("Hi, you all" == player.getDialog().getDialogText());
    }

    @Test
    public void testDialogFile(){
        System.out.println(player.getDialogFile().getName());
        assertTrue( 0 =="DialogTest.xml".compareTo(player.getDialogFile().getName()));
    }

}
