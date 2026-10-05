package com.pruebas.proyecto.nivell1.exercici4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.assertj.core.api.Assertions.assertThat;

class ReadFileTest {
    @TempDir
    Path tempDir;

    @Test
    void printsContentsOfTheGivenFile() throws Exception {
        Path file = tempDir.resolve("notes.txt");
        Files.writeString(file, "First line" + System.lineSeparator() + "Second line");

        assertThat(runReadFile(file.toString()))
                .isEqualTo("First line" + System.lineSeparator()
                        + "Second line" + System.lineSeparator());
    }

    @Test
    void printsAnErrorWhenArgumentIsMissing() {
        assertThat(runReadFile())
                .contains("Error: Needs only one argument.");
    }

    @Test
    void printsAnErrorWhenFileDoesNotExist() {
        assertThat(runReadFile(tempDir.resolve("missing.txt").toString()))
                .contains("Error: File does not exists.");
    }

    @Test
    void printsAnErrorWhenPathIsNotAFile() {
        assertThat(runReadFile(tempDir.toString()))
                .contains("Error: the specified path is not a file.");
    }

    private String runReadFile(String... arguments) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try (PrintStream testOutput = new PrintStream(output)) {
            System.setOut(testOutput);
            ReadFile.main(arguments);
        } finally {
            System.setOut(originalOut);
        }

        return output.toString();
    }
}
