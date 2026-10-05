package com.pruebas.proyecto.nivell1.exercici3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReadFolderToFileTest {
    @TempDir
    Path tempDir;

    @Test
    void writesFilesAndDirectoriesToFile() throws Exception {

        Files.createFile(tempDir.resolve("zulu.txt"));
        Files.createFile(tempDir.resolve("alpha.txt"));

        Files.createDirectory(tempDir.resolve("Beta"));
        Files.createFile(tempDir.resolve("Beta").resolve("inside.txt"));

        ReadFolderToFile.main(new String[]{tempDir.toString()});

        Path resultFile = Path.of(
                "src/main/java/com/pruebas/proyecto/nivell1/file/exercici3Result.txt"
        );

        String output = Files.readString(resultFile);

        assertThat(output)
                .contains("F alpha.txt")
                .contains("D Beta")
                .contains("    F inside.txt")
                .contains("F zulu.txt");
    }
}
