package GUI;

import java.io.File;
import javafx.scene.Group;
import javafx.scene.Scene;
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

    private Stage myStage;
    private Scene myScene;
    private Group myRoot;
    private BorderPane myBorder;

    private simControls myControls;
    private simSelectMenu myMenu;

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

        myControls = new simControls(myBorder, myResources);
        myControls.addButtons();

        myMenu = new simSelectMenu(myStage, myBorder, myResources);
        myMenu.makeSideMenu();
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