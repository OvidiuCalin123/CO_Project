package Screens.Loading;

import Screens.SelectedFunction.RandomRead.RandomReadLogic;
import Screens.SelectedFunction.SelectedFunctionLogicHandle;
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
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static Screens.Result.ResultMain.showResult;

public class LoadingMain {

    public void start(StackPane root, StackPane randomReadMainScreen, String screenName, BorderPane pane, SelectedFunctionLogicHandle functionLogic, String historyBackgroundScreen) {

       // functionLogic.setIsCompleted(false);

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Stream<Path> paths = Files.walk(Paths.get("DesignFiles/LoadingBar"));
                paths
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            try {
                                Image img = new Image("file:" + path);
                                ImageView imageView = new ImageView(img);

                                imageView.setPreserveRatio(true);
                                imageView.setFitWidth(650);
                                imageView.setFitHeight(400);

                                imageView.setTranslateX(0 * randomReadMainScreen.getWidth() / 650);
                                imageView.setTranslateY(130 * randomReadMainScreen.getHeight() / 350);

                                // Reposition the button when the root pane dimensions change
                                randomReadMainScreen.widthProperty().addListener((obs, oldVal, newVal) -> {
                                    imageView.setTranslateX(0 * newVal.doubleValue() / 650);
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

                                switch (path.getFileName().toString()) {
                                    case "loadingCar_007.png", "loadingCar_025.png", "loadingCar_029.png", "loadingCar_016.png" -> Thread.sleep(1000);
                                }

                                if (!functionLogic.getIsCompleted()) {
                                    Thread.sleep(50);
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        });

            } catch (Exception e) {
                // Handle the exception here
            }
        });

        future.thenRunAsync(() -> {
            // Wait for the isCompleted variable to be set to true
            while (!functionLogic.getIsCompleted()) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // Handle the exception here
                }
            }

            // Show the result
            Platform.runLater(() -> {
                showResult(root, screenName, pane, historyBackgroundScreen);
            });
        });

    }

    public LoadingMain(StackPane root, StackPane previousScreen, String screenName, BorderPane pane, SelectedFunctionLogicHandle functionLogic, String historyBackgroundScreen){

        StackPane loadingMainScreen = new StackPane();

        previousScreen.getChildren().add(loadingMainScreen);

        loadingMainScreen.toFront();

        new Background().setBackgroundImage(previousScreen,loadingMainScreen,"finalCar.png");

        start(root, loadingMainScreen, screenName, pane, functionLogic, historyBackgroundScreen);
     }

}
