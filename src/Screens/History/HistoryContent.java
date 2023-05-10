package Screens.History;

import Screens.SelectedFunction.CheckSize.CheckSizeLogic;
import Screens.SelectedFunction.RandomRead.RandomReadLogic;
import Screens.SelectedFunction.RandomWrite.RandomWriteLogic;
import Screens.SelectedFunction.SelectedFunctionLogicHandle;
import Screens.SelectedFunction.SequentialRead.SequentialReadLogic;
import Screens.SelectedFunction.SequentialWrite.SequentialWriteLogic;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Locale;

import static Screens.History.FileOperations.*;
import static Screens.History.HistoryDataLogic.getLocalTime;
import static Screens.History.HistoryDataLogic.getStorage;
import static Shared.ButtonsHelper.buttonBuilder;
import static Shared.ButtonsHelper.scaleButton;
import static Shared.Dropbox.getSelectedOption;

public class HistoryContent extends BorderPane {

    private final TableView<HistoryModel> tableView;

    public HistoryContent(StackPane realRoot, StackPane root, BorderPane pane, String historyBackgroundScreen) {

        setPadding(new Insets(20));
        setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        TableColumn<HistoryModel, Double> rowNrCol = new TableColumn<>("Nr.");
        rowNrCol.setCellValueFactory(new PropertyValueFactory<>("rowNumber"));
        rowNrCol.setResizable(false);
        rowNrCol.setStyle("-fx-font-size: 20pt;");
        rowNrCol.setPrefWidth(75);

        TableColumn<HistoryModel, Double> optionCol = new TableColumn<>("Option");

        if(getSelectedOption() != null){

            optionCol.setCellValueFactory(new PropertyValueFactory<>("selectedOption"));
            optionCol.setResizable(false);
            optionCol.setStyle("-fx-font-size: 20pt;");
            optionCol.setPrefWidth(200);

        }

        TableColumn<HistoryModel, Double> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreCol.setResizable(false);
        scoreCol.setStyle("-fx-font-size: 20pt;");
        scoreCol.setPrefWidth(200);

        TableColumn<HistoryModel, Double> runTimeCol = new TableColumn<>("Run Time");
        runTimeCol.setCellValueFactory(new PropertyValueFactory<>("run_time"));
        runTimeCol.setResizable(false);
        runTimeCol.setStyle("-fx-font-size: 20pt;");
        runTimeCol.setPrefWidth(200);

        TableColumn<HistoryModel, String> hdd_ssdCol = new TableColumn<>("HDD/SSD");
        hdd_ssdCol.setCellValueFactory(new PropertyValueFactory<>("hdd_ssd"));
        hdd_ssdCol.setResizable(false);
        hdd_ssdCol.setStyle("-fx-font-size: 20pt;");
        hdd_ssdCol.setPrefWidth(300);

        TableColumn<HistoryModel, String> testTimeCol = new TableColumn<>("Test Time");
        testTimeCol.setCellValueFactory(new PropertyValueFactory<>("test_time"));
        testTimeCol.setResizable(false);
        testTimeCol.setStyle("-fx-font-size: 20pt;");

        testTimeCol.setPrefWidth(400);

        tableView = new TableView<>();

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        if(getSelectedOption() != null){
            tableView.getColumns().addAll(rowNrCol, optionCol,scoreCol, runTimeCol, hdd_ssdCol, testTimeCol);
        }else{
            tableView.getColumns().addAll(rowNrCol,scoreCol, runTimeCol, hdd_ssdCol, testTimeCol);
        }

        tableView.setPrefHeight(400);
        tableView.setOpacity(0.75);

        tableView.setScaleX(0.7);
        tableView.setScaleY(0.7);

        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        Label titleLabel = new Label("History Table");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web("#0076a3"));

        setTop(titleLabel);
        setAlignment(titleLabel, Pos.CENTER);
        setCenter(scrollPane);
        root.getChildren().add(tableView);

        back(realRoot, root, pane);
        quit(realRoot, root, pane);
        clearHistory(realRoot, root, pane, historyBackgroundScreen);
        titleBuilder(realRoot, root, pane, root, historyBackgroundScreen);

        createTableRow(historyBackgroundScreen);
        populateHistoryTable(historyBackgroundScreen);
        scaleTable(tableView, realRoot);

    }

    public void createTableRow(String historyBackgroundScreen){

        String score="";
        double run_time = 0.0;
        String storage = getStorage();
        String localTime = getLocalTime();

        if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster1")){

            SelectedFunctionLogicHandle checkSize = new CheckSizeLogic();
            run_time = checkSize.getTime();
            score = String.format(Locale.US,"%.2f", checkSize.getScore());

            writeToFile("src\\Screens\\History\\historyStorage\\historyCheckSize.txt", getSelectedOption() + "", score, run_time, storage, localTime);


        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster2")){

            SelectedFunctionLogicHandle randomRead = new RandomReadLogic();
            run_time = randomRead.getTime();
            score = String.format(Locale.US,"%.2f",randomRead.getScore());

            writeToFile("src\\Screens\\History\\historyStorage\\historyRandomRead.txt", score, run_time, storage, localTime);

        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster3")){

            SelectedFunctionLogicHandle randomWrite = new RandomWriteLogic();
            run_time = randomWrite.getTime();
            score = String.format(Locale.US,"%.2f",randomWrite.getScore());

            writeToFile("src\\Screens\\History\\historyStorage\\historyRandomWrite.txt", score, run_time, storage, localTime);

        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster4")){

            SelectedFunctionLogicHandle sequentialRead = new SequentialReadLogic();
            run_time = sequentialRead.getTime();
            score = String.format(Locale.US,"%.2f",sequentialRead.getScore());

            writeToFile("src\\Screens\\History\\historyStorage\\historySequentialRead.txt", score, run_time, storage, localTime);

        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster5")){


                SelectedFunctionLogicHandle sequentialWrite = new SequentialWriteLogic();
                run_time = sequentialWrite.getTime();
                score = String.format(Locale.US,"%.2f",sequentialWrite.getScore());

                writeToFile("src\\Screens\\History\\historyStorage\\historySequentialWrite.txt",getSelectedOption() + "", score, run_time, storage, localTime);


        }
    }

    public void scaleTable(TableView<HistoryModel> tableView, StackPane root) {

        double widthPercentage = 0.3;
        double heightPercentage = 0.25;

        tableView.prefWidthProperty().bind(root.widthProperty().multiply(widthPercentage));
        tableView.prefHeightProperty().bind(root.heightProperty().multiply(heightPercentage));


        tableView.setTranslateX(0 * root.getWidth() / 600);
        tableView.setTranslateY(0 * root.getHeight() / 350);

        root.widthProperty().addListener((obs, oldVal, newVal) -> tableView.setTranslateX(0 * newVal.doubleValue() / 600));
        root.heightProperty().addListener((obs, oldVal, newVal) -> tableView.setTranslateY(0 * newVal.doubleValue() / 350));
    }

    public void setTitle(StackPane realRoot, StackPane root, BorderPane pane, StackPane checkSizeMainScreen, String titleName){
        Image img = new Image("file:DesignFiles/Background/" + titleName + ".png");
        ImageView imageView = new ImageView(img);

        imageView.fitWidthProperty().bind(realRoot.widthProperty().multiply(0.40));
        imageView.fitHeightProperty().bind(realRoot.heightProperty().multiply(0.10));

        imageView.setTranslateX(0 * realRoot.getWidth() / 600);
        imageView.setTranslateY(-145 * realRoot.getHeight() / 350);

        realRoot.widthProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateX(0 * newVal.doubleValue() / 600));
        realRoot.heightProperty().addListener((obs, oldVal, newVal) -> imageView.setTranslateY(-145 * newVal.doubleValue() / 350));

        pane.setCenter(imageView);

        checkSizeMainScreen.getChildren().add(imageView);
    }

    public void titleBuilder(StackPane realRoot, StackPane root, BorderPane pane, StackPane checkSizeMainScreen, String historyBackgroundScreen){
       if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster1")){

           setTitle(realRoot, root, pane, checkSizeMainScreen, "checkSizeTitle2");
       }
       else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster2")){

           setTitle(realRoot, root, pane, checkSizeMainScreen, "randomReadTitle2");

       }
       else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster3")){

           setTitle(realRoot, root, pane, checkSizeMainScreen, "randomWriteTitle2");

       }
       else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster4")){

           setTitle(realRoot, root, pane, checkSizeMainScreen, "sequentialReadTitle2");

       }
       else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster5")){

           setTitle(realRoot, root, pane, checkSizeMainScreen, "sequentialWriteTitle2");

       }

   }

    public void populateHistoryTable(String historyBackgroundScreen){

        if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster1")){

            readFromFile("src\\Screens\\History\\historyStorage\\historyCheckSize.txt", tableView);

        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster2")){

            readFromFile("src\\Screens\\History\\historyStorage\\historyRandomRead.txt", tableView);

        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster3")){

            readFromFile("src\\Screens\\History\\historyStorage\\historyRandomWrite.txt", tableView);

        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster4")){

            readFromFile("src\\Screens\\History\\historyStorage\\historySequentialRead.txt", tableView);

        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster5")){

            readFromFile("src\\Screens\\History\\historyStorage\\historySequentialWrite.txt", tableView);

        }

    }

    public void clearHistory(StackPane realRoot, StackPane checkSizeMainScreen, BorderPane pane, String historyBackgroundScreen){
        EventHandler<ActionEvent> event = e -> {
            if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster1")){

                clearFile("src\\Screens\\History\\historyStorage\\historyCheckSize.txt");
                Label noContent = new Label("No history available for Check Size.");
                noContent.setStyle("-fx-font-size: 20pt;");
                tableView.setPlaceholder(noContent);

            }
            else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster2")){

                clearFile("src\\Screens\\History\\historyStorage\\historyRandomRead.txt");
                Label noContent = new Label("No history available for Random Read.");
                noContent.setStyle("-fx-font-size: 20pt;");
                tableView.setPlaceholder(noContent);

            }
            else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster3")){

                clearFile("src\\Screens\\History\\historyStorage\\historyRandomWrite.txt");
                Label noContent = new Label("No history available for Random Write.");
                noContent.setStyle("-fx-font-size: 20pt;");
                tableView.setPlaceholder(noContent);

            }
            else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster4")){

                clearFile("src\\Screens\\History\\historyStorage\\historySequentialRead.txt");
                Label noContent = new Label("No history available for Sequential Read.");
                noContent.setStyle("-fx-font-size: 20pt;");
                tableView.setPlaceholder(noContent);

            }
            else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster5")){

                clearFile("src\\Screens\\History\\historyStorage\\historySequentialWrite.txt");
                Label noContent = new Label("No history available for Sequential Write.");
                noContent.setStyle("-fx-font-size: 20pt;");
                tableView.setPlaceholder(noContent);

            }
            tableView.getItems().clear();
        };

        double xCoords = 0;
        double yCoords = 775;

        double xScale = 2500;
        double yScale = 1800;

        Button b = buttonBuilder("clearHistory2", checkSizeMainScreen, event, pane);
        scaleButton(b,realRoot,xScale,yScale, xCoords, yCoords);
    }

    public void back(StackPane realRoot, StackPane sequentialReadMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            sequentialReadMainScreen.toBack();
            sequentialReadMainScreen.toBack();
            ObservableList<Node> children = sequentialReadMainScreen.getChildren();
            int index = children.size() - 4;
            sequentialReadMainScreen.getChildren().get(index).toBack();
            sequentialReadMainScreen.getChildren().get(index + 1).toBack();
            sequentialReadMainScreen.getChildren().get(index + 2).toBack();
            sequentialReadMainScreen.getChildren().get(index + 2).toBack();
            sequentialReadMainScreen.getChildren().get(index + 3).toBack();

        };

        double xCoords = -975;
        double yCoords = 705;

        double xScale = 2400;
        double yScale = 1650;

        Button b = buttonBuilder("back", sequentialReadMainScreen, event, pane);

        scaleButton(b,realRoot,xScale,yScale, xCoords, yCoords);


    }

    public void quit(StackPane realRoot, StackPane checkSizeMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            Platform.exit();
            System.exit(0);
        };

        double xCoords = 850;
        double yCoords = 625;

        double xScale = 2050;
        double yScale = 1450;

        Button b = buttonBuilder("Quit", checkSizeMainScreen, event, pane);
        scaleButton(b,realRoot,xScale,yScale, xCoords, yCoords);
    }
}