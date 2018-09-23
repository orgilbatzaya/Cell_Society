package GUI;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
    private VBox myVbox;
    private File myFile;
    private int mySimSpeed;
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
     *
     * @return
     */
    public int getSimSpeed() {
        return mySimSpeed;
    }

    /**
     * creates VBox to contain FileChooser
     */
    public void makeSideMenu() {
        myVbox = new VBox(borderPadding);
        myVbox.setPrefWidth(sideMenuPadding);
        myVbox.setPadding(new Insets(borderPadding));
        myVbox.setAlignment(Pos.TOP_CENTER);

        this.addFileChooser();
        this.addSliders();

        myBorder.setRight(myVbox);
    }

    private void addFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(myResources.getString("FileWindow"));

        // creates a button that opens the FileChooser
        Button fileBtn = new Button(myResources.getString("File"));
        Label fileName = new Label();
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

        myVbox.getChildren().add(fileBtn);
        myVbox.getChildren().add(fileName);
    }

    private void addSliders() {
        Slider simSpeed = new Slider(0, 100, 0);
        simSpeed.setMajorTickUnit(1);
        Label speedLabel = new Label(Double.toString(simSpeed.getValue()));
        speedLabel.setId("speedLbl");
        simSpeed.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mySimSpeed = newValue.intValue();
                speedLabel.setText(String.format("%.0f", newValue));
            }
        });

        myVbox.getChildren().addAll(simSpeed, speedLabel);

    }
}
