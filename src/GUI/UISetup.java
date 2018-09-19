package GUI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;
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

    private int buttonSpace = 20;
    private int borderSpace = 10;

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

        myBorder.prefHeightProperty().bind(myScene.heightProperty());
        myBorder.prefWidthProperty().bind(myScene.widthProperty());
        myBorder.setPadding(new Insets(borderSpace));
    }

    /**
     * adds start, stop, step, and reset to GUI
     */
    public void addButtons() {
        HBox bottomRow = new HBox(buttonSpace);
        bottomRow.setPadding(new Insets(buttonSpace));
        bottomRow.setAlignment(Pos.CENTER_LEFT);

        startBtn = new Button("Start");
        stopBtn = new Button("Stop");
        stepBtn = new Button("Step");
        resetBtn = new Button("Reset");
        setButtonFunctionality();

        bottomRow.getChildren().addAll(startBtn, stopBtn, stepBtn, resetBtn);
        myBorder.setBottom(bottomRow);
        myRoot.getChildren().add(myBorder);
    }

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

    /**
     *
     * @return myScene
     */
    public Scene getScene() {
        return myScene;
    }
}
