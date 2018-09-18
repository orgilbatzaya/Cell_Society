package GUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
//        stage.setScene(myScene);
        stage.setTitle("Welcomes To BreakOut!");
        stage.show();

//        var frame = new KeyFrame(Duration.millis(milli_delay), e -> gameMode(second_delay, stage));
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.getKeyFrames().add(frame);
//        animation.play();

    }

    //Start scene
    public static void main(String[] args) {
        launch(args);
    }

}
