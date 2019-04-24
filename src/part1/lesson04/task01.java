package part1.lesson04;

import sun.security.krb5.internal.crypto.Aes128;

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
 * @version 1.0
 */
public class task01 {
    public static void main(String[] args) {
        int length = 10;
        Float[] arr = new Float[length];
        fill(arr);

        MathBox mth = new MathBox(arr);

        System.out.println("Your collection is " + mth.toString());             /* toString работает*/

        System.out.println("Your summed collection is "
                            + mth.getSummator(mth.getArr()));                   /* Summator работает*/

        float divisor = 3.0f;
        System.out.println("Your splitted collection with divisor=" + divisor + " is " +
                mth.getSplitter(mth.getArr(), divisor));                        /* Splitter работает*/

        System.out.println("Hash code is " + mth.hashCode());                   /* hashCode работает*/

        Collection col = new HashSet();
        System.out.println("Does some other collection equals to yours? " +
                            (mth.getArr()).equals(col));                        /* equals работает*/

        float element = 3.0f;
        findAndRemoveAndPrint(mth, element);                                    /* Поиск и удаление элемента работает*/
    }

    /**
     * Заполняем массив
     * @param arr массив
     * @return заполненный массив
     */
    private static Float[] fill(Float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)i;
        }

        return arr;
    }

    /**
     * Проверяем, содержится ли элемент в коллекции и удаляем его
     * @param mth класс с коллекцией
     * @param element искомый и удаляемый элемент
     */
    private static void findAndRemoveAndPrint(MathBox mth, float element){
        String check = "not ";

        if (mth.findAndRemove(element))
            check = "";

        System.out.println("Your element " + element + " was " + check + "found and removed;"  );
        System.out.println("Your updated collection is " + mth.getArr());
    }
}

class MathBox {
    private Set<Float> arr = new HashSet<>();                                   /* Выбрали HashSet в виде коллекции*/

    /**
     * Конструктор класса
     * @param number массив элементов
     */
    MathBox(Number[] number) {
        Collection col = new HashSet<>();
        col.addAll(Arrays.asList(number));
        arr.addAll(col);
    }

    public Set<Float> getArr(){
        return arr;
    }

    private static Float summator(Set<Float> arr) {
        float sum = 0;
        List<Float> ls = new ArrayList<>();
        ls.addAll(arr);
        for (int i = 0; i < ls.size(); i++) {
            sum+= (float)ls.get(i);
        }
        return sum;
    }

    public static Float getSummator(Set<Float> arr){
        return summator(arr);
    }

    private static Set<Float> splitter(Set<Float> arr, float divisor) {
        List ls = new ArrayList<>();
        ls.addAll(arr);
        arr.clear();
        float f = 0;
        for (int i = 0; i < ls.size(); i++) {
            f = (Float)ls.get(i) / divisor;
            arr.add(f);
        }
        return arr;
    }

    public static Set<Float> getSplitter(Set<Float> arr, float divisor){
        return splitter(arr, divisor);
    }

    @Override
    public String toString(){
        Collection<String> col = new ArrayList<>();
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

    boolean findAndRemove(Number element) {
        if (arr.contains(element)) {
            arr.remove(element);
            return true;
        }

        return false;
    }
}