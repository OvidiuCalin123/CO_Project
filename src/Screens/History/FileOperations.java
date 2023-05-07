package Screens.History;

import javafx.scene.control.TableView;
import java.io.*;
import java.util.Stack;

public class FileOperations {

    public static void writeToFile(String filePath, Object ...content){
        try {
            File file = new File(filePath);

            FileWriter openFileForWriting = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter writeToFile = new BufferedWriter(openFileForWriting);

            writeToFile.write( content[0] + "," + content[1] + "," + content[2] + "," + content[3]);
            writeToFile.newLine();
            writeToFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getHistorySize(String filePath){

        int colNr = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            while (reader.readLine() != null) {

                colNr+=1;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return colNr;
    }

    public static void readFromFile(String filePath, TableView<HistoryModel> tableView){

        Stack<HistoryModel> historyStack = new Stack<>();

        int colNr = getHistorySize(filePath);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {

                String[] fields = line.split(",");
                int score = Integer.parseInt(fields[0]);
                double run_time = Double.parseDouble(fields[1]);
                String storage = fields[2];
                String localTime = fields[3];

                HistoryModel newPerson = new HistoryModel(colNr, score, run_time, storage, localTime);
                historyStack.push(newPerson);
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
