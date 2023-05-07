package Screens.SelectedFunction.SequentialWrite;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static Shared.Dropbox.getSelectedOption;

public class SequentialWriteLogic implements SelectedFunctionLogicHandle{

    private static double speed;
    private static double time;
    private static boolean isCompleted=false;

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
                long fileSize = (long)getSelectedOption();
                int bufferSize = 4096;
                double writeSpeed = SequentialWriteLogic.measureSequentialWriteSpeed(file, fileSize, bufferSize);
            } catch (IOException e) {
                System.out.println(e);
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
