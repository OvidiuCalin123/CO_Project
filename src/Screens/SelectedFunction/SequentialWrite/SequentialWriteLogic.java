package Screens.SelectedFunction.SequentialWrite;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static Shared.Dropbox.getSelectedOption;

public class SequentialWriteLogic implements SelectedFunctionLogicHandle {
    private static double speed;
    private static double time;
    private static boolean isCompleted = false;

    public static double measureSequentialWriteSpeed(File file, long fileSize, int bufferSize) throws IOException {

        byte[] buffer = new byte[bufferSize];

        long startTime = System.currentTimeMillis();

        try (FileOutputStream fos = new FileOutputStream(file)) {
            long bytesToWrite = fileSize;
            while (bytesToWrite > 0) {
                int bytesToWriteNow = (int) Math.min(bytesToWrite, buffer.length);
                fos.write(buffer, 0, bytesToWriteNow);
                bytesToWrite -= bytesToWriteNow;
            }
        }

        long endTime = System.currentTimeMillis();

        return fileSize / (1024.0 * 1024.0 * (endTime - startTime) / 1000.0);
    }

    public void run() throws IOException {
        Thread t2 = new Thread(() -> {
            try {

                File file = new File("testing");
                file.deleteOnExit();

                long fileSize = (long) getSelectedOption();
                int bufferSize = 4096;

                long startTime = System.currentTimeMillis();

                speed = SequentialWriteLogic.measureSequentialWriteSpeed(file, fileSize, bufferSize);

                long endTime = System.currentTimeMillis();
                time = endTime - startTime;

                isCompleted = true;

            } catch (IOException e) {
                System.out.println(e);
            }

        });
        t2.start();
    }

    public void runWarmUp() {
        Thread t2 = new Thread(() -> {
            try {

                File file = new File("testing");
                file.deleteOnExit();

                long fileSize = (long) getSelectedOption();
                int bufferSize = 4096;

                SequentialWriteLogic.measureSequentialWriteSpeed(file, fileSize, bufferSize);

            } catch (IOException e) {
                System.out.println(e);
            }

        });
        t2.start();
    }

    public boolean getIsCompleted() {
        return !isCompleted;
    }

    public void setIsCompleted(boolean value) {
        isCompleted = value;
    }

    public double getTime() {
        return time / 1000;
    }

    public double getScore() {
        return speed;
    }
}
