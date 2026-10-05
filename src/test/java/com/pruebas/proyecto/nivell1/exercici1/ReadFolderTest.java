package com.pruebas.proyecto.nivell1.exercici1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class ReadFolderTest {
    @TempDir
    Path tempDir;

    @Test
    void listsDirectoryContentsInAlphabeticalOrder() throws Exception {
        Files.createFile(tempDir.resolve("zulu.txt"));
        Files.createFile(tempDir.resolve("alpha.txt"));
        Files.createDirectory(tempDir.resolve("Beta"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ReadFolder.main(new String[]{tempDir.toString()});

        assertThat(output.toString()).isEqualTo(
                "alpha.txt" + System.lineSeparator()
                        + "Beta" + System.lineSeparator()
                        + "zulu.txt" + System.lineSeparator()
        );
    }

    @Test
    void detectsEmptyFolder() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ReadFolder.main(new String[]{tempDir.toString()});

        assertThat(output.toString()).isEmpty();
    }

    @Test
    void detectsFilesInFolder() throws Exception {
        Files.createFile(tempDir.resolve("file.txt"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ReadFolder.main(new String[]{tempDir.toString()});

        assertThat(output.toString()).contains("file.txt");
    }
}
