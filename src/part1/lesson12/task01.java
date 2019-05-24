package part1.lesson12;

import java.util.Random;

/**
 * Необходимо создать программу, которая продемонстрирует утечку памяти в Java. При этом объекты должны не только
 * создаваться, но и периодически частично удаляться, чтобы GC имел возможность очищать часть памяти.
 * Через некоторое время программа должна завершиться с ошибкой OutOfMemoryError c пометкой Java Heap Space.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
 *
 * -XX:+UseSerialGC -Xmx100m
 */
public class task01 {
    final static int SIZE = 1_000_000_000;

    public static void main(String[] args) {
        Random rand = new Random();
        int multiplier = 100;

        try {
            Thread.sleep(10_000);                                                   /* успеть запустить JMC */

            for (int i = 0; i < SIZE; i++) {
                Thread.sleep(1);
                Integer[] value = new Integer[multiplier];

                for (int j = 0; j < value.length; j++) {
                    value[j] = rand.nextInt();
                }

                multiplier*=1.01;                                                      /* генератор утечки памяти */
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}