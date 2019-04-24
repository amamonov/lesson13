package part1.lesson04;

import java.util.*;

/**
 * Доработать классы MathBox и ObjectBox таким образом, чтобы MathBox был наследником ObjectBox. Необходимо сделать
 * такую связь, правильно распределить поля и методы. Функциональность в целом должна сохраниться. При попытке положить Object в
 * MathBox должно создаваться исключение.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
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

    /**
     * Заполняем массив объектами Object типа char
     * @param arr исходный массив
     * @return заполненный массив
     */
    private static Object[] fill(Object[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //arr[i] = (Object)i;                                                       /* А так почти все работает */
            arr[i] = letter_arr[i];
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
        System.out.println(mtb.getSummator(mtb.getArr()));
        System.out.println("Hash code is " + mtb.hashCode());

        Collection col = new HashSet();
        System.out.println("Does some other collection equals to yours? " + (mtb.getArr()).equals(col));

        Number element = 3;
        mtb.findAndRemove(element);
        System.out.println(mtb.getArr());
    }
}

/**
 * Создаем новый класс вместо переписывания старого
 * @see MathBox
 */
class MathBox3 extends ObjectBox {
    private static Collection<? extends Number> arr = new HashSet<>();
    static Collection col = new HashSet();                                               /* Временная коллекция */

    public MathBox3(Object[] number) {
        super(arr);
        col.addAll(Arrays.asList(number));
        arr.addAll(col);
    }

    public Collection<? extends Number> getArr() {
        return arr;
    }

    private static Double summator(Collection<? extends Number> arr) {
        return arr
                .stream()
                .map(Number::doubleValue)
                .reduce((s1, s2) -> s1 + s2)
                .orElse(0D);
    }

    public static Double getSummator(Collection<? extends Number> arr) {
        return summator(arr);
    }

    private static Collection<? extends Number> splitter(Collection<? extends Number> arr, Number divisor) {
        List<Number> ls = new ArrayList<>();
        ls.addAll(arr);
//        arr.clear();                                                          /* Сохраним исходный массив */
        for (int i = 0; i < ls.size(); i++) {
            Number num = ls.get(i);
//            arr.add(num);                                                     /* Инвариантность не позволяет */
        }
        return arr;
    }

    public static Collection<? extends Number> getSplitter(Collection<? extends Number> arr, Number divisor) {
        return splitter(arr, divisor);
    }

    @Override
    public String toString() {
        Collection<String> col = new ArrayList<>();
        return arr.toString();
    }

    @Override
    public int hashCode() {
        return arr.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == arr)
            return true;

        return false;
    }

    public boolean findAndRemove(Number element) {
        if (arr.contains(element)) {
            arr.remove(element);
            return true;
        }

        return false;
    }

    public boolean addObject(Object obj) {
        return super.addObject(obj);
    }

    public boolean deleteObject(Object obj) {
        if (col.contains(obj)) {
            return super.deleteObject(obj);
        }

        return false;
    }

    /**
     * Выводим на экран нашу коллекцию arr
     * @see ObjectBox
     */
    public void dump() {
        super.dump();
    }

    public void clear() {
        super.clear();
    }
}