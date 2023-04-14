package Screens.Loading;

import Shared.Background;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static Screens.Result.ResultMain.showResult;

public class LoadingMain {

    public void start(StackPane root, StackPane randomReadMainScreen, String screenName, BorderPane pane) {

        Thread t = new Thread(() -> {
            try {
                Stream<Path> paths = Files.walk(Paths.get("DesignFiles/LoadingBar"));
                paths
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            try {
                                Image img = new Image("file:" + path);
                                ImageView imageView = new ImageView(img);

                                imageView.setPreserveRatio(true);
                                imageView.setFitWidth(600);
                                imageView.setFitHeight(400);

                                imageView.setTranslateX(0 * randomReadMainScreen.getWidth() / 600);
                                imageView.setTranslateY(130 * randomReadMainScreen.getHeight() / 350);

                                // Reposition the button when the root pane dimensions change
                                randomReadMainScreen.widthProperty().addListener((obs, oldVal, newVal) -> {
                                    imageView.setTranslateX(0 * newVal.doubleValue() / 600);
                                });
                                randomReadMainScreen.heightProperty().addListener((obs, oldVal, newVal) -> {
                                    imageView.setTranslateY(130 * newVal.doubleValue() / 350);
                                });

                                Platform.runLater(() -> {
                                    // Get the first child of the StackPane
                                    Node firstChild = randomReadMainScreen.getChildren().get(0);

                                    // Clear all children of the StackPane except for the first child
                                    randomReadMainScreen.getChildren().removeIf(child -> child != firstChild);

                                    // Add the new ImageView as the second child of the StackPane
                                    randomReadMainScreen.getChildren().add(1, imageView);
                                });

                                Thread.sleep(50);
                            } catch (Exception e) {
                                // Handle the exception here
                            }
                        });

                Platform.runLater(() -> {
                    showResult(root, screenName, pane);
                });


            } catch (Exception e) {
                // Handle the exception here
            }
        });

        t.start();
    }

    public LoadingMain(StackPane root, StackPane previousScreen, String screenName, BorderPane pane){

        StackPane loadingMainScreen = new StackPane();

        previousScreen.getChildren().add(loadingMainScreen);

        loadingMainScreen.toFront();

        new Background().setBackgroundImage(previousScreen,loadingMainScreen,"finalCar.png");

        start(root, loadingMainScreen, screenName, pane);
    }

}
