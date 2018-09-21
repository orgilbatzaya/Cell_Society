package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ResourceBundle;


/**
 * Main
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
    public static final String RESOURCE_PACKAGE = "resources/English";
    public static final String STYLESHEET = "default.css";
    public static final String BLANK = " ";


    private UISetup myDisplay;
    private String simulation;

    @Override
    public void start(Stage stage) {
        // use resources for labels
        ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_PACKAGE);
        myDisplay = new UISetup(WIDTH, HEIGHT, BACKGROUND, myResources);
        myDisplay.initializeUI();
        simulation = myDisplay.getSimSelection();
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
        String newSim = myDisplay.getSimSelection();
        if(newSim != null && !newSim.equals(simulation)) {
            System.out.println(newSim);
        }
        simulation = newSim;

        int speed = myDisplay.getSimSpeed();
        int grid = myDisplay.getGridSize();

        if(speed == 50) {
            System.out.println(speed);
        }
        if(grid == 2) {
            System.out.println(grid);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
