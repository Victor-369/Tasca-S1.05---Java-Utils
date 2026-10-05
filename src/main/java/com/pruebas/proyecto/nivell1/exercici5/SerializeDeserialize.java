package com.pruebas.proyecto.nivell1.exercici5;

import com.pruebas.proyecto.nivell1.exercici5.model.Person;

import java.io.*;

public class SerializeDeserialize {
    public static void main(String[] args) {
        if (!hasValidArgument(args)) {
            System.out.println("Error: Needs only one argument.");
            return;
        }

        File serialisedFile = new File(args[0]);
        File parentDirectory = serialisedFile.getParentFile();

        if (parentDirectory != null && !parentDirectory.exists()) {
            if (!parentDirectory.mkdirs()) {
                System.out.println("Error: unable to create the output directory.");

                return;
            }
        }

        Person person = new Person(
                "Alice Smith",
                25,
                "alice.smith@example.com"
        );

        serialise(person, serialisedFile);

        Person deserialisedPerson = deserialise(serialisedFile);
        if (deserialisedPerson != null) {
            System.out.println("Deserialised object:");
            System.out.println(deserialisedPerson);
        }
    }

    public static boolean hasValidArgument(String[] args) {
        return args.length == 1;
    }

    private static void serialise(Person person, File file) {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(
                             new FileOutputStream(file))) {

            outputStream.writeObject(person);
            System.out.println("Object successfully serialised to: "+ file.getPath());
        } catch (IOException exception) {
            System.out.println("Error serialising the object: " + exception.getMessage());
        }
    }

    private static Person deserialise(File file) {
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            Object object = inputStream.readObject();

            if (object instanceof Person person) {
                System.out.println("Object successfully deserialised.");

                return person;
            }

            System.out.println("Error: the file does not contain a Person object.");

        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Error deserialising the object: " + exception.getMessage());
        }

        return null;
    }
}
