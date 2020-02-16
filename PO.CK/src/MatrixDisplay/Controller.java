package MatrixDisplay;

import Menu.MenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Controller extends Menu.MenuController {

    public static String[] filePath = new String[120];
    public static int fileCount = 0;

    public AnchorPane matrixAnchor;
    public GridPane CorrelationMatrix;
    public ScrollPane scrollPane;
    public Text longestString;
    public Text fileFound;
    public Text lineMatched;

    BufferedReader bFile1 = null;
    BufferedReader bFile2 = null;

    String line1SansSpaces;
    String line2SansSpaces = "";
    String line1;
    String line2 = "";
    String longestLine = "";
    String longestLineFile1 = "";
    String longestLineFile2 = "";

    int currentCharacterCount = 0;
    int longestCharacterCount = 0;
    int lineCount = 0;
    int longestLineNum = 0;

    int lineCount1;
    int lineCount2;

    double[][] similarityCount = new double[120][120];
    double[][] totalLineCount = new double[120][120];
    double[][] similarityIndex = new double[120][120];

    ArrayList<String>[] lineStorage;

    DecimalFormat df = new DecimalFormat("0.00");

    String directoryPath = absoluteFolderPath;
    File submissionsFolder = new File(directoryPath);

    public void initialize() throws IOException {

        System.out.println("I N I T I A L I Z I N G");

        if(submissionsFolder.exists() && submissionsFolder.isDirectory()){
            File arr[] = submissionsFolder.listFiles();

            listFiles(arr, 0 ,0);
        }

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

        bFile1.close();
        bFile2.close();
    }

    static void listFiles(File[] arr, int index, int level){
        if(index == arr.length) return;

        if(arr[index].isDirectory()){
            listFiles(arr[index].listFiles(), 0, level+1);
        }

        else if(arr[index].isFile()) {
            if (arr[index].getName().endsWith(".java") || arr[index].getName().endsWith(".cpp")){
                //System.out.println(arr[index].getPath());
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
                                longestLineFile1 = filePath[i];
                                longestLineFile2 = filePath[j];
                                longestLineNum = lineCount;
                            }
                        }
                    }
                }
            }
        }
    }

    private void displayMatrix() throws IOException {
        for(int i = 0; i < fileCount; i++){
            for(int j = 0; j < fileCount; j++){
                similarityIndex[i][j] = similarityCount[i][j] / totalLineCount[i][j];
                Label correlationScore = new Label();
                correlationScore.setText(String.valueOf(df.format(similarityIndex[i][j])));
                VBox cellFormat = new VBox();
                correlationScore.setStyle("-fx-text-fill: #000000;");
                cellFormat.setAlignment(Pos.CENTER);

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

        longestLineFile1 = longestLineFile1.replace("assets\\Submissions\\","");
        longestLineFile2 = longestLineFile2.replace("assets\\Submissions\\","");

        longestString.setText(longestLine);
        fileFound.setText(longestLineFile1 + "\t\tAND\t\t" + longestLineFile2);
        lineMatched.setText(String.valueOf(longestLineNum));
    }

    public void getBack(ActionEvent actionEvent) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(getClass().getResource("../Menu/MenuUI.fxml"));
        matrixAnchor.getChildren().setAll(nextAnchorPane);
    }
}

