package Screens.History;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryDataLogic {

    public static String getStorage() {
        try {
            Path root = Paths.get(System.getProperty("user.dir")).getRoot();
            FileStore store = Files.getFileStore(root);

            return store.name();

        } catch (IOException e) {
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
