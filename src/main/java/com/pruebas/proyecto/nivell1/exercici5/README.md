# Exercise 5: Serialize and deserialize an object

`SerializeDeserialize` serializes a sample `Person` object to a file, then reads the object back and prints it to the console. The output file path is supplied as the only argument. If its parent directory does not exist, the program attempts to create it.

## Run

From the project root, compile the project:

```bash
mvn compile
```

Run the class with the desired output file path:

```bash
java -cp target/classes com.pruebas.proyecto.nivell1.exercici5.SerializeDeserialize "output/person.ser"
```

The program prints a confirmation when serialization and deserialization succeed, followed by the deserialized `Person`.