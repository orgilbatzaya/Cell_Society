package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ResourceBundle;


/**
 * Main
 *
 * main class to run program
 *
 * @author Brooke Keene
 */
public class Main extends Application {
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    public static final Paint BACKGROUND = Color.WHITE;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final String RESOURCE_PACKAGE = "resources/";
    public static final String UI_TEXT = "English";

    private UISetup myDisplay;

    @Override
    /**
     * sets Stage and initializes GUI
     */
    public void start(Stage stage) {
        ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_PACKAGE + UI_TEXT);

        myDisplay = new UISetup(WIDTH, HEIGHT, BACKGROUND, stage);
        myDisplay.initializeUI();
        stage.setScene(myDisplay.getScene());
        stage.setTitle(myResources.getString("Title"));
        stage.show();

        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();

    }

    /**
     * Animation Loop
     *
     * @param elapsedTime
     */
    private void step(double elapsedTime) {

    }

    public static void main(String[] args) {
        launch(args);
    }

}
