package part1.lesson05.comparators;

import part1.lesson05.Animal;
import java.util.Comparator;

/**
 * Сравнение животных по именам их хозяев
 */
public class MasterNameComparator implements Comparator<Animal> {
    public int compare(Animal a, Animal b){
        return a.master.name.compareTo(b.master.name);
    }
}
