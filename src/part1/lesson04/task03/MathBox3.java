package part1.lesson04.task03;

import part1.lesson04.task01.MathBox;
import part1.lesson04.task02.ObjectBox;

import java.util.*;

/**
 * Создаем новый класс вместо переписывания старого
 * @see MathBox
 */
class MathBox3 extends ObjectBox {
    private static Collection<? extends Number> arr = new HashSet<>();
    abstract class T extends Number{};

    public MathBox3(Object[] number) {
        super(arr);
        Collection col = new HashSet();
        col.addAll(Arrays.asList(number));
        arr.addAll(col);
    }

    public Collection<? extends Number> getArr() {
        return arr;
    }

    public static Double summator(Collection<? extends Number> arr) {
        return arr
                .stream()
                .map(Number::doubleValue)
                .reduce((s1, s2) -> s1 + s2)
                .orElse(0D);
    }

    public static Collection<? extends Number> splitter(Collection<? extends Number> arr, double divisor) {
        Collection<Number> temp = new ArrayList<>();
        Iterator iterator = arr.iterator();

        while (iterator.hasNext()){
            Number num = (Number) iterator.next();
            num = num.doubleValue()/divisor;
            temp.add(num);
        }

        return temp;
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