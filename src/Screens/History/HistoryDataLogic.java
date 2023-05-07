package Screens.History;

import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryDataLogic {

    public static String getStorage(){
        try {

            Path root = FileSystems.getDefault().getRootDirectories().iterator().next();
            FileStore store = Files.getFileStore(root);
            System.out.println(store.name()+" ceva ");
            return store.name();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return "";
    }

    public static String getLocalTime(){

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd HH:mm:ss yyyy");

        return now.format(formatter);
    }
}
