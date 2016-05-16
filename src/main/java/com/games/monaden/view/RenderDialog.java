package com.games.monaden.view;

import com.games.monaden.model.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Created by mike on 2016-05-16.
 */
public class RenderDialog {
    private VBox dialog;
     private  Label question, a1,a2,a3;


    public RenderDialog(VBox dialog, Label q, Label a1, Label a2, Label a3){

        this.dialog = dialog;
        this.question = q;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
    }

    public void showDialog(String questionText, List<String> answers){
        dialog.setVisible(true);
        question.setVisible(true);
        question.setText(questionText);

        if (answers.size() == 3){
            a3.setVisible(true);
            a3.setText(answers.get(2));
        }

        if (answers.size() >= 2){
            a2.setVisible(true);
            a2.setText(answers.get(1));
        }

        if (answers.size() >= 1){
            a1.setVisible(true);
            a1.setText(answers.get(0));
        }

        dialog.setVisible(true);
    }

    public void hideDialog(){
        dialog.setVisible(false);
        a3.setVisible(false);
        a2.setVisible(false);
        a1.setVisible(false);
    }

}
