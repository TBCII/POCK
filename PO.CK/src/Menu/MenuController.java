package Menu;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DecimalFormat;

import javafx.scene.shape.Rectangle;

public class MenuController {

    public static String absoluteFolderPath;
    public static String[] filePath = new String[120];
    public static int fileCount = 0;
    public static String[] fileNames = new String[120];
    public static String longestLine = "";
    public static String longestLineFile1, longestLineFile2, longestLineDir1, longestLineDir2 = "";
    //public static int longestLineNum = 0;
    public static double firstScore = 0, secondScore = 0, thirdScore = 0, fourthScore = 0, fifthScore = 0;
    public static int[][] topIndices = new int[5][2];
    public String directoryPath = absoluteFolderPath;

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

        absoluteFolderPath = "";
        longestLine = "";
        longestLineFile1 = "";
        longestLineFile2 = "";
        fileCount = 0/* longestLineNum = 0*/;
        firstScore = secondScore = thirdScore = fourthScore = fifthScore = 0;

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 2; j++){
                topIndices[i][j] = 0;
            }
        }

        for(int i = 0; i < 120; i++){
            filePath[i] = "";
            fileNames[i] = "";
        }

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

    public void ProceedSearch(ActionEvent actionEvent) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(getClass().getResource("SearchFile.fxml"));
        MenuAnchor.getChildren().setAll(nextAnchorPane);
    }
}