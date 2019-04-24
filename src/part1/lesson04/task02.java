package part1.lesson04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Создать класс ObjectBox, который будет хранить коллекцию Object.
 *
 *         У класса должен быть метод addObject, добавляющий объект в коллекцию.
 *         У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 *         Должен быть метод dump, выводящий содержимое коллекции в строку.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
 */
public class task02 {
    public static void main(String[] args) {
        Collection<Integer> icol = new ArrayList<>();           /* Testing different type of arrays */
        ObjectBox ob = new ObjectBox(icol);
        test_commands(ob, "Integer");

        Collection<Short> shcol = new ArrayList<>();
        ob = new ObjectBox(shcol);
        test_commands(ob, "Short");

        Collection<String> scol = new ArrayList<>();
        ob = new ObjectBox(scol);
        test_commands(ob, "String");

        Collection<Double> dcol = new ArrayList<>();
        ob = new ObjectBox(dcol);
        test_commands(ob, "Double");

        Collection<Float> fcol = new ArrayList<>();
        ob = new ObjectBox(fcol);
        test_commands(ob, "Float");

        Collection<Character> ccol = new ArrayList<>();
        ob = new ObjectBox(ccol);
        test_commands(ob, "Character");
    }

    /**
     * Тестирует работоспособность класса ObjectBox с коллекциями различных типов
     * @param ob ObjectBox
     * @param type тип коллекции (Integer, String и т.д.)
     */
    private static void test_commands(ObjectBox ob, String type) {
        System.out.println("\nTest of " + type + " collection:");
        for (int i = 0; i < 10; i++) {
            ob.addObject(i);
        }
        ob.dump();

        for (int i = 0; i < 5; i++) {
            ob.deleteObject(i);
        }
        ob.dump();
        ob.clear();
    }
}

class ObjectBox {
    protected static Collection col = new ArrayList();

    public ObjectBox(Collection col){ this.col = col; }

    public boolean addObject(Object obj) { return col.add(obj); }

    public boolean deleteObject(Object obj) {
        if (col.contains(obj)) {
            return col.remove(obj);
        }

        return false;
    }

    public void dump() { System.out.println("Your collection is " + col.toString()); }

    public void clear() { col.clear(); }
}