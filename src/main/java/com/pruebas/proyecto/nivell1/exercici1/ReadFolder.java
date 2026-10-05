package com.pruebas.proyecto.nivell1.exercici1;


import java.io.File;
import java.util.Arrays;

public class ReadFolder {
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

        Arrays.sort(files,
                (file1, file2) -> file1.getName().compareToIgnoreCase(file2.getName()));

        for (File file : files) System.out.println(file.getName());
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
