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


public class SequentialWriteLogic implements SelectedFunctionLogicHandle{

    private static double speed;
    private static double time;
    private static boolean isCompleted=true;

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