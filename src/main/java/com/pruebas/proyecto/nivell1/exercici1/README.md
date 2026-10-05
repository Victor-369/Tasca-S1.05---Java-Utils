# Exercise 1: List a directory

`ReadFolder` lists the contents of a directory supplied as a command-line argument. Entries are sorted alphabetically, without distinguishing between upper- and lower-case letters.

## Run

From the project root, compile the project:

```bash
mvn compile
```

Then run the class with the directory path as its single argument:

```bash
java -cp target/classes com.pruebas.proyecto.nivell1.exercici1.ReadFolder "/path/to/directory"
```

The listing includes files and subdirectories in the specified directory; it does not recurse into subdirectories.

## Tests

Run the tests with:

```bash
mvn test
```
