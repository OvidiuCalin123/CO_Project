package Screens.SelectedFunction.CheckSize;

import Screens.SelectedFunction.SelectedFunctionLogicHandle;
import java.io.File;

import static Shared.Dropbox.getSelectedOption;

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

        File hardDrive = new File((String) getSelectedOption());
        long startTime = System.currentTimeMillis();
        long sizeInBytes = getHardDriveSizeInBytes(hardDrive);
        long sizeInGB = convertBytesToGB(sizeInBytes);
        long endTime = System.currentTimeMillis();

        speed = sizeInGB;
        time = endTime - startTime;
        isCompleted = true;
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
