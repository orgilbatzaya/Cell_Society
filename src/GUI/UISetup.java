package GUI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
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

    private Scene myScene;
    private Group myRoot;
    private BorderPane myBorder;

    private simControls myControls;

    private ResourceBundle myResources;

    private Button doneBtn;

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
        myResources = ResourceBundle.getBundle(RESOURCE_PACKAGE + UI_TEXT);
        myScene.getStylesheets().add(STYLESHEET);

        this.makeBorderPane();

        myControls = new simControls(myBorder, myResources);
        myControls.addButtons();

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
     * returns the File selected by the user
     *
     * @return
     */
    public String getUserFile() {
        //TODO: return File or String
        return "";
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

    /**
     * creates VBox to contain side menu options
     */
    private void makeSideMenu() {
        VBox right = new VBox(borderSpace);
        right.setPrefWidth(sideMenuSpace);
        right.setPadding(new Insets(borderSpace));
        right.setAlignment(Pos.TOP_CENTER);

        doneBtn = new Button(myResources.getString("Done"));
        doneBtn.setOnAction(value -> {
            //TODO: reset xml file
        });
        right.getChildren().add(doneBtn);

        myBorder.setRight(right);
    }
}
