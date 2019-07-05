package part1.lesson04.task02;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Класс ObjectBox, который хранит коллекцию Object.
 *        У класса есть метод addObject, добавляющий объект в коллекцию.
 *        У класса есть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 *        Есть метод dump, выводящий содержимое коллекции в строку.
 */
public class ObjectBox {
    protected static Collection<Object> col = new ArrayList<>();
    public ObjectBox(Collection col){ this.col = col; }

    public boolean addObject(Object obj) {
        return col.add(obj);
    }

    public boolean deleteObject(Object obj) {
        if (col.contains(obj)) {
            return col.remove(obj);
        }

        return false;
    }

    public void dump() {
        System.out.println("Your collection is " + col.toString());
    }

    public void clear() {
        col.clear();
    }
}