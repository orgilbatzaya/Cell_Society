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
     *
     * @return
     */
    public RadioButton getFinBtn() {
        return finiteBtn;
    }

    /**
     *
     * @return
     */
    public RadioButton getTorBtn() {
        return toroidalBtn;
    }

    /**
     *
     * @return
     */
    public RadioButton getInfinBtn() {
        return infiniteBtn;
    }

    public HBox makeGridControls() {
        this.addShapeChoice();
        this.addTypeBtns();
        return myGridBtns;
    }

    /**
     * creates a ChoiceBox to select simulation type
     */
    private void addShapeChoice() {
        ChoiceBox shapeSelect = new ChoiceBox();

        shapeSelect.getItems().add(myResources.getString("Square"));
        shapeSelect.getItems().add(myResources.getString("Triangle"));
        shapeSelect.getItems().add(myResources.getString("Hexagon"));

        myGridBtns.getChildren().add(shapeSelect);
    }

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

    /**
     * sets functionality of each button for when user clicks
     */
    private void setButtonFunctionality() {
        finiteBtn.setOnAction(value ->  {
            System.out.println("finite");
        });
        toroidalBtn.setOnAction(value ->  {
            System.out.println("toroidal");
        });
        infiniteBtn.setOnAction(value -> {
            System.out.println("infinite");
        });
    }
}
