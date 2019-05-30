package part1.lesson11;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.*;

/**
 * Вычисляем факториал с учетом уже произведенных вычислений. Используем лямбда-выражения ценой производительности.
 */
public class FactorialCounting {
    private ConcurrentHashMap<Integer,BigInteger> map = new ConcurrentHashMap<>();       /* ключ - значение*/

    static BigInteger method(BigIntFunc bif, int value) {                                       /* лямбда-аргумент */
        return bif.method(value);
    }

    public void count(int value) {
        BigInteger calc = method((someValue) -> {
            BigInteger res = BigInteger.valueOf(1);
            for (int i = 1; i < value; i++) {
                res = res.multiply(BigInteger.valueOf(i));
            }
            return res;
        }, value);

        map.put(value, calc);
//        System.out.printf("%-20d%d\n", value, map.get(value));

    }

    public void printAll() {
        Stream.of(map).forEach(System.out::println);
    }

//    public void count(int value) {
//        map.put(value, factorialCalculation(value));
//        System.out.printf("%-20d%d\n", value, map.get(value));
//    }
//
//    public BigInteger factorialCalculation(int value) {
//        int lastKnownValue = 0;
//        BigInteger res = BigInteger.valueOf(1);
//
//        for (int i = value; i > 0; i--) {
//            if (map.containsKey(i)){
//                lastKnownValue = i;
//                res = map.get(lastKnownValue);
////                System.out.println("Last known value for the key " + lastKnownValue + " is " + res);   /* работает! */
//                break;
//            }
//        }
//        lastKnownValue++;
//
//        for (int i = lastKnownValue; i <= value; i++) {
//            res = res.multiply(BigInteger.valueOf(i));
//        }
//
//        return res;
//    }
}

