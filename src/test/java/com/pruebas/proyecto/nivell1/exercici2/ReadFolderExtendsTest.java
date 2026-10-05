package com.pruebas.proyecto.nivell1.exercici2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class ReadFolderExtendsTest {
    @TempDir
    Path tempDir;

    @Test
    void listsFilesAndDirectoriesRecursively() throws Exception {
        Files.createFile(tempDir.resolve("zulu.txt"));
        Files.createFile(tempDir.resolve("alpha.txt"));

        Files.createDirectory(tempDir.resolve("Beta"));
        Files.createFile(tempDir.resolve("Beta").resolve("inside.txt"));

        Files.createDirectory(tempDir.resolve("Beta").resolve("child"));
        Files.createFile(tempDir.resolve("Beta").resolve("child").resolve("deep.txt"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ReadFolderExtends.main(new String[]{tempDir.toString()});

        assertThat(output.toString(StandardCharsets.UTF_8)).contains(
                "F alpha.txt",
                "D Beta",
                "    D child",
                "        F deep.txt",
                "    F inside.txt",
                "F zulu.txt"
        );
    }
}
