package GUI;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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

    private HBox myGridBtns;

    private ChoiceBox shapeSelect;
    private RadioButton finiteBtn;
    private RadioButton toroidalBtn;
    private RadioButton infiniteBtn;

    /**
     * Constructor
     *
     * @param resources ResourceBundle, contains keys for button text
     */
    public gridControls(ResourceBundle resources) {
        myResources = resources;
        myGridBtns = new HBox();
    }

    /**
     * returns a ChoiceBox that indicates the shape of the grid
     *
     * @return ChoiceBox
     */
    public ChoiceBox getShapeBox() {
        return shapeSelect;
    }

    /**
     * returns a RadioButton that indicates if grid should be finite
     *
     * @return RadioButton
     */
    public RadioButton getFinBtn() {
        return finiteBtn;
    }

    /**
     * returns a RadioButton that indicates if grid should be toroidal
     *
     * @return RadioButton
     */
    public RadioButton getTorBtn() {
        return toroidalBtn;
    }

    /**
     * returns a RadioButton that indicates if grid should be infinite
     *
     * @return RadioButon
     */
    public RadioButton getInfinBtn() {
        return infiniteBtn;
    }

    /**
     * calls methods that create UI elements of grid controls
     *
     * @return
     */
    public HBox makeGridControls() {
        this.addShapeChoice();
        this.addTypeBtns();
        return myGridBtns;
    }

    /**
     * creates a ChoiceBox to select simulation grid type (square, triangle, hexagon)
     */
    private void addShapeChoice() {
        shapeSelect = new ChoiceBox();

        shapeSelect.getItems().add(myResources.getString("Square"));
        shapeSelect.getItems().add(myResources.getString("Triangle"));
        shapeSelect.getItems().add(myResources.getString("Hexagon"));

        myGridBtns.getChildren().add(shapeSelect);
    }

    /**
     * creates Radio Buttons to handle grid edge types (finite, toroidal, infinite)
     */
    private void addTypeBtns() {
        ToggleGroup gridTypes = new ToggleGroup();

        finiteBtn = new RadioButton(myResources.getString("Finite"));
        finiteBtn.setToggleGroup(gridTypes);
        finiteBtn.setSelected(true);

        toroidalBtn = new RadioButton(myResources.getString("Toroidal"));
        toroidalBtn.setToggleGroup(gridTypes);

        infiniteBtn = new RadioButton(myResources.getString("Infinite"));
        infiniteBtn.setToggleGroup(gridTypes);

        myGridBtns.getChildren().addAll(finiteBtn, toroidalBtn, infiniteBtn);
    }
}
