package Shared;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;

public class Dropbox {

    private static Object selectedOption=null;

    public static void resetSelectedOption(){
        selectedOption = null;
    }

    private static void updateButtonImage(Button b) {

        if (selectedOption == null) {

            Image image = new Image("file:DesignFiles/Buttons/shadowRun.png");
            ImageView imageView = new ImageView(image);
            b.setGraphic(imageView);

        } else {

            Image image = new Image("file:DesignFiles/Buttons/run.png");
            ImageView imageView = new ImageView(image);

            b.setGraphic(imageView);

            b.setOnMouseEntered(e -> {
                if(getSelectedOption()!=null) {
                    System.out.println("HM");

                    b.setStyle("-fx-cursor: hand; -fx-background-color: transparent;");
                    DropShadow dropShadow = new DropShadow();
                    dropShadow.setRadius(20);
                    dropShadow.setOffsetX(20);
                    dropShadow.setOffsetY(20);
                    dropShadow.setColor(Color.ORANGE);
                    imageView.setEffect(dropShadow);

                    File file = new File("SoundFiles/OnHoverSound.wav");

                    AudioClip audioClip = new AudioClip(file.toURI().toString());
                    audioClip.setVolume(3);
                    audioClip.play();
                }

            });

            b.setOnMouseExited(e -> {
                b.setStyle("-fx-background-color: transparent;");
                imageView.setEffect(null);
            });
        }
    }

    public static void dropbox(StackPane root, StackPane sequentialWriteMainScreen, String[] options, double xCoords, double yCoords, Button b){

        ComboBox<String> dropdown = new ComboBox<>();

        dropdown.setStyle("-fx-background-image: url('file:DesignFiles/Buttons/templateButton2.png');" +
                "-fx-background-position: top;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: 100% 100%;" +
                "-fx-background-color: transparent;");


        dropdown.getItems().addAll(options);

        dropdown.setPrefSize(100, 20);

        dropdown.prefWidthProperty().bind(root.widthProperty().multiply(0.225));
        dropdown.prefHeightProperty().bind(root.heightProperty().multiply(0.0915));


        dropdown.setTranslateX(xCoords * root.getWidth() / 1280);
        dropdown.setTranslateY(yCoords * root.getHeight() / 720);

        root.widthProperty().addListener((obs, oldVal, newVal) -> dropdown.setTranslateX(xCoords * newVal.doubleValue() / 1280));
        root.heightProperty().addListener((obs, oldVal, newVal) -> dropdown.setTranslateY(yCoords * newVal.doubleValue() / 720));


        dropdown.setCellFactory(param -> new ListCell<>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {

                    Text text = new Text(item);
                    text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 25));
                    text.setStyle("-fx-fill: orange; -fx-stroke: black; -fx-stroke-width: 1.75px;");

                    text.fontProperty().bind(Bindings.createObjectBinding(() -> {
                        double fontSize = 0.03 * Math.min(root.getWidth()*0.65, root.getHeight()*1.25);
                        return Font.font("Snap ITC",fontSize);
                    }, root.widthProperty(), root.heightProperty()));

                    setGraphic(text);
                }
            }
        });

        dropdown.setButtonCell(new ListCell<>() {
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("SELECT");
                } else {
                    Text text = new Text(item);
                    text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 30));
                    setFont(text.getFont());

                    text.setStyle("-fx-fill: orange; -fx-stroke: black; -fx-stroke-width: 2px;");

                    text.fontProperty().bind(Bindings.createObjectBinding(() -> {
                        double fontSize = 0.04 * Math.min(root.getWidth()*0.7, root.getHeight()*1.25);
                        return Font.font("Snap ITC",fontSize);
                    }, root.widthProperty(), root.heightProperty()));


                    if(text.getText().equals("1 GB")){

                        selectedOption=(long)1024*1024*1024;

                    }else if(text.getText().equals("500 MB")){

                        selectedOption=Math.round(1024*1024*1024*0.5);


                    }else if(text.getText().equals("100 MB")){

                        selectedOption=Math.round(1024*1024*1024*0.1);

                    }
                    else{
                        selectedOption=text.getText();

                    }

                    setGraphic(text);
                }
            }
        });

        dropdown.setOnAction(event -> {
            String selectedItem = dropdown.getSelectionModel().getSelectedItem();
                        if (selectedItem != null) {

                Text text = new Text(selectedItem);
                text.setFont(Font.font("Snap ITC", FontWeight.BOLD, 12));
                text.setFill(Color.ORANGE);
                updateButtonImage(b);
            }
        });

        dropdown.setMinWidth(Region.USE_COMPUTED_SIZE * 0.5);
        dropdown.setMinHeight(Region.USE_COMPUTED_SIZE * 0.5);
        dropdown.setMaxWidth(Region.USE_COMPUTED_SIZE);
        dropdown.setMaxHeight(Region.USE_COMPUTED_SIZE);

        sequentialWriteMainScreen.getChildren().add(dropdown);
    }

   public static Object getSelectedOption(){

        return selectedOption;
   }
}
