package com.games.monaden.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Created by mike on 2016-05-16.
 */
public class RenderDialog {
    private VBox dialog;
    private Label question;
    private Label[] answer;
    private int selected;
    private List<String> answerText;

    public RenderDialog(VBox dialog, Label q, Label a1, Label a2, Label a3){
        this.dialog = dialog;
        this.question = q;
        answer = new Label[3];
        answer[0] = a1;
        answer[1] = a2;
        answer[2] = a3;
    }

    public void showDialog(String questionText, List<String> answers){
        dialog.setVisible(true);
        question.setVisible(true);
        question.setText(questionText);

        for (int i = 0; i < answers.size(); i++){
            answer[i].setVisible(true);
            answer[i].setText(answers.get(i));
        }

        this.answerText = answers;
        select(0);
        dialog.setVisible(true);
    }

    public void hideDialog(){
        dialog.setVisible(false);
        for (int i = 0; i < 3; i++){
            answer[i].setVisible(false);
        }
    }

    public void selectPreviousAnswer(){
        if (selected != 0){
            select(selected--);
        }
    }

    public void selectNextAnswer(){
        if (selected < answerText.size()-1){
            select(selected++);
        }
    }

    private void select(int answerIndex){
        for (int i = 0; i < 3; i++){
            this.answer[i].getStyleClass().remove("dialog-choice-selected");
        }
        answer[answerIndex].getStyleClass().add("dialog-choice-selected");
        selected = answerIndex;
    }

    public void choose() {

    }

}
