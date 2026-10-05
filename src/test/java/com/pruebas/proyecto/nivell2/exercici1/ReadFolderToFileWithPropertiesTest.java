package com.pruebas.proyecto.nivell2.exercici1;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.assertj.core.api.Assertions.assertThat;

class ReadFolderToFileWithPropertiesTest {
    @TempDir
    Path tempDir;

    @Test
    void loadsConfigurationWithInputAndOutputPaths() {
        Properties properties = ReadFolderToFileWithProperties.loadProperties();

        assertThat(properties).isNotNull();
        assertThat(properties.getProperty("input.directory")).isNotBlank();
        assertThat(properties.getProperty("output.file")).isNotBlank();
    }

    @Test
    void identifiesValidDirectories() throws Exception {
        Path folder = Files.createDirectory(tempDir.resolve("folder"));
        Path file = Files.createFile(tempDir.resolve("file.txt"));

        assertThat(ReadFolderToFileWithProperties.isValidFolder(folder.toFile())).isTrue();
        assertThat(ReadFolderToFileWithProperties.isValidFolder(file.toFile())).isFalse();
        assertThat(ReadFolderToFileWithProperties.isValidFolder(
                tempDir.resolve("missing").toFile()
        )).isFalse();
    }

    @Test
    void identifiesWhetherDirectoryContentsCanBeListed() throws Exception {
        File[] files = {Files.createFile(tempDir.resolve("file.txt")).toFile()};

        assertThat(ReadFolderToFileWithProperties.canReadFiles(files)).isTrue();
        assertThat(ReadFolderToFileWithProperties.canReadFiles(null)).isFalse();
    }

    @Test
    void formatsLastModifiedDate() throws Exception {
        File file = Files.createFile(tempDir.resolve("file.txt")).toFile();

        assertThat(ReadFolderToFileWithProperties.getLastModifiedDate(file))
                .matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}");
    }

    @Test
    void writesDirectoryContentsRecursivelyInAlphabeticalOrder() throws Exception {
        Path folder = Files.createDirectory(tempDir.resolve("folder"));
        Path betaFolder = Files.createDirectory(folder.resolve("Beta"));
        File alphaFile = Files.createFile(folder.resolve("alpha.txt")).toFile();
        File zuluFile = Files.createFile(folder.resolve("zulu.txt")).toFile();
        File betaDirectory = betaFolder.toFile();
        File nestedFile = Files.createFile(betaFolder.resolve("inside.txt")).toFile();
        Path outputFile = tempDir.resolve("listing.txt");

        try (FileWriter writer = new FileWriter(outputFile.toFile())) {
            ReadFolderToFileWithProperties.listFolder(folder.toFile(), 0, writer);
        }

        String output = Files.readString(outputFile);
        String expectedOutput = "F alpha.txt "
                + ReadFolderToFileWithProperties.getLastModifiedDate(alphaFile)
                + System.lineSeparator()
                + "D Beta "
                + ReadFolderToFileWithProperties.getLastModifiedDate(betaDirectory)
                + System.lineSeparator()
                + "    F inside.txt "
                + ReadFolderToFileWithProperties.getLastModifiedDate(nestedFile)
                + System.lineSeparator()
                + "F zulu.txt "
                + ReadFolderToFileWithProperties.getLastModifiedDate(zuluFile)
                + System.lineSeparator();

        assertThat(output).isEqualTo(expectedOutput);
    }
}
