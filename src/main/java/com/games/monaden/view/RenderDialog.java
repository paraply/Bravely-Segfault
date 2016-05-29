package com.games.monaden.view;

import com.games.monaden.model.dialog.Dialog;
import com.games.monaden.model.inventory.Inventory;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Created by mike on 2016-05-16.
 * This class is used to show dialogs using a JavaFX pane and labels
 */
public class RenderDialog {
    private HBox dialog;
    private VBox labelBox;
    private Label question;
    private Label[] answer;
    private int selected;
    private Dialog dialogObject;
    private final int DIALOG_FADE_TIME = 280;

    private boolean dialogFail;

    public RenderDialog(HBox dialogBox){
        this.dialog = dialogBox;
        if (dialogBox == null){
            System.err.println("Dialog render: Got null as dialogBox in the constructor");
            dialogFail = true;
        }
    }

    public void newDialog(Dialog dialogObject, Inventory inventory){
        if (dialogFail){
            return;
        }
        if (dialog == null || dialogObject == null){
            System.err.println("Dialog render : newDialog got a null object");
            return;
        }
        try {
            this.dialogObject = dialogObject;

            dialog.getChildren().clear();
            if (dialogObject.getImageFile() != null) {
                ImageView imageView = new ImageView();
                imageView.setImage(new Image("avatars/" + dialogObject.getImageFile().toString()));
                dialog.getChildren().add(imageView);
            }

            labelBox = new VBox();
            question = new Label();
            question.setText(dialogObject.getDialogText());
            question.getStyleClass().add("dialog-question");
            question.setPadding(new Insets(0, 0, 3, 5));
            question.setWrapText(true);

            labelBox.getChildren().add(question);

            if (dialogObject.getChoiceTextCount(inventory) != 0) {
                createAnswers(inventory);
            }
            dialog.getChildren().add(labelBox);
            dialog.setOpacity(0);
            FadeTransition ft = new FadeTransition(Duration.millis(DIALOG_FADE_TIME));
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.setNode(dialog);
            ft.play();
            dialog.setVisible(true);
        }catch (Exception e){
            System.err.println("Dialog Render: Error creating new dialog" );
            e.printStackTrace();
        }
    }

    private void createAnswers(Inventory inventory){
        answer = new Label[dialogObject.getChoiceTextCount(inventory)];

        for (int i = 0; i < dialogObject.getChoiceTextCount(inventory); i++) {
            Label l = new Label();
            l.setText(dialogObject.getChoiceText(i));
            l.getStyleClass().add("dialog-choice");
            l.setPadding(new Insets(0, 0, 0, 5));
            l.setWrapText(true);
            labelBox.getChildren().add(l);
            answer[i] = l;
        }
        selected = -1; // This is used to say that no answer was selected before

        select(0);
    }


    public void hideDialog(){
        if (dialogFail){
            return;
        }
        selected = 0;
        FadeTransition ft = new FadeTransition(Duration.millis(DIALOG_FADE_TIME));
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setNode(dialog);
        ft.play();
//        dialog.setVisible(false);
    }

    public int getSelected(){
        return selected;
    }

    public void select(int answerIndex){
        if (dialogFail){
            return;
        }

        if (answer == null){
            return;
        }

        if (selected != -1){
            answer[selected].getStyleClass().remove("dialog-choice-selected"); // Remove old selection
            answer[selected].setText(dialogObject.getChoiceText(selected)) ;
        }
        selected = answerIndex;
        answer[selected].getStyleClass().add("dialog-choice-selected");
        answer[selected].setText("> " + dialogObject.getChoiceText(selected));
    }

}
