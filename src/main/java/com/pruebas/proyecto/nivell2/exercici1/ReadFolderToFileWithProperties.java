package com.pruebas.proyecto.nivell2.exercici1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Properties;

public class ReadFolderToFileWithProperties {
    public static final String CONFIG_FILE = "src/main/resources/config.properties";

    public static void main(String[] args) {
        Properties properties = loadProperties();
        if (properties == null) return;

        String inputDirectory = properties.getProperty("input.directory");
        String outputFile = properties.getProperty("output.file");
        if (inputDirectory == null || outputFile == null) {
            System.out.println("Configuration is incomplete.");

            return;
        }

        File folder = new File(inputDirectory);
        if (!isValidFolder(folder)) {
            System.out.println("Not possible to read folder.");

            return;
        }

        File[] files = folder.listFiles();
        if (!canReadFiles(files)) {
            System.out.println("Not possible to read files in folder.");

            return;
        }

        try {
            FileWriter writer = new FileWriter(outputFile);
            listFolder(folder, 0, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();

        try (FileInputStream input =
                     new FileInputStream(CONFIG_FILE)) {
            properties.load(input);

            return properties;
        } catch (IOException e) {
            System.out.println("Error reading configuration file.");
            return null;
        }
    }

    public static void listFolder(
            File folder,
            int level,
            FileWriter writer
    ) throws IOException {
        File[] files = folder.listFiles();
        if (files == null) return;

        Arrays.sort(
                files,
                (file1, file2) ->
                        file1.getName()
                                .compareToIgnoreCase(file2.getName())
        );

        for (File file : files) {
            String type = file.isDirectory() ? "D" : "F";
            writer.write(
                    "    ".repeat(level)
                            + type + " "
                            + file.getName()
                            + " "
                            + getLastModifiedDate(file)
                            + System.lineSeparator()
            );

            if (file.isDirectory()) listFolder(file, level + 1, writer);
        }
    }

    public static String getLastModifiedDate(File file) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return dateFormat.format(file.lastModified());
    }

    public static boolean isValidFolder(File folder) {
        return folder.exists() && folder.isDirectory();
    }

    public static boolean canReadFiles(File[] files) {
        return files != null;
    }
}
