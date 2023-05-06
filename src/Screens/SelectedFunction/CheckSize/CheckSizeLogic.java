package Screens.SelectedFunction.CheckSize;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class CheckSizeLogic implements SelectedFunctionLogicHandle {

    private static double speed;
    private static double time;
    private static boolean isCompleted=false;

    public long getHardDriveSizeInBytes(File drive) {
        return drive.getTotalSpace();
    }

    public long convertBytesToGB(long bytes) {
        return bytes / (1024 * 1024 * 1024);
    }

    public void run(){
        File hardDrive = new File("C:\\");
        long startTime = System.currentTimeMillis();
        long sizeInBytes = getHardDriveSizeInBytes(hardDrive);
        long sizeInGB = convertBytesToGB(sizeInBytes);
        long endTime = System.currentTimeMillis();
        if (hardDrive.exists() && hardDrive.isDirectory()) {
            try {
                long realSize = FileUtils.sizeOfDirectory(hardDrive);
                long realSizeInGB = convertBytesToGB(realSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Drive not found.");
        }
        speed = realSizeInGB;
        sizeInBytes=realSize;
        time = endTime - startTime;
        isCompleted = true;
        System.out.println("Size of hard drive in bytes: " + sizeInBytes);
        System.out.println("Size of hard drive in GB: " + sizeInGB);
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
