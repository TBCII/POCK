package Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SearchFile extends MenuController{

    public AnchorPane searchAnchor;
    public Button proceedToMatrix;
    public Text dirPath;
    public Text initializingText;

    public void fileBrowse(ActionEvent actionEvent) {
        final DirectoryChooser dirChoose = new DirectoryChooser();
        Stage stage = (Stage) searchAnchor.getScene().getWindow();
        File file = dirChoose.showDialog(stage);

        if(file != null){
            dirPath.setText(file.getAbsolutePath());
            absoluteFolderPath = file.getAbsolutePath();
            proceedToMatrix.setDisable(false);
        }
    }

    public void proceedToMatrix(ActionEvent actionEvent) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(getClass().getResource("../MatrixDisplay/MatrixDisplay.fxml"));
        searchAnchor.getChildren().setAll(nextAnchorPane);
    }

    public void getBack(ActionEvent actionEvent) throws IOException {
        AnchorPane nextAnchorPane = FXMLLoader.load(getClass().getResource("MenuUI.fxml"));
        searchAnchor.getChildren().setAll(nextAnchorPane);
    }
}