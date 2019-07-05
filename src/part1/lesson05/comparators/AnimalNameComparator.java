package part1.lesson05.comparators;

import part1.lesson05.Animal;
import java.util.Comparator;

/**
 * Сравнение животных по именам
 */
public class AnimalNameComparator implements Comparator<Animal> {
    public int compare(Animal a, Animal b){
        return a.name.compareTo(b.name);
    }
}
