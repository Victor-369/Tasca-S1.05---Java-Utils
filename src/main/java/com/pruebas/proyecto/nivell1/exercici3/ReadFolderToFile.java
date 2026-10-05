package com.pruebas.proyecto.nivell1.exercici3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class ReadFolderToFile {
    public static void main(String[] args) {
        if (!hasValidArgument(args)) {
            System.out.println("You had to add one folder name to read it.");
            return;
        }

        File folder = new File(args[0]);

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
            FileWriter writer = new FileWriter(
                    "src/main/java/com/pruebas/proyecto/nivell1/file/exercici3Result.txt"
            );

            listFolder(folder, 0, writer);

            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    public static void listFolder(File folder, int level, FileWriter writer) throws IOException {
        File[] files = folder.listFiles();
        if (files == null) return;

        Arrays.sort(files,
                (file1, file2) ->
                        file1.getName().compareToIgnoreCase(file2.getName()));

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

    public static boolean hasValidArgument(String[] args) {
        return args.length == 1;
    }

    public static boolean isValidFolder(File folder) {
        return folder.exists() && folder.isDirectory();
    }

    public static boolean canReadFiles(File[] files) {
        return files != null;
    }
}
