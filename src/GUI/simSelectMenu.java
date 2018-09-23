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

    /**
     * Constructor
     *
     * @param stage
     * @param border
     * @param resources
     */
    public simSelectMenu(Stage stage, BorderPane border, ResourceBundle resources) {
        myStage = stage;
        myResources = resources;
        myBorder = border;
        fileName = new Label();
    }

    /**
     * returns user selected File
     *
     * @return myFile, an appropriate file selected by user
     */
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

        // creates a button that opens the FileChooser
        fileBtn = new Button(myResources.getString("File"));
        fileBtn.setOnAction(value -> {
            //TODO: reset xml file
            //TODO: exceptions if wrong type of file
            File file = fileChooser.showOpenDialog(myStage);
            // saves File if no exceptions and File is not null
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
