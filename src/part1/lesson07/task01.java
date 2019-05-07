package part1.lesson07;

import java.security.SecureRandom;

/**
 * 1. Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива. Использовать пул потоков
 * для решения задачи.
 *
 * Особенности выполнения:
 *
 * Для данного примера использовать рекурсию - не очень хороший вариант, т.к. происходит большое выделение памяти, очень вероятен
 * StackOverFlow. Лучше перемножать числа в простом цикле при этом создавать объект типа BigInteger
 *
 * По сути, есть несколько способа решения задания:
 *
 * 1) распараллеливать вычисление факториала для одного числа
 * 2) распараллеливать вычисления для разных чисел
 * 3) комбинированный
 *
 * Причем вычислив факториал для одного числа, можно запомнить эти данные и использовать их для вычисления другого, что будет
 * гораздо быстрее.
 *
 * @autor Alexander.Mamonov@protonmail.ch;
 * @version 1.0
 */
public class task01 {
    public static void main(String[] args) {
        int arraySize = 10;
        int valueLimit = 10000;

        factorial(arraySize, valueLimit);
    }

    /**
     * Автоматически генерируем потоки и в каждоом вычислям факториал
     * @param arraySize - максимальный размер входного массива
     * @param valueLimit - максимальное значение элемента массива
     */
    public static void factorial(int arraySize, int valueLimit) {
        SecureRandom rand = new SecureRandom();
        System.out.printf("%-20s%-15s\n", "Default array", "Factorial");
        for (int i = 0; i < arraySize; i++) {
            NewThread nf = new NewThread("Thread " + i, rand.nextInt(valueLimit));
        }
    }
}