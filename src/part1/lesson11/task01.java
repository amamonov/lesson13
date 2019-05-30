package part1.lesson11;

import java.security.SecureRandom;

/**
 * Перевести одну из предыдущих работ на использование стримов и лямбда-выражений там, где это уместно
 * (возможно, жертвуя производительностью).
 *
 * @author Alexander.Mamonov@protonmaul.ch
 * @version 1.0
 */
public class task01 {
    public static void main(String[] args) {
        final int arraySize = 10;
        final int valueLimit = 10000;

        IntFunc f = ((x, y) -> {
            SecureRandom rand = new SecureRandom();

            for (int i = 0; i < x; i++) {
                new NewThread("Thread " + i, rand.nextInt(y));
            }
        });
        f.method(arraySize, valueLimit);

//        factorial(arraySize, valueLimit);
    }

//    /**
//     * Автоматически генерируем потоки и в каждоом вычислям факториал
//     * @param arraySize - максимальный размер входного массива
//     * @param valueLimit - максимальное значение элемента массива
//     */
//    public static void factorial(int arraySize, int valueLimit) {
//        SecureRandom rand = new SecureRandom();
//        System.out.printf("%-20s%-15s\n", "Default array", "Factorial");
//        for (int i = 0; i < arraySize; i++) {
//            NewThread nf = new NewThread("Thread " + i, rand.nextInt(valueLimit));
//        }
//    }
}
