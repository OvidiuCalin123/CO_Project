package Screens.SelectedFunction.CheckSize;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;
import java.io.File;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Shared.Dropbox.getSelectedOption;

public class CheckSizeLogic implements SelectedFunctionLogicHandle {

    private static double speed;
    private static double time;
    private static boolean isCompleted=false;

    public double getHardDriveSizeInBytes(File drive) {

        return drive.getTotalSpace();
    }

    public double convertBytesToGB(double bytes) {

        return bytes / (1024 * 1024 * 1024);
    }

    public static long printRemainingSpace() {
        try {
            File hardDrive = new File((String) getSelectedOption());

            long freeSpace = hardDrive.getFreeSpace();

            return freeSpace / (1024 * 1024 * 1024);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return -1;
    }



    public void run(){

        File hardDrive = new File((String) getSelectedOption());
        long startTime = System.currentTimeMillis();
        double sizeInBytes = getHardDriveSizeInBytes(hardDrive);
        double sizeInGB = convertBytesToGB(sizeInBytes);
        long endTime = System.currentTimeMillis();

        speed = sizeInGB;
        time = endTime - startTime;
        isCompleted = true;
    }
    public void runWarmUp(){

        File hardDrive = new File((String) getSelectedOption());
        double sizeInBytes = getHardDriveSizeInBytes(hardDrive);
        System.out.println(convertBytesToGB(sizeInBytes));
        double sizeInGB = convertBytesToGB(sizeInBytes);

        speed = sizeInGB;

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
