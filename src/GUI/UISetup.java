package GUI;

import GUI.simGrid;
import java.io.File;

import Simulation.Simulation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.util.ResourceBundle;


/**
 * UISetup
 *
 * creates a scene for UI, adds other UI elements,
 * and includes methods to return a scene
 *
 * @author Brooke Keene
 */
public class UISetup {
    public static final String RESOURCE_PACKAGE = "resources/";
    public static final String UI_TEXT = "English";
    public static final String STYLESHEET = "default.css";
    public static final double DEFAULT_INTERVAL = 0.5;

    private Stage myStage;
    private Scene myScene;
    private Group myRoot;
    private BorderPane myBorder;


    private Simulation mySimulation;
    private simControls myControls;
    private simSelectMenu myMenu;
    private simGrid myGrid;

    private ResourceBundle myResources;

    private int borderSpace = 10;

    /**
     * Constructor
     *
     * @param width
     * @param height
     * @param color
     */
    public UISetup(int width, int height, Paint color, Stage stage) {
        myRoot = new Group();
        myStage = stage;
        myScene = new Scene(myRoot, width, height, color);
        myBorder = new BorderPane();
    }

    /**
     * initializes the GUI when called by Main
     */
    public void initializeUI() {
        myResources = ResourceBundle.getBundle(RESOURCE_PACKAGE + UI_TEXT);
        myScene.getStylesheets().add(STYLESHEET);

        this.makeBorderPane();

        myMenu = new simSelectMenu(myStage, myBorder, myResources);
        myMenu.makeSideMenu();

        myGrid = new simGrid(8, "life", myBorder);

        mySimulation = new Simulation(myGrid, DEFAULT_INTERVAL);

        myControls = new simControls(mySimulation, myBorder, myResources);
        myControls.addButtons();

    }

    public void tickTock(double duration) {
        mySimulation.ticktock(duration);
    }

    /**
     * returns Scene when called by Main
     *
     * @return myScene, the Scene created by this UISetup class
     */
    public Scene getScene() {
        return myScene;
    }

    public simGrid getMyGrid() {
        return myGrid;
    }

    /**
     *
     * @return the File selected by the user
     */
    public File getUserFile() {
        return myMenu.getFile();
    }

    /**
     * creates BorderPane object to hold other UI controls
     */
    private void makeBorderPane() {
        myBorder.prefHeightProperty().bind(myScene.heightProperty());
        myBorder.prefWidthProperty().bind(myScene.widthProperty());
        myBorder.setPadding(new Insets(borderSpace));
        myRoot.getChildren().add(myBorder);
    }
}