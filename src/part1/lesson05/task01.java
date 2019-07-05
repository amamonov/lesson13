package part1.lesson05;

import java.util.*;

/**
 * Разработать программу – картотеку домашних животных. У каждого животного есть уникальный идентификационный номер,
 * кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 *
 * Реализовать:
 *
 *     метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной
 *     ситуации)
 *     поиск животного по его кличке (поиск должен быть эффективным)
 *     изменение данных животного по его идентификатору
 *     вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 *
 * @author Alexander.Mamonov#protonmail.ch
 * @version 2.0
 */
public class task01 {
    public static void main(String[] args) {
        AnimalMaster master = new AnimalMaster();
        Animal animal = new Animal();
        ActAnimal animalList = new ActAnimal();

        master = master.generateMaster("Alexander", 36, "male");

        animalList.addAnimal(animal.generateAnimal("Druzhok", master, 14));
        animalList.addAnimal(animal.generateAnimal("Dinka", master, 12));
        animalList.addAnimal(animal.generateAnimal("Dinka", master, 12));     /* Для проверки исключения*/
        animalList.addAnimal(animal.generateAnimal("Bobik", master, 10));
        animalList.addAnimal(animal.generateAnimal("Mus'ka", master, 6));
        animalList.addAnimal(animal.generateAnimal("Homka", master, 0));
        animalList.addAnimal(animal.generateAnimal("Kesha", master, 1));

        master = master.generateMaster("Zinaida", 31, "female");

        animalList.addAnimal(animal.generateAnimal("Cherniy", master, 14));
        animalList.addAnimal(animal.generateAnimal("Tasha", master, 6));
        animalList.addAnimal(animal.generateAnimal("Yurok", master, 0));

        System.out.println("\nLooking for the animal...");
        animalList.printAnimal(animalList.findAnimal("Mus'ka"));
        System.out.println("\nChanging for the animal...");                                 /* Перезаписываем id = 1 */
        animalList.printAnimal(animalList.changeAnimal(1, "Pleshivik", master, 6));

        animalList.sortAndPrint();
    }
}