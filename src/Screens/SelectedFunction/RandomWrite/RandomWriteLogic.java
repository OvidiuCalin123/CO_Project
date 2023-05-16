package Screens.SelectedFunction.RandomWrite;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomWriteLogic implements SelectedFunctionLogicHandle {
    private static double speed;
    private static double time;
    private static boolean isCompleted = false;

    public void run() throws IOException {
        Thread t2 = new Thread(() -> {
            try {

                File file = new File("testing");
                file.deleteOnExit();

                long fileSize = 1024 * 1024 * 1024;
                int bufferSize = 4096;

                long startTime = System.currentTimeMillis();

                speed = RandomWriteLogic.measureRandomWriteSpeed(file, fileSize, bufferSize);

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

                long fileSize = 1024 * 1024 * 1024;
                int bufferSize = 4096;

                RandomWriteLogic.measureRandomWriteSpeed(file, fileSize, bufferSize);

            } catch (IOException e) {
                System.out.println(e);
            }

        });

        t2.start();
    }

    public static double measureRandomWriteSpeed(File file, long fileSize, int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];

        long startTime = System.currentTimeMillis();

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            long bytesToWrite = fileSize;
            while (bytesToWrite > 0) {
                long pos = (long) (Math.random() * fileSize);
                raf.seek(pos);
                raf.write(buffer);
                bytesToWrite -= buffer.length;
            }
        }

        long endTime = System.currentTimeMillis();

        long timeTaken = endTime - startTime;

        return fileSize / (1024.0 * 1024.0 * timeTaken / 1000.0);
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
