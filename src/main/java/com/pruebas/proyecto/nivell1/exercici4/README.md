# Exercise 4: Read the directory listing

`ReadFile` continues Exercise 3: it reads the `exercici3Result.txt` file created
by `ReadFolderToFile` and prints its contents to the console. Run Exercise 3
first; Exercise 4 does not need any arguments.

## Run

From the project root, compile the project:

```bash
mvn compile
```

First create the directory listing:

```bash
java -cp target/classes com.pruebas.proyecto.nivell1.exercici3.ReadFolderToFile "/path/to/directory"
```

Then print the generated file:

```bash
java -cp target/classes com.pruebas.proyecto.nivell1.exercici4.ReadFile
```

The generated file is read using UTF-8 encoding.
