package com.pruebas.proyecto.nivell1.exercici4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class ReadFileTest {
    @TempDir
    Path tempDir;

    @Test
    void printsTextFileContents() throws Exception {
        Path file = tempDir.resolve("notes.txt");
        String contents = String.join(System.lineSeparator(), "First line", "Second line");
        Files.writeString(file, contents);

        assertThat(runReadFile(file.toString())).isEqualTo(contents);
    }

    @Test
    void printsAnErrorWhenNoArgumentIsProvided() {
        assertThat(runReadFile())
                .contains("You had to add one TXT file name to read it.");
    }

    @Test
    void printsAnErrorWhenFileDoesNotExist() {
        assertThat(runReadFile(tempDir.resolve("missing.txt").toString()))
                .contains("Not possible to read file.");
    }

    @Test
    void printsAnErrorWhenPathIsADirectory() {
        assertThat(runReadFile(tempDir.toString()))
                .contains("Not possible to read file.");
    }




    private String runReadFile(String... arguments) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream testOutput = new PrintStream(output);

        try {
            System.setOut(testOutput);
            ReadFile.main(arguments);
        } finally {
            testOutput.close();
        }

        return output.toString();
    }
}
