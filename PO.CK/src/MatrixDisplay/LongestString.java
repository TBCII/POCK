package MatrixDisplay;

import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;

public class LongestString extends Controller{
    public Text longestString;
    public Text fileFound1;
    public Text fileFound2;
    public Text lineMatched;
    public Text firstSimilar1;
    public Text firstSimilar2;
    public Text secondSimilar1;
    public Text secondSimilar2;
    public Text thirdSimilar1;
    public Text thirdSimilar2;
    public Text fourthSimilar1;
    public Text fourthSimilar2;
    public Text fifthSimilar1;
    public Text fifthSimilar2;
    public Text score1;
    public Text score2;
    public Text score3;
    public Text score4;
    public Text score5;
    public Text one;
    public Text two;
    public Text three;
    public Text four;
    public Text five;


    public void initialize(){
        if(fileCount >= 5){
            Tooltip tip00 = new Tooltip("File [" + (topIndices[0][0] + 1) + "] ");
            Tooltip tip01 = new Tooltip("File [" + (topIndices[0][1] + 1) + "] ");
            firstSimilar1.setText(fileNames[topIndices[0][0]]);
            firstSimilar2.setText(fileNames[topIndices[0][1]]);
            Tooltip.install(firstSimilar1, tip00);
            Tooltip.install(firstSimilar2, tip01);
            score1.setText(df.format(firstScore));

            Tooltip tip10 = new Tooltip("File [" + (topIndices[1][0] + 1) + "] ");
            Tooltip tip11 = new Tooltip("File [" + (topIndices[1][1] + 1) + "] ");
            secondSimilar1.setText(fileNames[topIndices[1][0]]);
            secondSimilar2.setText(fileNames[topIndices[1][1]]);
            Tooltip.install(secondSimilar1, tip10);
            Tooltip.install(secondSimilar2, tip11);
            score2.setText(df.format(secondScore));

            Tooltip tip20 = new Tooltip("File [" + (topIndices[2][0] + 1) + "] ");
            Tooltip tip21 = new Tooltip("File [" + (topIndices[2][1] + 1) + "] ");
            thirdSimilar1.setText(fileNames[topIndices[2][0]]);
            thirdSimilar2.setText(fileNames[topIndices[2][1]]);
            Tooltip.install(thirdSimilar1, tip20);
            Tooltip.install(thirdSimilar2, tip21);
            score3.setText(df.format(thirdScore));

            Tooltip tip30 = new Tooltip("File [" + (topIndices[3][0] + 1) + "] ");
            Tooltip tip31 = new Tooltip("File [" + (topIndices[3][1] + 1) + "] ");
            fourthSimilar1.setText(fileNames[topIndices[3][0]]);
            fourthSimilar2.setText(fileNames[topIndices[3][1]]);
            Tooltip.install(fourthSimilar1, tip30);
            Tooltip.install(fourthSimilar2, tip31);
            score4.setText(df.format(fourthScore));

            Tooltip tip40 = new Tooltip("File [" + (topIndices[4][0] + 1) + "] ");
            Tooltip tip41 = new Tooltip("File [" + (topIndices[4][1] + 1) + "] ");
            fifthSimilar1.setText(fileNames[topIndices[4][0]]);
            fifthSimilar2.setText(fileNames[topIndices[4][1]]);
            Tooltip.install(fifthSimilar1, tip40);
            Tooltip.install(fifthSimilar2, tip41);
            score5.setText(df.format(fifthScore));
        }

        else if(fileCount == 4){

            five.setOpacity(0);
            score5.setOpacity(0);

            Tooltip tip00 = new Tooltip("File [" + (topIndices[0][0] + 1) + "] ");
            Tooltip tip01 = new Tooltip("File [" + (topIndices[0][1] + 1) + "] ");
            firstSimilar1.setText(fileNames[topIndices[0][0]]);
            firstSimilar2.setText(fileNames[topIndices[0][1]]);
            Tooltip.install(firstSimilar1, tip00);
            Tooltip.install(firstSimilar2, tip01);
            score1.setText(df.format(firstScore));

            Tooltip tip10 = new Tooltip("File [" + (topIndices[1][0] + 1) + "] ");
            Tooltip tip11 = new Tooltip("File [" + (topIndices[1][1] + 1) + "] ");
            secondSimilar1.setText(fileNames[topIndices[1][0]]);
            secondSimilar2.setText(fileNames[topIndices[1][1]]);
            Tooltip.install(secondSimilar1, tip10);
            Tooltip.install(secondSimilar2, tip11);
            score2.setText(df.format(secondScore));

            Tooltip tip20 = new Tooltip("File [" + (topIndices[2][0] + 1) + "] ");
            Tooltip tip21 = new Tooltip("File [" + (topIndices[2][1] + 1) + "] ");
            thirdSimilar1.setText(fileNames[topIndices[2][0]]);
            thirdSimilar2.setText(fileNames[topIndices[2][1]]);
            Tooltip.install(thirdSimilar1, tip20);
            Tooltip.install(thirdSimilar2, tip21);
            score3.setText(df.format(thirdScore));

            Tooltip tip30 = new Tooltip("File [" + (topIndices[3][0] + 1) + "] ");
            Tooltip tip31 = new Tooltip("File [" + (topIndices[3][1] + 1) + "] ");
            fourthSimilar1.setText(fileNames[topIndices[3][0]]);
            fourthSimilar2.setText(fileNames[topIndices[3][1]]);
            Tooltip.install(fourthSimilar1, tip30);
            Tooltip.install(fourthSimilar2, tip31);
            score4.setText(df.format(fourthScore));
        }

        else if(fileCount == 3){

            four.setOpacity(0);
            five.setOpacity(0);
            score5.setOpacity(0);
            score4.setOpacity(0);

            Tooltip tip00 = new Tooltip("File [" + (topIndices[0][0] + 1) + "] ");
            Tooltip tip01 = new Tooltip("File [" + (topIndices[0][1] + 1) + "] ");
            firstSimilar1.setText(fileNames[topIndices[0][0]]);
            firstSimilar2.setText(fileNames[topIndices[0][1]]);
            Tooltip.install(firstSimilar1, tip00);
            Tooltip.install(firstSimilar2, tip01);
            score1.setText(df.format(firstScore));

            Tooltip tip10 = new Tooltip("File [" + (topIndices[1][0] + 1) + "] ");
            Tooltip tip11 = new Tooltip("File [" + (topIndices[1][1] + 1) + "] ");
            secondSimilar1.setText(fileNames[topIndices[1][0]]);
            secondSimilar2.setText(fileNames[topIndices[1][1]]);
            Tooltip.install(secondSimilar1, tip10);
            Tooltip.install(secondSimilar2, tip11);
            score2.setText(df.format(secondScore));

            Tooltip tip20 = new Tooltip("File [" + (topIndices[2][0] + 1) + "] ");
            Tooltip tip21 = new Tooltip("File [" + (topIndices[2][1] + 1) + "] ");
            thirdSimilar1.setText(fileNames[topIndices[2][0]]);
            thirdSimilar2.setText(fileNames[topIndices[2][1]]);
            Tooltip.install(thirdSimilar1, tip20);
            Tooltip.install(thirdSimilar2, tip21);
            score3.setText(df.format(thirdScore));
        }

        else if(fileCount == 2){

            three.setOpacity(0);
            four.setOpacity(0);
            five.setOpacity(0);
            score5.setOpacity(0);
            score4.setOpacity(0);
            score3.setOpacity(0);

            Tooltip tip00 = new Tooltip("File [" + (topIndices[0][0] + 1) + "] ");
            Tooltip tip01 = new Tooltip("File [" + (topIndices[0][1] + 1) + "] ");
            firstSimilar1.setText(fileNames[topIndices[0][0]]);
            firstSimilar2.setText(fileNames[topIndices[0][1]]);
            Tooltip.install(firstSimilar1, tip00);
            Tooltip.install(firstSimilar2, tip01);
            score1.setText(df.format(firstScore));

            Tooltip tip10 = new Tooltip("File [" + (topIndices[1][0] + 1) + "] ");
            Tooltip tip11 = new Tooltip("File [" + (topIndices[1][1] + 1) + "] ");
            secondSimilar1.setText(fileNames[topIndices[1][0]]);
            secondSimilar2.setText(fileNames[topIndices[1][1]]);
            Tooltip.install(secondSimilar1, tip10);
            Tooltip.install(secondSimilar2, tip11);
            score2.setText(df.format(secondScore));
        }

        else if(fileCount == 1){

            two.setOpacity(0);
            three.setOpacity(0);
            four.setOpacity(0);
            five.setOpacity(0);
            score2.setOpacity(0);
            score3.setOpacity(0);
            score4.setOpacity(0);
            score5.setOpacity(0);


            Tooltip tip00 = new Tooltip("File [" + (topIndices[0][0] + 1) + "] ");
            Tooltip tip01 = new Tooltip("File [" + (topIndices[0][1] + 1) + "] ");
            firstSimilar1.setText(fileNames[topIndices[0][0]]);
            firstSimilar2.setText(fileNames[topIndices[0][1]]);
            Tooltip.install(firstSimilar1, tip00);
            Tooltip.install(firstSimilar2, tip01);
            score1.setText(df.format(firstScore));
        }

        longestString.setText(longestLine);
        fileFound1.setText(longestLineFile1);
        fileFound2.setText(longestLineFile2);

        Tooltip ttipF1 = new Tooltip(longestLineDir1);
        Tooltip.install(fileFound1, ttipF1);

        Tooltip ttipF2 = new Tooltip(longestLineDir1);
        Tooltip.install(fileFound1, ttipF2);

        //lineMatched.setText(String.valueOf(longestLineNum));
    }
}
