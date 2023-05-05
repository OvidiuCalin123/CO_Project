package Screens.SelectedFunction.SequentialWrite;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URLEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SequentialWriteLogic implements SelectedFunctionLogicHandle{

    private static double speed;
    private static double time;
    private static boolean isCompleted=false;

       public static double measureSequentialWriteSpeed(File file, long fileSize, int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];
        // Measure the sequential write speed
        long startTime = System.currentTimeMillis();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            long bytesToWrite = fileSize;
            while (bytesToWrite > 0) {
                int bytesToWriteNow = (int) Math.min(bytesToWrite, buffer.length);
                fos.write(buffer, 0, bytesToWriteNow);
                bytesToWrite -= bytesToWriteNow;
            }
        }
        // Calculate the time taken and return the results.
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        time=timeTaken;
        double writeSpeed = fileSize / (1024.0 * 1024.0 * timeTaken / 1000.0);
        speed=writeSpeed;
        isCompleted=true;
        return writeSpeed;
    }

    public void run() throws IOException {
        Thread t2 = new Thread(() -> {
            try {
                File file = new File("testfile");
                file.deleteOnExit();
                long fileSize = 1024 * 1024 * 1024; // 1 GB for testing, implement user input or dropdown later.
                int bufferSize = 4096;
                double writeSpeed = SequentialWriteLogic.measureSequentialWriteSpeed(file, fileSize, bufferSize);
                System.out.printf("Sequential write speed: %.2f MB/s\n", writeSpeed);
            } catch (IOException e) {
                // handle the exception
            }

        });
        t2.start();
    }
    public void dropbox(StackPane sequentialWriteMainScreen, BorderPane pane){

        ComboBox<String> dropdown = new ComboBox<>();

        dropdown.setStyle("-fx-background-image: url('file:DesignFiles/Buttons/templateButton.png');" +
                "-fx-background-position: center right;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: 100% 100%;" +
                "-fx-background-color: transparent;");

        Text option1GB = new Text("1 GB");
        Text option500MB = new Text("500 MB");
        Text option100MB = new Text("100 MB");

        setFontText(option1GB);

        dropdown.setScaleX(3);
        dropdown.setScaleY(2.70);

        dropdown.getItems().addAll(option1GB.getText(), option500MB.getText(), option100MB.getText());

        VBox dropboxRoot = new VBox(dropdown);

        dropdown.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {

                    Text text = new Text(item);
                    text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 20));

                    text.setStyle("-fx-fill: orange; -fx-stroke: black; -fx-stroke-width: 1px;");
                    setGraphic(text);
                }
            }
        });

        dropdown.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("SELECT");
                } else {
                    Text text = new Text(item);
                    text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 14));
                    //setText(text.getText());
                    setFont(text.getFont());

                    text.setStyle("-fx-fill: orange; -fx-stroke: black; -fx-stroke-width: 0.65px;");

                    setGraphic(text);
                }
            }
        });

        dropdown.setOnAction(event -> {
            String selectedItem = dropdown.getSelectionModel().getSelectedItem();
            System.out.println(selectedItem);
            // Set font, color, and stroke for selected item
            if (selectedItem != null) {

                Text text = new Text(selectedItem);
                text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 12));
                text.setFill(Color.ORANGE);

            }
        });

        dropboxRoot.setStyle("-fx-background-color: transparent;");
        dropboxRoot.setTranslateX(325 * sequentialWriteMainScreen.getWidth() / 600);
        dropboxRoot.setTranslateY(161.5 * sequentialWriteMainScreen.getHeight() / 350);
        // Reposition the button when the root pane dimensions change
        sequentialWriteMainScreen.widthProperty().addListener((obs, oldVal, newVal) -> {
            dropboxRoot.setTranslateX(325 * newVal.doubleValue() / 600);
        });
        sequentialWriteMainScreen.heightProperty().addListener((obs, oldVal, newVal) -> {
            dropboxRoot.setTranslateY(161.5 * newVal.doubleValue() / 350);
        });
        //pane.setCenter(root);
        sequentialWriteMainScreen.getChildren().add(dropboxRoot);
    }

    public void setFontText(Text text){
        text.setFill(Color.ORANGE);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(2);
        text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 14));
    }

    public boolean getIsCompleted(){
        return isCompleted;
    }

    public void setIsCompleted(boolean value){
        isCompleted = value;
    }

    public double getTime(){ return time/1000; }

    public double getScore(){
        return speed;
    }
}
