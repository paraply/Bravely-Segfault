package com.games.monaden.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Created by mike on 2016-05-16.
 */
public class RenderDialog {
    private HBox dialog;
    private VBox labelBox;
    private Label question;
    private Label[] answer;
    private int selected;
    private List<String> answerText;

    public RenderDialog(HBox dialogBox, VBox labelBox){
        this.dialog = dialogBox;
        this.labelBox = labelBox;
    }

    public void newDialog(String questionText, List<String> answers){
        labelBox.getChildren().clear();

        question = new Label();
        question.setText(questionText);
        question.getStyleClass().add("dialog-question");
        question.setWrapText(true);
        labelBox.getChildren().add(question);

        answer = new Label[answers.size()];


        for (int i = 0; i < answers.size(); i++){
//            answer[i].setVisible(true);
//            answer[i].setText(answers.get(i));
//            answer[i].getStyleClass().remove("dialog-choice-selected"); // If any dialog was shown before, one was selected. Unselect that one.
            Label l = new Label();
            l.setText(answers.get(i));
            l.getStyleClass().add("dialog-choice");
            labelBox.getChildren().add(l);
            answer[i] = l;
        }
        selected = -1; // This is used to say that no answer was selected before

        this.answerText = answers;
        select(0);
        dialog.setVisible(true);
    }

    public void hideDialog(){
        dialog.setVisible(false);
        for (int i = 0; i < answerText.size() - 1; i++){
            answer[i].setVisible(false);
        }
    }

    public void selectPreviousAnswer(){
        if (selected != 0){
            select(selected--);
        }
    }

    public void selectNextAnswer(){
        if (selected < answerText.size() - 1){
            select(selected+1);
        }
    }

    private void select(int answerIndex){
        if (selected != -1){
            answer[selected].getStyleClass().remove("dialog-choice-selected"); // Remove old selection
            answer[selected].setText(answerText.get(answerIndex));
        }
        selected = answerIndex;
        answer[selected].getStyleClass().add("dialog-choice-selected");
        answer[selected].setText("> " + answerText.get(selected));
    }

    public void choose() {

    }

}
