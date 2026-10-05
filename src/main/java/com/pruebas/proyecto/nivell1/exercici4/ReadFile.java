package com.pruebas.proyecto.nivell1.exercici4;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ReadFile {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("You had to add one TXT file name to read it.");
            return;
        }

        File file = new File(args[0]);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Not possible to read file.");
            return;
        }

        try {
            String contents = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            System.out.print(contents);
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}
