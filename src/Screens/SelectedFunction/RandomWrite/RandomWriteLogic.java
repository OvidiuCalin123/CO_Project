package Screens.SelectedFunction.RandomWrite;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomWriteLogic implements SelectedFunctionLogicHandle {

    private static double speed;
    private static double time;
    private static boolean isCompleted=false;

    public static double measureRandomWriteSpeed(File file, long fileSize, int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];
        // Measure the random write speed
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
                double writeSpeed = RandomWriteLogic.measureRandomWriteSpeed(file, fileSize, bufferSize);
                System.out.printf("Random write speed: %.2f MB/s\n", writeSpeed);
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

    public double getReadSpeed(){
        return speed;
    }

}
