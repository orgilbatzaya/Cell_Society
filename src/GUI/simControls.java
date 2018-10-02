package GUI;

import Simulation.Simulation;
import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.util.ResourceBundle;

/**
 * simControls
 *
 * creates an Hbox with various buttons,
 * Start, Stop, Step, and Reset
 *
 * @author Brooke Keene
 */
public class simControls {
    private Stage myStage;
    private ResourceBundle myResources;

    private Button startBtn;
    private Button stopBtn;
    private Button stepBtn;
    private Button fileBtn;

    private int buttonPadding = 20;
    private int borderPadding = 10;
    private int sideMenuPadding = 200;

    private int mySimSpeed;
    private int startSpeed = 5;
    private int minSpeed = 1;
    private int maxSpeed = 10;
    private Label speedLabel;

    private boolean fileFlag;   // flag for if user has uploaded a new file
    private File myFile;
    private Label fileName;
    private Simulation mySim;
    private VBox myVbox;

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
     * @return fileFlag, true if user has selected a file,
     * otherwise false
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
     *
     * @return
     */
    public Button getStepBtn() {
        return stepBtn;
    }

    /**
     * creates a HBox with start, stop, step, and reset buttons
     * and adds the HBox to myBorder
     */
    public HBox addButtons() {
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
     * creates VBox to contain FileChooser
     */
    public VBox makeSideMenu() {
        myVbox = new VBox(borderPadding);
        myVbox.setPrefWidth(sideMenuPadding);
        myVbox.setPadding(new Insets(borderPadding));
        myVbox.setAlignment(Pos.TOP_CENTER);

        this.addFileChooser();
        this.addSliders();

        return myVbox;
    }

    /**
     * creates speed control slider
     */
    public void addSliders() {
        Slider simSpeed = new Slider(minSpeed, maxSpeed, startSpeed);
        mySimSpeed = startSpeed;
        simSpeed.setMajorTickUnit(1);
        speedLabel = new Label(Double.toString(simSpeed.getValue()));
        speedLabel.setId("speedLbl");
        simSpeed.valueProperty().addListener(this.addSliderListener());

        myVbox.getChildren().addAll(simSpeed, speedLabel);
    }

    /**
     * creates FileChooser and button associated with it
     */
    public void addFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(myResources.getString("FileWindow"));

        fileBtn = new Button(myResources.getString("File"));
        fileName = new Label();
        fileName.setId("fileName");

        this.setFileButtonFunctionality(fileChooser);

        myVbox.getChildren().add(fileBtn);
        myVbox.getChildren().add(fileName);
    }

    /**
     *
     * @param fc
     */
    private void setFileButtonFunctionality(FileChooser fc) {
        fileBtn.setOnAction(value -> {
            //TODO: exceptions if wrong type of file
            File file = fc.showOpenDialog(myStage);
            // saves File if no exceptions and File is not null
            if(file != null) {
                fileFlag = true;
                myFile = file;
                fileName.setText(myFile.toString());
                mySim.stop();
            }
        });
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