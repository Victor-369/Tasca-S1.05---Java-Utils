# Exercise 1: Write a directory tree using configuration

`ReadFolderToFileWithProperties` reads the input directory and output file paths from `src/main/resources/config.properties`, then writes a recursively sorted directory listing to the configured text file. Directories are marked with `D`, files with `F`, and each entry includes its last-modified date in `dd/MM/yyyy HH:mm:ss` format.

## Configure

Edit `src/main/resources/config.properties` from the project root:

```properties
input.directory=src/main/java/com/pruebas/proyecto/nivell1/file
output.file=src/main/java/com/pruebas/proyecto/nivell1/file/exercici3Result.txt
```

Both paths are interpreted relative to the directory from which the program is run.

## Run

From the project root, compile the project:

```bash
mvn compile
```

Run the class:

```bash
java -cp target/classes com.pruebas.proyecto.nivell2.exercici1.ReadFolderToFileWithProperties
```

The program requires no command-line arguments. Running it again overwrites the configured output file.
