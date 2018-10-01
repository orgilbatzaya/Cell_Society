package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.util.ResourceBundle;

/**
 * gridControls
 *
 * creates an Hbox with various buttons,
 * to specify the type of Grid and
 * adds the Hbox to an existing BorderPane
 *
 * @author Brooke Keene
 */
public class gridControls {
    private ResourceBundle myResources;
    private BorderPane myBorder;

    private HBox myControls;

    private int buttonPadding = 20;
    private RadioButton finiteBtn;
    private RadioButton toroidalBtn;
    private RadioButton infiniteBtn;

    private boolean changeFlag;

    /**
     * Constructor
     *
     * @param border BorderPane, where buttons should be added
     * @param resources ResourceBundle, contains keys for button text
     */
    public gridControls(BorderPane border, ResourceBundle resources) {
        myResources = resources;
        myBorder = border;

        myControls = new HBox();
        myControls.setPadding(new Insets(buttonPadding));
        myControls.setAlignment(Pos.TOP_LEFT);

        changeFlag = false;
    }

    public boolean getChangeFlag() {
        return changeFlag;
    }

    /**
     * creates a ChoiceBox to select simulation type
     */
    public void addShapeChoice() {
        ChoiceBox shapeSelect = new ChoiceBox();

        shapeSelect.getItems().add(myResources.getString("Square"));
        shapeSelect.getItems().add(myResources.getString("Triangle"));
        shapeSelect.getItems().add(myResources.getString("Hexagon"));

        myControls.getChildren().add(shapeSelect);
    }

    public void addTypeBtns() {
        ToggleGroup gridTypes = new ToggleGroup();

        finiteBtn = new RadioButton(myResources.getString("Finite"));
        finiteBtn.setToggleGroup(gridTypes);
        finiteBtn.setSelected(true);

        toroidalBtn = new RadioButton(myResources.getString("Toroidal"));
        toroidalBtn.setToggleGroup(gridTypes);

        infiniteBtn = new RadioButton(myResources.getString("Infinite"));
        infiniteBtn.setToggleGroup(gridTypes);

        setButtonFunctionality();

        myControls.getChildren().addAll(finiteBtn, toroidalBtn, infiniteBtn);
        myBorder.setTop(myControls);
    }

    /**
     * sets functionality of each button for when user clicks
     */
    private void setButtonFunctionality() {
        finiteBtn.setOnAction(value ->  {
            System.out.println("finite");
            changeFlag = true;
        });
        toroidalBtn.setOnAction(value ->  {
            System.out.println("toroidal");
            changeFlag = true;
        });
        infiniteBtn.setOnAction(value -> {
            System.out.println("infinite");
            changeFlag = true;
        });
    }
}
