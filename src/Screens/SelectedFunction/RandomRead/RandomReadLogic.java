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
                File file = new File("testfile");
                file.deleteOnExit();
                long fileSize = 1024 * 1024 * 1024;
                int bufferSize = 4096;
                speed= RandomReadLogic.measureRandomReadSpeed(file, fileSize, bufferSize);
                isCompleted=true;
            } catch (IOException e) {
                System.out.println(e);
            }


        });

        t2.start();
    }

    public static double measureRandomReadSpeed(File file, long fileSize, int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            for (long i = 0; i < fileSize; i += buffer.length) {
                raf.write(buffer);
            }
        }

        long startTime = System.currentTimeMillis(); 
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
        long timeTaken = endTime - startTime;
        time=timeTaken;
        return fileSize / (1024.0 * 1024.0 * timeTaken / 1000.0);
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



