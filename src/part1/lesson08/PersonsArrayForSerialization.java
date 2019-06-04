package part1.lesson08;

import java.io.Serializable;

/**
 * Класс для сериализации, включающий в себя массив классов хозяев животных.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
 */
class PersonsArrayForSerialization implements Serializable {
    private Person[] persons = {};

    PersonsArrayForSerialization(Person[] persons) {
        this.persons = persons;
    }

    public Person[] getPersons() {
        return persons;
    }
}