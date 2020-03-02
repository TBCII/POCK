package MatrixDisplay;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProgramMetrics extends Controller{

    public GridPane metricStats;
    public Text vocab;
    public Text length;
    public Text calcLen;
    public Text vol;
    public Text diff;
    public Text eff;
    public Text time;
    public Text bugs;


    public void initialize() throws IOException {

        Tooltip t1 = new Tooltip("The total number of unique operator and unique operand occurrences.");
        Tooltip.install(vocab, t1);

        Tooltip t2 = new Tooltip("The total number of operator occurrences and the total number of operand occurrences.");
        Tooltip.install(length, t2);

        Tooltip t3 = new Tooltip("Estimated program length.");
        Tooltip.install(calcLen, t3);

        Tooltip t4 = new Tooltip("Proportional to program size, represents the size, in bits, of space necessary for storing the program.");
        Tooltip.install(vol, t4);

        Tooltip t5 = new Tooltip("This parameter shows how difficult to handle the program is.");
        Tooltip.install(diff, t5);

        Tooltip t6 = new Tooltip("Measures the amount of mental activity needed to translate the existing algorithm into implementation in the specified program language.");
        Tooltip.install(eff, t6);

        Tooltip t7 = new Tooltip("Shows time (in minutes) needed to translate the existing algorithm into implementation in the specified program language.");
        Tooltip.install(time, t7);

        Tooltip t8 = new Tooltip("Estimate for the number of errors in the implementation.");
        Tooltip.install(bugs, t8);

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
            progTime.setText(df.format(hal.getTimeReqProg()));
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
