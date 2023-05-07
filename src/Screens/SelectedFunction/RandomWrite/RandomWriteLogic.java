package Screens.SelectedFunction.RandomWrite;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomWriteLogic implements SelectedFunctionLogicHandle {

    private static double speed;
    private static double time;
    private static boolean isCompleted=false;

    public void run() throws IOException {
        Thread t2 = new Thread(() -> {
            try {
                File file = new File("testfile");
                file.deleteOnExit();
                long fileSize = 1024 * 1024 * 1024;
                int bufferSize = 4096;
                double writeSpeed = RandomWriteLogic.measureRandomWriteSpeed(file, fileSize, bufferSize);
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
        time=timeTaken;
        double writeSpeed = fileSize / (1024.0 * 1024.0 * timeTaken / 1000.0);
        speed=writeSpeed;
        isCompleted=true;
        return writeSpeed;
    }

    public boolean getIsCompleted(){

        return isCompleted;
    }

    public void setIsCompleted(boolean value){

        isCompleted = value;
    }

    public double getTime(){

        return time/1000;
    }

    public double getScore(){

        return speed;
    }
}
