package Menu;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.IOException;
import javafx.scene.shape.Rectangle;

public class MenuController {

    public static String absoluteFolderPath;

    @FXML
    private AnchorPane MenuAnchor;

    @FXML
    private Rectangle squareOne;

    @FXML
    private Rectangle squareTwo;

    @FXML
    private Rectangle rectPath;

    @FXML
    private Rectangle rectPath2;

    public void initialize() throws IOException {

        // [FOR ANIMATION PURPOSES] main menu animations
        PathTransition transition = new PathTransition();
        transition.setNode(squareOne);
        transition.setDuration(Duration.seconds(20));
        transition.setPath(rectPath);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.play();

        // [FOR ANIMATION PURPOSES] main menu animations
        PathTransition transition2 = new PathTransition();
        transition2.setNode(squareTwo);
        transition2.setDuration(Duration.seconds(20));
        transition2.setPath(rectPath2);
        transition2.setCycleCount(PathTransition.INDEFINITE);
        transition2.setInterpolator(Interpolator.EASE_BOTH);
        transition2.play();
    }

    // Button function go to the page to generate the encrypted message
    public void ProceedSearch(ActionEvent actionEvent) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(getClass().getResource("SearchFile.fxml"));
        MenuAnchor.getChildren().setAll(nextAnchorPane);
    }


}
