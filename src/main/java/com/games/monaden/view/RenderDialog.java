package com.games.monaden.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private boolean dialogFail;

    public RenderDialog(HBox dialogBox){
        this.dialog = dialogBox;
        if (dialogBox == null){
            System.err.println("RenderDialog: Got null as dialogBox");
            dialogFail = true;
        }
    }

    public void newDialog(String questionText, List<String> answers, String avatarName){
        try {
            dialog.getChildren().clear();
            if (avatarName != null) {
                ImageView imageView = new ImageView();
                imageView.setImage(new Image("avatars/" + avatarName));
                dialog.getChildren().add(imageView);
            }

            labelBox = new VBox();
            question = new Label();
            question.setText(questionText);
            question.getStyleClass().add("dialog-question");
            question.setPadding(new Insets(0, 0, 3, 5));
            question.setWrapText(true);
            labelBox.getChildren().add(question);

            if (answers != null) {
                answer = new Label[answers.size()];

                for (int i = 0; i < answers.size(); i++) {
                    Label l = new Label();
                    l.setText(answers.get(i));
                    l.getStyleClass().add("dialog-choice");
                    l.setPadding(new Insets(0, 0, 0, 5));
                    labelBox.getChildren().add(l);
                    answer[i] = l;
                }
                selected = -1; // This is used to say that no answer was selected before

                this.answerText = answers;
                select(0);
            }
            dialog.getChildren().add(labelBox);
            dialog.setVisible(true);
        }catch (Exception e){
            System.err.println("RenderDialog: Error creating new dialog" );
            e.printStackTrace();
        }
    }

    public void hideDialog(){
        if (dialogFail){
            return;
        }
        dialog.setVisible(false);
    }

    public void selectPreviousAnswer(){
        if (answerText == null){
            return;
        }
        if (selected != 0){
            select(selected--);
        }
    }

    public void selectNextAnswer(){
        if (answerText == null){
            return;
        }
        if (selected < answerText.size() - 1){
            select(selected+1);
        }
    }

    private void select(int answerIndex){
        if (dialogFail){
            return;
        }
        if (selected != -1){
            answer[selected].getStyleClass().remove("dialog-choice-selected"); // Remove old selection
            answer[selected].setText(answerText.get(selected));
        }
        selected = answerIndex;
        answer[selected].getStyleClass().add("dialog-choice-selected");
        answer[selected].setText("> " + answerText.get(selected));
    }

    public void choose() {

    }

}
