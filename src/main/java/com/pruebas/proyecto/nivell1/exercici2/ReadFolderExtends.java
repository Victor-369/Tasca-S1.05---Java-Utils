package com.pruebas.proyecto.nivell1.exercici2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class ReadFolderExtends {
    public static void main(String[] args) {
        if (!hasValidArgument(args)) {
            System.out.println("You had to add one folder name to read it. ");

            return;
        }

        String ruta = args[0];

        File folder = new File(ruta);
        if (!isValidFolder(folder)) {
            System.out.println("Not possible to read folder.");

            return;
        }

        File[] files = folder.listFiles();
        if (!canReadFiles(files)) {
            System.out.println("Not possible to read files in folder.");

            return;
        }

        listFolder(folder, 0);
    }

    public static void listFolder(File folder, int level) {

        File[] files = folder.listFiles();

        if (files == null) {
            return;
        }

        Arrays.sort(files,
                (file1, file2) ->
                        file1.getName().compareToIgnoreCase(file2.getName()));

        for (File file : files) {

            String type = file.isDirectory() ? "D" : "F";

            System.out.println(
                    "    ".repeat(level)
                            + type + " "
                            + file.getName()
                            + " "
                            + getLastModifiedDate(file)
            );

            if (file.isDirectory()) {
                listFolder(file, level + 1);
            }
        }
    }

    public static String getLastModifiedDate(File file) {
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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
