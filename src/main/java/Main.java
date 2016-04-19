import Control.Game_Loop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by paraply on 2016-04-13.
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("window.fxml"));
        primaryStage.setTitle("Bravely Segfault");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.out.println("USER CLOSED APPLICATION WINDOW"));

        new Game_Loop().start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
