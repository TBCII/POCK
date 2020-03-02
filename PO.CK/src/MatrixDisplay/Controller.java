package MatrixDisplay;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Controller extends Menu.MenuController {

    public AnchorPane matrixAnchor;
    public GridPane CorrelationMatrix;
    public GridPane FilesList;
    public ScrollPane scrollPane;
    public Text fileTotal;
    public Button toSimStats;
    @FXML
    private Rectangle squareOne;
    @FXML
    private Rectangle rectPath;

    BufferedReader bFile1 = null;
    BufferedReader bFile2 = null;

    String line1SansSpaces;
    String line2SansSpaces = "";
    String line1;
    String line2 = "";


    int currentCharacterCount = 0;
    int longestCharacterCount = 0;
    int lineCount = 0;


    int lineCount1;
    int lineCount2;

    double[][] similarityCount = new double[120][120];
    double[][] totalLineCount = new double[120][120];
    double[][] similarityIndex = new double[120][120];

    ArrayList<String>[] lineStorage;

    DecimalFormat df = new DecimalFormat("0.00");

    File submissionsFolder = new File(directoryPath);

    public void initialize() throws IOException {

        PathTransition transition = new PathTransition();
        transition.setNode(squareOne);
        transition.setDuration(Duration.seconds(5));
        transition.setPath(rectPath);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.play();

        System.out.println("I N I T I A L I Z I N G");

        if(submissionsFolder.exists() && submissionsFolder.isDirectory()){
            File arr[] = submissionsFolder.listFiles();

            listFiles(arr, 0 ,0);
        }

        if(fileCount > 0){
            lineStorage = new ArrayList[fileCount];

            for(int i = 0; i < fileCount; i++){
                for(int j = 0; j < fileCount; j++){
                    similarityCount[i][j] = 0;
                }
            }
            // LINE TO ARRAY PUSHING
            pushLineContentsToArray();

            // LINE SIMILARITY CHECKER
            checkIndividualSimilarity();

            // SEARCH FOR THE LONGEST LINE SIMILARITY
            checkLongestSimilarity();

            // DISPLAY MATRIX AND SIMILARITY STATUSES
            displayMatrix();

            // DISPLAY ALL OF THE FILES
            displayFiles();

            bFile1.close();
            bFile2.close();

            if(fileCount < 2) toSimStats.setDisable(true);
        }

        else{
            Parent root = FXMLLoader.load(getClass().getResource("ErrNoFiles.fxml"));

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setTitle("ERROR!");
            stage.setScene(scene);
            stage.getIcons().add(new Image("imageAssets/POCKicon.png"));
            stage.show();

            AnchorPane nextAnchorPane = FXMLLoader.load(getClass().getResource("../Menu/SearchFile.fxml"));
            matrixAnchor.getChildren().setAll(nextAnchorPane);
        }

    }

    static void listFiles(File[] arr, int index, int level){
        if(index == arr.length) return;

        if(arr[index].isDirectory()){
            listFiles(arr[index].listFiles(), 0, level+1);
        }

        else if(arr[index].isFile()) {
            if (arr[index].getName().endsWith(".java") || arr[index].getName().endsWith(".cpp")){
                //System.out.println(arr[index].getPath());
                fileNames[fileCount] = arr[index].getName();
                filePath[fileCount] = arr[index].getPath();
                fileCount++;
            }
        }

        else if(arr[index].isDirectory()){
            //System.out.print(arr[index].getName() + "/");
            listFiles(arr[index].listFiles(), 0, level+1);
        }
        listFiles(arr, ++index, level);
    }

    private void pushLineContentsToArray() throws IOException {
        for(int i = 0; i < fileCount; i++){
            for(int j = 0; j < fileCount; j++){

                bFile1 = new BufferedReader(new FileReader(filePath[i]));

                ArrayList<String> arrayOfLines = new ArrayList<String>();
                lineCount1 = 0;
                while ((line1 = bFile1.readLine()) != null) {
                    line1SansSpaces = line1.replaceAll("\\s+", "");
                    arrayOfLines.add(line1SansSpaces);
                    lineCount1 = lineCount1 + 1;
                }

                lineStorage[i] = arrayOfLines;
            }
        }
    }

    private void checkIndividualSimilarity() throws IOException {
        for (int i = 0; i < fileCount; i++) {
            for (int j = 0; j < fileCount; j++) {
                if (lineStorage[i].size() <= lineStorage[j].size()) {
                    lineCount1 = 0;
                    while (lineCount1 < lineStorage[i].size()) {
                        lineCount2 = 0;
                        while (lineCount2 < lineStorage[j].size()) {
                            if (lineStorage[i].get(lineCount1).toLowerCase().equals(lineStorage[j].get(lineCount2).toLowerCase())) {
                                similarityCount[i][j] = similarityCount[i][j] + 1;
                                break;
                            }
                            lineCount2 = lineCount2 + 1;
                        }
                        lineCount1 = lineCount1 + 1;
                    }

                    totalLineCount[i][j] = lineStorage[i].size();
                    totalLineCount[j][i] = lineStorage[i].size();

                } else {
                    lineCount1 = 0;
                    while (lineCount1 < lineStorage[j].size()) {
                        lineCount2 = 0;
                        while (lineCount2 < lineStorage[i].size()) {
                            if (lineStorage[j].get(lineCount1).toLowerCase().equals(lineStorage[i].get(lineCount2).toLowerCase())) {
                                similarityCount[i][j] = similarityCount[i][j] + 1;
                                break;
                            }
                            lineCount2 = lineCount2 + 1;
                        }
                        lineCount1 = lineCount1 + 1;
                    }
                }

            }
        }
    }

    private void checkLongestSimilarity() throws IOException {
        for(int i = 0; i <= fileCount-1; i++){
            for(int j = 0; j <= fileCount-1; j++){
                bFile1 = new BufferedReader(new FileReader(filePath[i]));
                bFile2 = new BufferedReader(new FileReader(filePath[j]));
                while (((line1 = bFile1.readLine()) != null) && ((line2 = bFile2.readLine()) != null)) {
                    lineCount = 0;
                    lineCount = lineCount + 1;

                    line1SansSpaces = line1.replaceAll("\\s+","");
                    line2SansSpaces = line2.replaceAll("\\s+","");

                    if (line1SansSpaces.toLowerCase().equals(line2SansSpaces.toLowerCase())) {
                        if(!filePath[i].equals(filePath[j])){
                            currentCharacterCount = 0;
                            for(int k = 0; k < line1SansSpaces.length(); k++) {
                                currentCharacterCount++;
                            }
                            if (currentCharacterCount > longestCharacterCount){
                                longestCharacterCount = currentCharacterCount;
                                longestLine = line1;
                                longestLineDir1 = filePath[i];
                                longestLineDir2 = filePath[j];
                                longestLineFile1 = fileNames[i];
                                longestLineFile2 = fileNames[j];
                                //longestLineNum = lineCount;
                            }
                        }
                    }
                }
            }
        }
    }

    private void displayMatrix() {
        for(int i = 0; i < fileCount; i++){
            for(int j = 0; j < fileCount; j++){

                if (totalLineCount[i][j] == 0) similarityIndex[i][j] = 0;
                else similarityIndex[i][j] = similarityCount[i][j] / totalLineCount[i][j];

                Label correlationScore = new Label();
                correlationScore.setText(String.valueOf(df.format(similarityIndex[i][j])));
                VBox cellFormat = new VBox();
                correlationScore.setStyle("-fx-text-fill: #000000;");
                cellFormat.setAlignment(Pos.CENTER);

                Tooltip t = new Tooltip("  [" + (i + 1) + "] " + fileNames[i] + "  &  " + " [" + (j + 1) + "] " + fileNames[j] + " ");
                Tooltip.install(cellFormat, t);

                if(similarityIndex[i][j] <= 0) cellFormat.setStyle("-fx-border-color: #000000;\n" + "-fx-background-color: #ffffff;");
                else if(similarityIndex[i][j] <= 0.125) cellFormat.setStyle("-fx-border-color: #000000;\n" + "-fx-background-color: #cccccc;");
                else if(similarityIndex[i][j] <= 0.25) cellFormat.setStyle("-fx-border-color: #000000;\n" + "-fx-background-color: #ffffb3;");
                else if(similarityIndex[i][j] <= 0.375) cellFormat.setStyle("-fx-border-color: #000000;\n" + "-fx-background-color: #fed976;");
                else if(similarityIndex[i][j] <= 0.50) cellFormat.setStyle("-fx-border-color: #000000;\n" + "-fx-background-color: #feb34f;");
                else if(similarityIndex[i][j] <= 0.625) cellFormat.setStyle("-fx-border-color: #000000;\n" + "-fx-background-color: #fd8d3c;");
                else if(similarityIndex[i][j] <= 0.75) cellFormat.setStyle("-fx-border-color: #000000;\n" + "-fx-background-color: #fb4e29;");
                else if(similarityIndex[i][j] <= 0.875) cellFormat.setStyle("-fx-border-color: #000000;\n" + "-fx-border-color: black;\n" + "-fx-background-color: #e41a1c;");
                else if(similarityIndex[i][j] < 1){
                    cellFormat.setStyle("-fx-border-color: #000000;\n" + "-fx-background-color: #b10026;");
                    correlationScore.setStyle("-fx-text-fill: #ffffff;");
                }
                else if(similarityIndex[i][j] >= 1) {
                    cellFormat.setStyle("-fx-border-color: #000000;\n" + "-fx-background-color: #000000;");
                    correlationScore.setStyle("-fx-text-fill: #ffffff;");
                }

                cellFormat.getChildren().addAll(correlationScore);
                CorrelationMatrix.add(cellFormat, j, i);
            }
        }

        fileTotal.setText(" Total Number of Files:  " + fileCount);

        longestLineFile1 = longestLineFile1.replace("assets\\Submissions\\","");
        longestLineFile2 = longestLineFile2.replace("assets\\Submissions\\","");



        for (int i = 0; i < fileCount; i++) {
            for (int j = 0; j < fileCount; j++) {
                if (i != j) {
                    if (similarityIndex[i][j] > firstScore) {
                        fifthScore = fourthScore;
                        fourthScore = thirdScore;
                        thirdScore = secondScore;
                        secondScore = firstScore;
                        firstScore = similarityIndex[i][j];

                        topIndices[4][0] = topIndices[3][0];
                        topIndices[4][1] = topIndices[3][1];

                        topIndices[3][0] = topIndices[2][0];
                        topIndices[3][1] = topIndices[2][1];

                        topIndices[2][0] = topIndices[1][0];
                        topIndices[2][1] = topIndices[1][1];

                        topIndices[1][0] = topIndices[0][0];
                        topIndices[1][1] = topIndices[0][1];

                        topIndices[0][0] = i;
                        topIndices[0][1] = j;
                    }

                    else if (similarityIndex[i][j] > secondScore) {
                        fifthScore = fourthScore;
                        fourthScore = thirdScore;
                        thirdScore = secondScore;
                        secondScore = similarityIndex[i][j];

                        topIndices[4][0] = topIndices[3][0];
                        topIndices[4][1] = topIndices[3][1];

                        topIndices[3][0] = topIndices[2][0];
                        topIndices[3][1] = topIndices[2][1];

                        topIndices[2][0] = topIndices[1][0];
                        topIndices[2][1] = topIndices[1][1];

                        topIndices[1][0] = i;
                        topIndices[1][1] = j;
                    }

                    else if (similarityIndex[i][j] > thirdScore) {
                        fifthScore = fourthScore;
                        fourthScore = thirdScore;
                        thirdScore = similarityIndex[i][j];

                        topIndices[4][0] = topIndices[3][0];
                        topIndices[4][1] = topIndices[3][1];

                        topIndices[3][0] = topIndices[2][0];
                        topIndices[3][1] = topIndices[2][1];

                        topIndices[2][0] = i;
                        topIndices[2][1] = j;
                    }

                    else if (similarityIndex[i][j] > fourthScore) {
                        fifthScore = fourthScore;
                        fourthScore = similarityIndex[i][j];

                        topIndices[4][0] = topIndices[3][0];
                        topIndices[4][1] = topIndices[3][1];

                        topIndices[3][0] = i;
                        topIndices[3][1] = j;
                    }

                    else if (similarityIndex[i][j] > fifthScore) {
                        fifthScore = similarityIndex[i][j];

                        topIndices[4][0] = i;
                        topIndices[4][1] = j;
                    }
                }
            }
        }
    }

    private void displayFiles(){
        for(int i = 0; i < fileCount; i++){
            Label fileName = new Label();
            fileName.setText("  [" + (i + 1) + "] " + fileNames[i]);
            fileName.setStyle("-fx-text-fill: #5d5d4b;");

            VBox cellFormat = new VBox();
            cellFormat.setStyle("-fx-border-color: #5d5d4b;");

            Tooltip t = new Tooltip(filePath[i]);
            Tooltip.install(cellFormat, t);

            cellFormat.getChildren().addAll(fileName);
            FilesList.add(cellFormat, 0, i);
        }
    }

    public void longestStringStats(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LongestString.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("SIMILARITY STATISTICS");
        stage.setScene(scene);
        stage.getIcons().add(new Image("imageAssets/POCKicon.png"));
        stage.show();
    }

    public void programMetrics(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ProgramMetrics.fxml"));

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("PROGRAM METRICS");
        stage.setScene(scene);
        stage.getIcons().add(new Image("imageAssets/POCKicon.png"));
        stage.show();
    }

    public void getBack(ActionEvent actionEvent) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(getClass().getResource("../Menu/MenuUI.fxml"));
        matrixAnchor.getChildren().setAll(nextAnchorPane);
    }
}

