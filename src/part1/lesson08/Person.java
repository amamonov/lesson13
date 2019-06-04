package part1.lesson08;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Хозяина, у которого есть домашние животные. Включает ссылочный объект массив классов.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
 */
public class Person implements Serializable {
    private String name;
    private int age;
    private String sex;
    List<Animal> animals = new ArrayList<>();

    Person(String name, int age, String sex, int animalsAmount) {
        this.name = name;
        this.age = age;
        this.sex = sex;

        getAnimals(animalsAmount);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public List<Animal> getAnimals(int animalsAmount) {
        for (int i = 0; i < animalsAmount; i++) {
            animals.add(new Animal());
        }

        return animals;
    }

    @Override
    public String toString(){
        return name + ", " + sex + ", " + age + " years old has animals: " + animals;
    }
}