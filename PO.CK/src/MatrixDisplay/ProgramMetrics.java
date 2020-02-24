package MatrixDisplay;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ProgramMetrics extends Controller{

    public GridPane metricStats;

    public void initialize() throws IOException {
        for (int i = 0; i < fileCount; i++) {

            HalsteadMetrics hal = MetricMachine.getMetrics(filePath[i]);

            Label fileName = new Label();
            fileName.setText("\t[" + (i + 1) + "] " + fileNames[i]);
            fileName.setStyle("-fx-text-fill: #5d5d4b;");
            VBox cellFormat0 = new VBox();
            cellFormat0.setStyle("-fx-border-color: #5d5d4b;");
            cellFormat0.getChildren().addAll(fileName);

            Label progVocabulary = new Label();
            progVocabulary.setText(String.valueOf(hal.getVocabulary()));
            progVocabulary.setStyle("-fx-text-fill: #5d5d4b;");
            VBox cellFormat1 = new VBox();
            cellFormat1.setAlignment(Pos.CENTER);
            cellFormat1.setStyle("-fx-border-color: #5d5d4b;");
            cellFormat1.getChildren().addAll(progVocabulary);

            Label progLength = new Label();
            progLength.setText(String.valueOf(hal.getProglen()));
            progLength.setStyle("-fx-text-fill: #5d5d4b;");
            VBox cellFormat2 = new VBox();
            cellFormat2.setAlignment(Pos.CENTER);
            cellFormat2.setStyle("-fx-border-color: #5d5d4b;");
            cellFormat2.getChildren().addAll(progLength);

            Label progCalcLen = new Label();
            progCalcLen.setText(df.format(hal.getCalcProgLen()));
            progCalcLen.setStyle("-fx-text-fill: #5d5d4b;");
            VBox cellFormat3 = new VBox();
            cellFormat3.setAlignment(Pos.CENTER);
            cellFormat3.setStyle("-fx-border-color: #5d5d4b;");
            cellFormat3.getChildren().addAll(progCalcLen);

            Label progVolume = new Label();
            progVolume.setText(df.format(hal.getVolume()));
            progVolume.setStyle("-fx-text-fill: #5d5d4b;");
            VBox cellFormat4 = new VBox();
            cellFormat4.setAlignment(Pos.CENTER);
            cellFormat4.setStyle("-fx-border-color: #5d5d4b;");
            cellFormat4.getChildren().addAll(progVolume);

            Label progDifficulty = new Label();
            progDifficulty.setText(df.format(hal.getDifficulty()));
            progDifficulty.setStyle("-fx-text-fill: #5d5d4b;");
            VBox cellFormat5 = new VBox();
            cellFormat5.setAlignment(Pos.CENTER);
            cellFormat5.setStyle("-fx-border-color: #5d5d4b;");
            cellFormat5.getChildren().addAll(progDifficulty);

            Label progEffort = new Label();
            progEffort.setText(df.format(hal.getEffort()));
            progEffort.setStyle("-fx-text-fill: #5d5d4b;");
            VBox cellFormat6 = new VBox();
            cellFormat6.setAlignment(Pos.CENTER);
            cellFormat6.setStyle("-fx-border-color: #5d5d4b;");
            cellFormat6.getChildren().addAll(progEffort);

            Label progTime = new Label();
            progTime.setText(df.format(hal.getTimeReqProg()) + " sec.");
            progTime.setStyle("-fx-text-fill: #5d5d4b;");
            VBox cellFormat7 = new VBox();
            cellFormat7.setAlignment(Pos.CENTER);
            cellFormat7.setStyle("-fx-border-color: #5d5d4b;");
            cellFormat7.getChildren().addAll(progTime);

            Label progBugs = new Label();
            progBugs.setText(df.format(hal.getTimeDelBugs()));
            progBugs.setStyle("-fx-text-fill: #5d5d4b;");
            VBox cellFormat8 = new VBox();
            cellFormat8.setAlignment(Pos.CENTER);
            cellFormat8.setStyle("-fx-border-color: #5d5d4b;");
            cellFormat8.getChildren().addAll(progBugs);

            metricStats.add(cellFormat0, 0, i+1);
            metricStats.add(cellFormat1, 1, i+1);
            metricStats.add(cellFormat2, 2, i+1);
            metricStats.add(cellFormat3, 3, i+1);
            metricStats.add(cellFormat4, 4, i+1);
            metricStats.add(cellFormat5, 5, i+1);
            metricStats.add(cellFormat6, 6, i+1);
            metricStats.add(cellFormat7, 7, i+1);
            metricStats.add(cellFormat8, 8, i+1);
        }
    }

}
