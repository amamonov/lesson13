package part1.lesson08;

import java.io.*;

/**
 * Задание 1. Необходимо разработать класс, реализующий следующие методы:
 *
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 *
 * Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
 * Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 *
 * Задание 2. Предусмотреть работу c любыми типами полей (полями могут быть ссылочные типы).
 * Требование: Использовать готовые реализации (Jaxb, jackson и т.д.) запрещается.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
 */
public class task01{
    private static final long serialVersionID = 1234567890L;

    public static void main(String[] args) {
        Person[] persons = {new Person("Denis", 10, "male", 5),
                            new Person("Alexander", 36, "male", 1),
                            new Person("Vladislav", 4, "male", 2),
                            new Person("Zinaida", 31, "female", 2)};

        PersonsArrayForSerialization ps = new PersonsArrayForSerialization(persons);

        String file = "./file.bin";

        try {
            printPersons("Persons before serialization: ", persons);
            serialize(ps, file);
            printFile("Serializated file: ", file);
            ps = null;
            ps = (PersonsArrayForSerialization) deSerialie(file);
            printPersons("Persons after serialization: ", ps.getPersons());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void serialize(Object object, String file) throws Exception{
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Object deSerialie(String file) throws Exception{
        Object object = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            object = ois.readObject();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (EOFException e) {
            e.printStackTrace();
        }

        return object;
    }

    static void printPersons(String intro, Person[] persons) {
        System.out.println(intro);
        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i]);
        }
    }

    static void printFile(String intro, String file) {
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            int i = -1;
            while ((i = fis.read()) != -1) {
                str += (char)i;
            }
            fis.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n" + intro);
        System.out.println(str + "\n");
    }

//    static void serializePersons(Person[] persons, String file) {
//        try {
//            for (int i = 0; i < persons.length; i++) {
//                serialize(persons[i], file);
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    static Person[] deSerialiePerson(String file, Person[] persons) {
//            try {
//                for (int i = 0; i < 4; i++) {
//                    persons[i] = (Person)deSerialie(file);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        return persons;
//    }
//
//    static Person[] resetPerson(Person[] persons) {
//        for (int i = 0; i < persons.length; i++) {
//            persons[i] = null;
//        }
//
//        return persons;
//    }
}