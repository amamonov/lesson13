package part1.lesson05.comparators;

import part1.lesson05.Animal;
import java.util.Comparator;

/**
 * Сравниение животных по весу
 */
public class AnimalWeightComparator implements Comparator<Animal> {
    public int compare(Animal a, Animal b){
        if(a.weight > b.weight)
            return 1;
        else if(a.weight < b.weight)
            return -1;
        else
            return 0;
    }
}
