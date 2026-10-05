# Exercise 3: Write a directory tree to a file

`ReadFolderToFile` recursively lists the contents of a directory and writes the result to a text file. Entries are sorted alphabetically at each level, without distinguishing between upper- and lower-case letters. Directories are marked with `D`, files with `F`, and each entry includes its last-modified date in `dd/MM/yyyy HH:mm:ss` format.

## Run

From the project root, compile the project:

```bash
mvn compile
```

Run the class with the directory to list as its single argument:

```bash
java -cp target/classes com.pruebas.proyecto.nivell1.exercici3.ReadFolderToFile "/path/to/directory"
```

The result is written to
`src/main/java/com/pruebas/proyecto/nivell1/file/exercici3Result.txt`, relative to the project root. Running the program again overwrites this file.