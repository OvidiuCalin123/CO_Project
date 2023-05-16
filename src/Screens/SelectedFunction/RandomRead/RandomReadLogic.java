package Screens.SelectedFunction.RandomRead;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomReadLogic implements SelectedFunctionLogicHandle {
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

                speed = RandomReadLogic.measureRandomReadSpeed(file, fileSize, bufferSize);
                isCompleted = true;

                long endTime = System.currentTimeMillis();

                time = endTime - startTime;

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

                RandomReadLogic.measureRandomReadSpeed(file, fileSize, bufferSize);

            } catch (IOException e) {
                System.out.println(e);
            }

        });

        t2.start();
    }

    public static double measureRandomReadSpeed(File file, long fileSize, int bufferSize) throws IOException {

        long startTime = System.currentTimeMillis();

        byte[] buffer = new byte[bufferSize];
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            for (long i = 0; i < fileSize; i += buffer.length) {
                raf.write(buffer);
            }
        }

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long bytesToRead = fileSize;
            while (bytesToRead > 0) {

                long pos = (long) (Math.random() * fileSize);
                raf.seek(pos);

                int bytesRead = raf.read(buffer, 0, buffer.length);
                if (bytesRead < 0) {
                    break;
                }
                bytesToRead -= bytesRead;
            }
        }

        long endTime = System.currentTimeMillis();

        return fileSize / (1024.0 * 1024.0 * (endTime - startTime) / 1000.0);
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



