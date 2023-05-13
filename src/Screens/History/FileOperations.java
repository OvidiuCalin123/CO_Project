package Screens.History;

import javafx.scene.control.TableView;

import java.io.*;
import java.util.Stack;

import static Shared.Dropbox.getSelectedOption;

public class FileOperations {

    public static void writeToFile(String filePath, Object... content) {
        try {
            File file = new File(filePath);

            FileWriter openFileForWriting = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter writeToFile = new BufferedWriter(openFileForWriting);

            writeToFile.write(content[0] + "," + content[1] + "," + content[2] + "," + content[3] + "," + content[4]);

            writeToFile.newLine();
            writeToFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getHistorySize(String filePath) {

        int colNr = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            while (reader.readLine() != null) {

                colNr += 1;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return colNr;
    }

    public static void readFromFile(String filePath, TableView<HistoryModel> tableView) {

        Stack<HistoryModel> historyStack = new Stack<>();

        int colNr = getHistorySize(filePath);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {

                String[] fields = line.split(",");

                String score = fields[1];
                String run_time = fields[2];
                String storage = fields[3];
                String localTime = fields[4];

                switch ((fields[0] + "")) {
                    case 1073741824 + "" -> {
                        HistoryModel newPerson = new HistoryModel(colNr, "1 GB", score, run_time, storage, localTime);
                        historyStack.push(newPerson);

                    }
                    case 536870912 + "" -> {
                        HistoryModel newPerson = new HistoryModel(colNr, "500 MB", score, run_time, storage, localTime);
                        historyStack.push(newPerson);

                    }
                    case 107374182 + "" -> {
                        HistoryModel newPerson = new HistoryModel(colNr, "100 MB", score, run_time, storage, localTime);
                        historyStack.push(newPerson);

                    }
                    default -> {

                        HistoryModel newPerson = new HistoryModel(colNr, fields[0] + "", score, run_time, storage, localTime);
                        historyStack.push(newPerson);
                    }
                }

                colNr--;
            }

            while (!historyStack.empty()) {
                tableView.getItems().add(historyStack.pop());
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearFile(String filePath) {
        try {
            FileWriter openFileForWriting = new FileWriter(filePath, false);
            openFileForWriting.write("");
            openFileForWriting.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
