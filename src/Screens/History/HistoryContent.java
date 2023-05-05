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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.*;

import static Screens.History.FileOperations.readFromFile;
import static Screens.History.HistoryDataLogic.getLocalTime;
import static Screens.History.HistoryDataLogic.getStorage;
import static Screens.History.FileOperations.writeToFile;
import static Shared.ButtonsHelper.buttonBuilder;
import static Shared.ButtonsHelper.scaleButton;

public class HistoryContent extends BorderPane {

    private final TableView<HistoryModel> tableView;

    public HistoryContent(StackPane root, BorderPane pane, String historyBackgroundScreen) {

        // Set padding and background color
        setPadding(new Insets(20));
        setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        TableColumn<HistoryModel, Double> rowNrCol = new TableColumn<>("Nr.");
        rowNrCol.setCellValueFactory(new PropertyValueFactory<>("rowNumber"));
        rowNrCol.setResizable(false);
        rowNrCol.setStyle("-fx-font-size: 20pt;");
        rowNrCol.setPrefWidth(75);

        // Create the table columns
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
        hdd_ssdCol.setPrefWidth(400);

        TableColumn<HistoryModel, String> testTimeCol = new TableColumn<>("Test Time");
        testTimeCol.setCellValueFactory(new PropertyValueFactory<>("test_time"));
        testTimeCol.setResizable(false);
        testTimeCol.setStyle("-fx-font-size: 20pt;");

        testTimeCol.setPrefWidth(400);

        // Create the table view and add the columns
        tableView = new TableView<>();

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(rowNrCol,scoreCol, runTimeCol, hdd_ssdCol, testTimeCol);
        tableView.setPrefHeight(400);
        tableView.setOpacity(0.6);

        tableView.setScaleX(0.7);
        tableView.setScaleY(0.7);

        // Create the scroll pane and add the table view
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // Create the title label
        Label titleLabel = new Label("History Table");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleLabel.setTextFill(Color.web("#0076a3"));

        // Add the title label and scroll pane to this border pane
        setTop(titleLabel);
        setAlignment(titleLabel, Pos.CENTER);
        setCenter(scrollPane);
        root.getChildren().add(tableView);

        back(root, pane);
        quit(root, pane);

        createTableRow(historyBackgroundScreen);
        populateHistoryTable(historyBackgroundScreen);

    }

    public void populateHistoryTable(String historyBackgroundScreen){

        // we need to check the historyBackgroundScreen
        readFromFile("src\\Screens\\History\\historyStorage\\historyCheckSize.txt", tableView);

    }

    public void createTableRow(String historyBackgroundScreen){

        double score = 0.0;
        double run_time = 0.0;
        int rowNr = 0;
        String storage = getStorage();
        String localTime = getLocalTime();

        if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster1")){

            SelectedFunctionLogicHandle checkSize = new CheckSizeLogic();
            run_time = checkSize.getTime();
            score = checkSize.getScore();

            writeToFile("src\\Screens\\History\\historyStorage\\historyCheckSize.txt", score, run_time, storage, localTime);

        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster2")){

            SelectedFunctionLogicHandle randomRead = new RandomReadLogic();
            run_time = randomRead.getTime();
            score = randomRead.getScore();

        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster3")){

            SelectedFunctionLogicHandle randomWrite = new RandomWriteLogic();
            run_time = randomWrite.getTime();
            score = randomWrite.getScore();

        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster4")){

            SelectedFunctionLogicHandle sequentialRead = new SequentialReadLogic();
            run_time = sequentialRead.getTime();
            score = sequentialRead.getScore();

        }
        else if(historyBackgroundScreen.substring(0, historyBackgroundScreen.lastIndexOf('.')).equals("monster5")){

            SelectedFunctionLogicHandle sequentialWrite = new SequentialWriteLogic();
            run_time = sequentialWrite.getTime();
            score = sequentialWrite.getScore();

        }

    }

    public void back(StackPane sequentialReadMainScreen, BorderPane pane){
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

        scaleButton(b,sequentialReadMainScreen,xScale,yScale, xCoords, yCoords);


    }
    public void quit(StackPane checkSizeMainScreen, BorderPane pane){
        EventHandler<ActionEvent> event = e -> {
            Platform.exit();
            System.exit(0);
        };

        double xCoords = 850;
        double yCoords = 625;

        double xScale = 2050;
        double yScale = 1450;

        Button b = buttonBuilder("Quit", checkSizeMainScreen, event, pane);
        scaleButton(b,checkSizeMainScreen,xScale,yScale, xCoords, yCoords);
    }
    public void setData(ObservableList<HistoryModel> data) {
        // Set the data for the table view
        tableView.setItems(data);
    }
}



