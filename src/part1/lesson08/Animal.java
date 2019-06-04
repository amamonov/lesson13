package part1.lesson08;

import java.io.Serializable;
import java.security.SecureRandom;

/**
 * Случайное домашнее животное из заданного массива имен и возрастов.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
 */
public class Animal implements Serializable {
    private String name;
    private int age;
    SecureRandom rand = new SecureRandom();
    String[] names = {"Dinka", "Cherniy", "Druzhok", "Mus'ka", "Barsik", "Kotofey"};

    Animal() {
        name = names[rand.nextInt(6)];
        age = rand.nextInt(20);
    }

    @Override
    public String toString(){
        return name + " " + age + " years old";
    }
}