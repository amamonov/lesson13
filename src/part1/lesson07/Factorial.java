package part1.lesson07;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Вычисляем факториал с учетом уже произведенных вычислений
 */
public class Factorial {
    private static ConcurrentHashMap<Integer,BigInteger> map = new ConcurrentHashMap<>();       /* ключ - значение*/

    public void count(int value) {
        map.put(value, factorialCalculation(value));
        System.out.printf("%-20d%d\n", value, map.get(value));
    }

    public BigInteger factorialCalculation(int value) {
        int lastKnownValue = 0;
        BigInteger res = BigInteger.valueOf(1);

        for (int i = value; i > 0; i--) {
            if (map.containsKey(i)){
                lastKnownValue = i;
                res = map.get(lastKnownValue);
//                System.out.println("Last known value for the key " + lastKnownValue + " is " + res);   /* работает! */
                break;
            }
        }
        lastKnownValue++;

        for (int i = lastKnownValue; i <= value; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }

        return res;
    }
}