package Screens.SelectedFunction.CheckSize;

import java.io.File;

public class CheckSizeLogic {

    public long getHardDriveSizeInBytes(File drive) {
        return drive.getTotalSpace();
    }

    public long convertBytesToGB(long bytes) {
        return bytes / (1024 * 1024 * 1024);
    }

    public void run(){
        File hardDrive = new File("C:\\");
        long sizeInBytes = getHardDriveSizeInBytes(hardDrive);
        long sizeInGB = convertBytesToGB(sizeInBytes);
        System.out.println("Size of hard drive in bytes: " + sizeInBytes);
        System.out.println("Size of hard drive in GB: " + sizeInGB);
    }

}
