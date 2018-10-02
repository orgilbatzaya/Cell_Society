package GUI;

import java.io.File;
import Simulation.Simulation;
import XML.XMLParser;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * UISetup
 *
 * creates a scene for UI, adds other UI elements,
 * and includes methods to return a scene
 *
 * @author Brooke Keene
 * @author Amy Kim
 */
public class UISetup {
    public static final String RESOURCE_PACKAGE = "resources/";
    public static final String UI_TEXT = "English";
    public static final String STYLESHEET = "default.css";
    public static final String DEFAULT_TYPE = "Life";
    public static final String DEFAUlT_SHAPE = "Square";
    public static final String DEFAULT_EDGE = "Finite";
    public static final int DEFAULT_GRID = 4;
    public static final double DEFAULT_INTERVAL = 10.0;

    private Stage myStage;
    private Scene myScene;
    private Group myRoot;
    private BorderPane myBorder;

    private ResourceBundle myResources;

    private simGrid myGrid;
    private simControls myControls;
    private gridControls myGridControls;
    private Simulation mySimulation;

    private String simType;
    private String gridShape;
    private String gridEdge;
    private int gridSize;

    private int borderPadding = 10;
    private int sideMenuPadding = 200;

    private Map<String, Double> simParam;
    private Map<String, Double> simParams;
    private Map<String, Double> simMoreParams;
    private Map<String, Double> simOneParam;

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
        myResources = ResourceBundle.getBundle(RESOURCE_PACKAGE + UI_TEXT);
        myScene.getStylesheets().add(STYLESHEET);

        simType = DEFAULT_TYPE;
        gridShape = DEFAUlT_SHAPE;
        gridEdge = DEFAULT_EDGE;
        gridSize = DEFAULT_GRID;
        simParam = new HashMap<>();
        simParams = new HashMap<>();
        simMoreParams = new HashMap<>();
        simOneParam = new HashMap<>();
    }

    /**
     * initializes the GUI when called by Main
     */
    public void initializeUI() {
        this.makeBorderPane();
        HBox top = new HBox();
        VBox sideMenu = new VBox();
        HBox bottom;

        mySimulation = new Simulation();

        myControls = new simControls(mySimulation, myStage, myResources);

        // add bottom buttons
        bottom = myControls.addSimButtons();
        Button myStepBtn = myControls.getStepBtn();
        myStepBtn.setOnAction(value ->  this.updateMyGrid());

        // add side menu elements
        sideMenu.setPrefWidth(sideMenuPadding);
        sideMenu.setPadding(new Insets(borderPadding));
        sideMenu.setAlignment(Pos.TOP_CENTER);

        sideMenu.getChildren().add(myControls.addFileChooser());
        sideMenu.getChildren().addAll(myControls.addSpeedSlider(), myControls.getSpeedLabel());

        // add top buttons
        myGridControls = new gridControls(myBorder, myResources);
        myGridControls.addShapeChoice();
        myGridControls.addTypeBtns();

        // add grid
        myGrid = new simGrid(gridSize, gridShape, gridEdge, simType, simParam, simParams, simMoreParams, simOneParam);

        // add all elements to BorderPane
        myBorder.setTop(top);
        myBorder.setRight(sideMenu);
        myBorder.setCenter(myGrid.makeGrid());
        myBorder.setBottom(bottom);
    }

    /**
     * animates GUI when called in Main game loop
     *
     * @param duration
     */
    public void tickTock(double duration) {
        // checks if new xml file has been added by user
        if(myControls.getFileFlag()) {
            this.resetGUI();
            this.getXMLParameters();
            initializeUI();
        }
        // checks if different button has been selected
        if(myGridControls.getChangeFlag()) {

        }
        // checks if simulation should be playing
        if(mySimulation.isPlaying()) {
            double interval = DEFAULT_INTERVAL/(double) myControls.getSimSpeed();
            mySimulation.incrementTimer(duration);
            if(interval < mySimulation.getTimer()) {
                mySimulation.resetTimer();
                this.updateMyGrid();
            }
        }
    }

    /**
     * returns Scene when called by Main
     *
     * @return myScene, the Scene created by this UISetup class
     */
    public Scene getScene() {
        return myScene;
    }

    /**
     * updates simGrid object
     */
    private void updateMyGrid() {
        myBorder.setCenter(null);
        myBorder.setCenter(myGrid.updateGrid());
    }


    /**
     * creates BorderPane object to hold other UI controls
     */
    private void makeBorderPane() {
        myBorder.prefHeightProperty().bind(myScene.heightProperty());
        myBorder.prefWidthProperty().bind(myScene.widthProperty());
        myBorder.setPadding(new Insets(borderPadding));
        myRoot.getChildren().add(myBorder);
    }

    /**
     * resets GUI by removing children of myRoot
     */
    private void resetGUI() {
        myRoot.getChildren().remove(myBorder);
    }

    /**
     * parses XML file given to GUI by user
     */
    private void getXMLParameters() {
        File file = myControls.getFile();
        XMLParser xmlParser = new XMLParser(file);
        xmlParser.readFile();
        simType = xmlParser.getSimulation();
        gridSize = xmlParser.getGridSize();
        if(simType.equals("Life")) {
            // no parameters!
        }
        else if(simType.equals("Seg")) {
            simParams.put("satisfaction", xmlParser.getParameter("satisfaction"));
            simMoreParams.put("empty", xmlParser.getParameter("empty"));
            simParam.put("red", xmlParser.getParameter("red"));
        }
        else if(simType.equals("Fire")) {
            simParams.put("probability", xmlParser.getParameter("probability"));
        }
        else if(simType.equals("WaTor")) {
            simParams.put("shark", xmlParser.getParameter("shark"));
            simMoreParams.put("empty", xmlParser.getParameter("empty"));
            simParam.put("breeding", xmlParser.getParameter("breeding"));
            simOneParam.put("energy", xmlParser.getParameter("energy"));
        }
        else if(simType.equals("RPS")) {
            // no parameters!
        }
//        else {
//            //TODO: throw exception
//            System.out.println("Exception");
//        }
    }
}