package com.pruebas.proyecto.nivell1.exercici4;

import java.io.*;

public class ReadFile {
    public static void main(String[] args) {
        if (!hasValidArgument(args)) {
            System.out.println("Error: Needs only one argument.");

            return;
        }

        File inputFile = new File(args[0]);
        if (!fileExists(inputFile)) {
            System.out.println("Error: File does not exists.");

            return;
        }

        if (!isFile(inputFile)) {
            System.out.println("Error: the specified path is not a file.");

            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(inputFile))) {

            String line;
            while ((line = reader.readLine()) != null) System.out.println(line);

        } catch (IOException exception) {
            System.out.println("Error reading the file: " + exception.getMessage());
        }
    }

    public static boolean hasValidArgument(String[] args) {
        return args.length == 1;
    }

    public static boolean fileExists(File inputFile) {
        return inputFile.exists();
    }

    public static boolean isFile(File inputFile) {
        return inputFile.isFile();
    }
}
