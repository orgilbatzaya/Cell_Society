package GUI;

import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.util.ResourceBundle;

//import javafx.scene.control.Label;

/**
 * simSelectMenu
 *
 * creates a Vbox with a FileChooser
 *
 * @author Brooke Keene
 */
public class simSelectMenu {
    private Stage myStage;
    private ResourceBundle myResources;
    private BorderPane myBorder;
    private Button fileBtn;
    private File myFile;
    private Label fileName;
    private int borderPadding = 10;
    private int sideMenuPadding = 200;

    public simSelectMenu(Stage stage, BorderPane border, ResourceBundle resources) {
        myStage = stage;
        myResources = resources;
        myBorder = border;
        fileName = new Label();
    }

    public File getFile() {
        return myFile;
    }

    /**
     * creates VBox to contain FileChooser
     */
    public void makeSideMenu() {
        VBox right = new VBox(borderPadding);
        right.setPrefWidth(sideMenuPadding);
        right.setPadding(new Insets(borderPadding));
        right.setAlignment(Pos.TOP_CENTER);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(myResources.getString("FileWindow"));

        fileBtn = new Button(myResources.getString("File"));
        fileBtn.setOnAction(value -> {
            //TODO: reset xml file
            File file = fileChooser.showOpenDialog(myStage);
            if(file != null) {
                myFile = file;
                fileName.setText(myFile.toString());
            }
        });
        right.getChildren().add(fileBtn);
        right.getChildren().add(fileName);

        myBorder.setRight(right);
    }
}
