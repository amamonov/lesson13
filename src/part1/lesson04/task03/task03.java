package part1.lesson04.task03;

import part1.lesson04.task02.*;
import java.util.*;

/**
 * Доработать классы MathBox и ObjectBox таким образом, чтобы MathBox был наследником ObjectBox. Необходимо сделать
 * такую связь, правильно распределить поля и методы. Функциональность в целом должна сохраниться. При попытке положить
 * Object в MathBox должно создаваться исключение.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 2.0
 */
public class task03 {
    public static void main(String[] args) {
        int length = 10;
        Object[] arr = new Object[length];                                               /* Исходный массив объектов */
        fill(arr);

        try {
            task(arr);
        }
        catch (ClassCastException e) {
            System.err.println("Exception " + e.getLocalizedMessage() + " found.");
        }
    }

    static char[] letter_arr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'k', 'l', 'm'};

    private static Object[] fill(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (Number)i;
//            arr[i] = letter_arr[i];                                                    /* Проверка исключения */
        }

        return arr;
    }

    /**
     * Тестируем работоспособность объекта класса согласно заданию 03
     * @param arr массив объектов
     */
    private static void task(Object[] arr) {
        ObjectBox obj = new MathBox3(arr);
        obj.dump();
        obj.addObject(100);
        obj.dump();
        obj.deleteObject(1);
        obj.dump();
        obj.clear();
        obj.dump();

        MathBox3 mtb = new MathBox3(arr);

        System.out.println(mtb.getArr());
        System.out.println("Your summ: " + mtb.summator(mtb.getArr()));
        System.out.println("Splitting: " + mtb.splitter(mtb.getArr(), 2.0D));
        System.out.println("Hash code: " + mtb.hashCode());

        Collection col = new HashSet();
        System.out.println("Does some other collection equals to yours? " + (mtb.getArr()).equals(col));

        Number element = 3;
        mtb.findAndRemove(element);
        System.out.println("After removing " + element + " element: ");
        System.out.println(mtb.getArr());
    }
}