package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.util.ResourceBundle;

/**
 * simControls
 *
 * creates an Hbox with various buttons,
 * Start, Stop, Step, and Reset, and
 * adds the Hbox to an existing BorderPane
 *
 * @author Brooke Keene
 */
public class simControls {
    private ResourceBundle myResources;
    private BorderPane myBorder;
    private Button startBtn;
    private Button stopBtn;
    private Button stepBtn;
    private Button resetBtn;
    private int buttonPadding = 20;

    /**
     * Constructor
     *
     * @param border BorderPane, where buttons should be added
     * @param resources ResourceBundle, contains keys for button text
     */
    public simControls(BorderPane border, ResourceBundle resources) {
        myResources = resources;
        myBorder = border;
    }

    /**
     * creates a HBox with start, stop, step, and reset buttons
     * and adds the HBox to myBorder
     */
    public void addButtons() {
        HBox bottomRow = new HBox(buttonPadding);
        bottomRow.setPadding(new Insets(buttonPadding));
        bottomRow.setAlignment(Pos.CENTER_LEFT);

        startBtn = new Button(myResources.getString("Start"));
        stopBtn = new Button(myResources.getString("Stop"));
        stepBtn = new Button(myResources.getString("Step"));
        resetBtn = new Button(myResources.getString("Reset"));
        setButtonFunctionality();

        bottomRow.getChildren().addAll(startBtn, stopBtn, stepBtn, resetBtn);
        myBorder.setBottom(bottomRow);
    }

    /**
     * sets functionality of each button for when user clicks
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