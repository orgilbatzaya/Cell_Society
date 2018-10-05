package GUI;

import Simulation.Simulation;
import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * simControls
 *
 * creates an Hbox with various buttons,
 * Start, Stop, Step, and Reset
 *
 * @author Brooke Keene
 * @author Amy Kim
 */
public class simControls {
    private Stage myStage;
    private ResourceBundle myResources;

    private Button startBtn;
    private Button stopBtn;
    private Button stepBtn;
    private Button fileBtn;

    private int buttonPadding = 20;

    private int mySimSpeed;
    private int startSpeed = 5;
    private int minSpeed = 1;
    private int maxSpeed = 10;
    private Label speedLabel;

    private boolean fileFlag;   // flag for if user has uploaded a new file
    private File myFile;
    private Label fileName;
    private Simulation mySim;

    /**
     * Constructor
     *
     * @param sim Simulation object
     * @param stage Stage that contains all UI elements
     * @param resources ResourceBundle, contains keys for button text
     */
    public simControls(Simulation sim, Stage stage, ResourceBundle resources) {
        mySim = sim;
        myStage = stage;
        myResources = resources;

        fileFlag = false;
    }

    /**
     * returns whether user selected File
     *
     * @return fileFlag, true if user has selected a file, otherwise false
     */
    public boolean getFileFlag() {
        return fileFlag;
    }


    /**
     * returns File uploaded by user
     *
     * @return myFile, as selected in FileChooser
     */
    public File getFile() {
        return myFile;
    }

    /**
     * returns the user specified simulation speed
     *
     * @return
     */
    public int getSimSpeed() {
        return mySimSpeed;
    }

    /**
     * returns the label of the speed slider
     *
     * @return Label
     */
    public Label getSpeedLabel() {
        return speedLabel;
    }

    /**
     * returns the button that controls stepping through the simulation
     *
     * @return Button
     */
    public Button getStepBtn() {
        return stepBtn;
    }

    /**
     * creates a HBox with start, stop, step, and reset buttons
     */
    public HBox addSimButtons() {
        HBox bottomRow = new HBox(buttonPadding);
        bottomRow.setPadding(new Insets(buttonPadding));
        bottomRow.setAlignment(Pos.CENTER_LEFT);

        startBtn = new Button(myResources.getString("Start"));
        startBtn.setOnAction(value ->  mySim.start());

        stopBtn = new Button(myResources.getString("Stop"));
        stopBtn.setOnAction(value ->  mySim.stop());

        stepBtn = new Button(myResources.getString("Step"));

        bottomRow.getChildren().addAll(startBtn, stopBtn, stepBtn);
        return bottomRow;
    }

    /**
     * creates FileChooser and button associated with it
     */
    public Node addFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(myResources.getString("FileWindow"));

        fileBtn = new Button(myResources.getString("File"));
        fileName = new Label();
        fileName.setId("fileName");

        this.setFileButtonFunctionality(fileChooser);

        return fileBtn;
    }

    /**
     * creates simulation speed control slider
     */
    public Node addSpeedSlider() {
        Slider simSpeed = new Slider(minSpeed, maxSpeed, startSpeed);
        mySimSpeed = startSpeed;
        simSpeed.setMajorTickUnit(1);
        speedLabel = new Label(Double.toString(simSpeed.getValue()));
        speedLabel.setId("speedLbl");
        simSpeed.valueProperty().addListener(this.addSliderListener());

        return simSpeed;
    }

    /**
     * handles the user selection of a file, calls errorDetect
     * to ensure it is an XML file, and if so saves the file
     * sets flag to true, and stops the simulation timer
     *
     * @param fc FileChooser
     */
    private void setFileButtonFunctionality(FileChooser fc) {
        fileBtn.setOnAction(value -> {
            File file = fc.showOpenDialog(myStage);
            errorDetect(file);
            // saves File if no exceptions and File is not null
            if(file.toString().contains(".xml")) { //When got the input as XML file.
                fileFlag = true;
                myFile = file;
                fileName.setText(myFile.toString());
                mySim.stop();
            }
        });
    }

    /**
     * gives an error message when the user choses a non-XML file
     *
     * @param file
     */
    private void errorDetect(File file) {
        if(!file.toString().contains(".xml")){
            mySim.stop();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("ERROR!");
            errorAlert.setContentText("Incorrect File Type. Choose XML file.");
            errorAlert.showAndWait();
        }
    }

    /**
     * creates a listener that reacts when user interacts with slider
     */
    private ChangeListener<Number> addSliderListener() {
        return new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mySimSpeed = newValue.intValue();
                speedLabel.setText(String.format("%.0f", newValue));
            }
        };
    }
}