package GUI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.geometry.Insets;


/**
 * UISetup
 *
 * creates a scene for UI, adds other UI elements,
 * and includes methods to return a scene
 *
 * @author Brooke Keene
 */
public class UISetup {
    private Scene myScene;
    private Group myRoot;
    private BorderPane myBorder;

    private Button startBtn;
    private Button stopBtn;
    private Button stepBtn;
    private Button resetBtn;

    private ChoiceBox simSelect;

    private Slider simSpeed;
    private Slider gridSize;

    private int buttonSpace = 20;
    private int borderSpace = 10;
    private int sideMenuSpace = 200;

    /**
     * Constructor
     *
     * @param width
     * @param height
     * @param color
     */
    public UISetup(int width, int height, Paint color) {
        myRoot = new Group();
        myScene = new Scene(myRoot, width, height, color);
        myBorder = new BorderPane();
    }

    /**
     * initializes the UI space, called by Main
     */
    public void initializeUI() {
        this.makeBorderPane();
        this.addButtons();
        this.makeSideMenu();
    }

    /**
     * returns the Scene created in this class, called by Main
     *
     * @return myScene
     */
    public Scene getScene() {
        return myScene;
    }

    /**
     * returns the Simulation selected by the user
     *
     * @return
     */
    public String getSimSelection() {
        return (String) simSelect.getValue();
    }

    /**
     * creates BorderPane
     */
    private void makeBorderPane() {
        myBorder.prefHeightProperty().bind(myScene.heightProperty());
        myBorder.prefWidthProperty().bind(myScene.widthProperty());
        myBorder.setPadding(new Insets(borderSpace));
        myRoot.getChildren().add(myBorder);
    }

    private void makeSideMenu() {
        VBox right = new VBox(borderSpace);
        right.setPrefWidth(sideMenuSpace);
        right.setPadding(new Insets(borderSpace));
        right.setAlignment(Pos.TOP_CENTER);

        this.addChoiceBox(right);
        this.addSliders(right);

        myBorder.setRight(right);
    }

    /**
     * creates a HBox with start, stop, step, and reset buttons
     */
    private void addButtons() {
        HBox bottomRow = new HBox(buttonSpace);
        bottomRow.setPadding(new Insets(buttonSpace));
        bottomRow.setAlignment(Pos.CENTER_LEFT);

        startBtn = new Button("Start"); // myResources.get (title);
        stopBtn = new Button("Stop");
        stepBtn = new Button("Step");
        resetBtn = new Button("Reset");
        setButtonFunctionality();

        bottomRow.getChildren().addAll(startBtn, stopBtn, stepBtn, resetBtn);
        myBorder.setBottom(bottomRow);
    }

    /**
     * creates a VBox with sliders
     */
    private void addSliders(VBox vBox) {
        simSpeed = new Slider(0, 100, 0);
        //simSpeed.valueProperty().addListener();
        gridSize = new Slider(1, 100, 1);
        //gridSize.valueProperty().addListener();

        vBox.getChildren().addAll(simSpeed, gridSize);
    }

    /**
     * creates a ChoiceBox to select simulation type
     */
    private void addChoiceBox(VBox vBox) {
        simSelect = new ChoiceBox();
        // create choices
        simSelect.getItems().add("Segregation");
        simSelect.getItems().add("Wa-Tor World");
        simSelect.getItems().add("Fire Spreading");

        vBox.getChildren().add(simSelect);
    }

    /**
     * adds functionality to each button for when user clicks
     */
    private void setButtonFunctionality() {
        startBtn.setOnAction(value ->  {
            System.out.println("Clicked Start!");
        });
        stopBtn.setOnAction(value ->  {
            System.out.println("Clicked Stop!");
        });
        stepBtn.setOnAction(value ->  {
            System.out.println("Clicked Step!");
        });
        resetBtn.setOnAction(value ->  {
            System.out.println("Clicked Reset!");
        });

    }
}
