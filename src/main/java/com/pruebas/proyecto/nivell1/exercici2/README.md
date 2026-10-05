# Exercise 2: List a directory tree

`ReadFolderExtends` lists the contents of a directory and recursively lists the contents of its subdirectories. Entries at each level are sorted alphabetically, without distinguishing between upper- and lower-case letters.

Directories are marked with `D` and files with `F`. Each nested level is indented by four spaces.

## Run

From the project root, compile the project:

```bash
mvn compile
```

Run the class with the directory path as its single argument:

```bash
java -cp target/classes com.pruebas.proyecto.nivell1.exercici2.ReadFolderExtends "/path/to/directory"
```

For example, the output may look like this:

```text
D documents
    F notes.txt
F photo.jpg
```

## Tests

Run the tests with:

```bash
mvn test
```
