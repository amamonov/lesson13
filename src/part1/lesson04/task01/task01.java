package part1.lesson04.task01;

import java.util.*;

/**
 * Написать класс MathBox, реализующий следующий функционал:
 *         Конструктор на вход получает массив Number. Элементы не могут повторяться. Элементы массива внутри объекта раскладываются в
 *         подходящую коллекцию (выбрать самостоятельно).
 *
 *         Существует метод summator, возвращающий сумму всех элементов коллекции.
 *
 *         Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся
 *         аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
 *
 *         Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox для вывода
 *         данных на экран и хранение объектов этого класса в коллекциях (например, hashMap). Выполнение контракта обязательно!
 *
 *         Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 *
 * @author Alexander.Mamonov@protonmail.ch;
 * @version 2.0
 */
public class task01 {
    public static void main(String[] args) {
        int length = 10;
        Double[] arr = new Double[length];
        fill(arr);
        MathBox mth = new MathBox(arr);

        System.out.println("Your collection is " + mth.toString());                         /* toString работает*/
        System.out.println("Your summed collection is " + mth.summator(mth.getArr()));   /* Summator работает*/

        double divisor = 3.0;
        System.out.println("Your splitted collection with divisor=" + divisor + " is " +
                mth.splitter(mth.getArr(), divisor));                                    /* Splitter работает*/

        System.out.println("Hash code is " + mth.hashCode());                               /* hashCode работает*/

        Collection col = new HashSet();
        System.out.println("Does some other collection equals to yours? " +
                            (mth.getArr()).equals(col));                                    /* equals работает*/

        float element = 3.0f;
        findAndRemoveAndPrint(mth, element);                              /* Поиск и удаление элемента работает*/
    }

    /**
     * Заполняем массив
     * @param arr массив
     * @return заполненный массив
     */
    private static Double[] fill(Double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (double)i;
        }

        return arr;
    }

    /**
     * Проверяем, содержится ли элемент в коллекции и удаляем его
     * @param mth класс с коллекцией
     * @param element искомый и удаляемый элемент
     */
    private static void findAndRemoveAndPrint(MathBox mth, double element){
        String check = "not ";

        if (mth.findAndRemove(element))
            check = "";

        System.out.println("Your element " + element + " was " + check + "found and removed;"  );
        System.out.println("Your updated collection is " + mth.getArr());
    }
}