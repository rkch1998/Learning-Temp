package com.connect.DB.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FunctionWriter {

    public void writeToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("Data written to file successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
