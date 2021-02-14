package ro.sda.example;

import ro.sda.example.entity.Vehicle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterUtils<T extends Vehicle> {

    private final String BASE_FILE_PATH = "C:\\radu\\";

    public void writeVehicleToFile(String fileName, List<T> vehicles) {
        File file = new File(BASE_FILE_PATH + fileName);
        try (FileWriter fr = new FileWriter(file); BufferedWriter br = new BufferedWriter(fr)) {
            for (T v : vehicles) {
                // writing the T vehicle to the buffer
                br.write(v.toString());
                // writing a new line to buffer
                br.newLine();
                // flushing to file so we can see the changes. It is not necessary because it's done at br.close() automatically
                br.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
