package Screens.SelectedFunction.SequentialWrite;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;
import javafx.beans.binding.Bindings;
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
