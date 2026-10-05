package com.pruebas.proyecto.nivell1.exercici5;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import static org.assertj.core.api.Assertions.assertThat;

class SerializeDeserializeTest {
    @TempDir
    Path tempDir;

    @Test
    void serialisesAndDeserialisesPerson() throws Exception {
        Path outputFile = tempDir.resolve("person.ser");

        String output = runProgram(outputFile.toString());

        assertThat(Files.exists(outputFile)).isTrue();
        assertThat(output)
                .contains("Object successfully serialised to: " + outputFile)
                .contains("Object successfully deserialised.")
                .contains("Deserialised object:")
                .contains("Person{name='Alice Smith', age=25, email='alice.smith@example.com'}");

        try (ObjectInputStream inputStream =
                     new ObjectInputStream(Files.newInputStream(outputFile))) {
            assertThat(inputStream.readObject().toString())
                    .isEqualTo("Person{name='Alice Smith', age=25, email='alice.smith@example.com'}");
        }
    }

    @Test
    void createsMissingParentDirectories() {
        Path outputFile = tempDir.resolve("new-folder").resolve("person.ser");

        String output = runProgram(outputFile.toString());

        assertThat(Files.exists(outputFile)).isTrue();
        assertThat(output).contains("Object successfully serialised to: " + outputFile);
    }

    @Test
    void printsAnErrorWhenArgumentIsMissing() {
        assertThat(runProgram())
                .contains("Error: Needs only one argument.");
    }

    @Test
    void printsAnErrorWhenMoreThanOneArgumentIsProvided() {
        assertThat(runProgram("first.ser", "second.ser"))
                .contains("Error: Needs only one argument.");
    }

    @Test
    void reportsErrorsWhenParentPathIsAFile() throws Exception {
        Path existingFile = Files.createFile(tempDir.resolve("existing-file"));
        Path outputFile = existingFile.resolve("person.ser");

        assertThat(runProgram(outputFile.toString()))
                .contains("Error serialising the object:")
                .contains("Error deserialising the object:");
    }

    private String runProgram(String... arguments) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try (PrintStream testOutput = new PrintStream(output)) {
            System.setOut(testOutput);
            SerializeDeserialize.main(arguments);
        } finally {
            System.setOut(originalOut);
        }

        return output.toString();
    }
}
