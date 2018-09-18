package GUI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;


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
    }

    /**
     *
     * @return myScene
     */
    public Scene getScene() {
        return myScene;
    }
}
