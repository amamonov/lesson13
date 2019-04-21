package part1.lesson01;

import java.security.SecureRandom;
import java.util.*;

/**
 * Урок 1, задание 2
 * Составить программу, генерирующую N случайных чисел. Для каждого числа k вычислить квадратный корень q.
 * Если квадрат целой части q числа равен k, то вывести это число на экран.
 * Предусмотреть что первоначальные числа могут быть отрицательные, в этом случае генерировать исключение.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
 */
public class task02 {
    public static void main(String[] args) {
        /** @value Количество элементов пользовательского массива */
        int lim = 10;

        int[] arr = new int[lim];

        /** @value Массив для вычислительных операций */
        double[] sqrt = new double[lim];

        try {
            Random rand = new SecureRandom();
            for (int i = 0; i < arr.length; i++) {
                int temp = rand.nextInt(lim) - 1;//Math.round(lim/2);
                if (temp < 0)
                    throw new Task02Exception();
                else {
                    arr[i] = temp;
                    sqrt[i] = Math.sqrt((double)arr[i]);
                }
            }
        }
        catch (Task02Exception e){
            System.err.println("Task02Exception");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == (int)Math.pow(sqrt[i], 2))
                System.out.println(arr[i]);
        }
    }
}

class Task02Exception extends Exception {}