package Screens.SelectedFunction.RandomRead;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomReadLogic {

    private static double speed;
    public static double measureRandomReadSpeed(File file, long fileSize, int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];
        // Fill the created file with random data.
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            for (long i = 0; i < fileSize; i += buffer.length) {
                raf.write(buffer);
            }
        }
        // Measure the random read speed
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
        // Calculate the time taken and return the results.
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        double readSpeed = fileSize / (1024.0 * 1024.0 * timeTaken / 1000.0);
        return readSpeed;
    }
    public void run() throws IOException {
        File file = new File("testfile");
        file.deleteOnExit();
        long fileSize = 1024 * 1024 * 1024; // 1 GB for testing, implement user input or dropdown later.
        int bufferSize = 4096;
        double readSpeed = RandomReadLogic.measureRandomReadSpeed(file, fileSize, bufferSize);
        System.out.printf("Random read speed: %.2f MB/s\n", readSpeed);
        speed=readSpeed;
        System.out.println(speed);
    }

    public double getReadSpeed(){
        return speed;
    }
}


