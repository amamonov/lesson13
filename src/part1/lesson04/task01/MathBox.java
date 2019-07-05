package part1.lesson04.task01;

import java.util.*;

/**
 * Класс MathBox реализует следующий функционал:
 *      Конструктор на вход получает массив Number. Элементы не могут повторяться. Элементы массива внутри объекта
 *      раскладываются в подходящую коллекцию.
 *
 *      Существует метод summator, возвращающий сумму всех элементов коллекции.
 *      Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся
 *      аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
 *
 *      Переопредены методы toString, hashCode, equals, чтобы можно было использовать MathBox для вывода
 *      данных на экран и хранение объектов этого класса в коллекциях (например, hashMap).
 *
 *      Существует метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 */
public class MathBox {
    private Set<Double> arr = new HashSet<>();

    MathBox(Number[] number) {
        for (int i = 0; i < number.length; i++) {
            arr.add((double)number[i]);
        }
    }

    public Set<Double> getArr(){
        return arr;
    }

    public static Double summator(Set<Double> arr) {
        double sum = 0;
        Iterator iterator = arr.iterator();
        while (iterator.hasNext()) {
            sum += (double)iterator.next();
        }

        return sum;
    }

    public static Set<Double> splitter(Set<Double> arr, double divisor) {
        List ls = new ArrayList<>();
        Iterator iterator = arr.iterator();

        while(iterator.hasNext()) {
            ls.add((double)iterator.next()/divisor);
        }
        arr.addAll(ls);

        return arr;
    }

    @Override
    public String toString(){
        return arr.toString();
    }

    @Override
    public int hashCode(){
        return arr.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (obj == arr)
            return true;

        return false;
    }

    boolean findAndRemove(Double element) {
        if (arr.contains(element)) {
            arr.remove(element);
            return true;
        }

        return false;
    }
}