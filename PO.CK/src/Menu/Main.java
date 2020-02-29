package Menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../Menu/MenuUI.fxml"));
        primaryStage.setTitle("POCK SOFTWARE SIMILARITY PROGRAM");
        primaryStage.setScene(new Scene(root, 1280, 800));
        primaryStage.getIcons().add(new Image("imageAssets/POCKicon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}