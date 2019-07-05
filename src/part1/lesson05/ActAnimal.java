package part1.lesson05;

import java.util.*;
import part1.lesson05.comparators.*;

/**
 * Взаимодействие с животным
 */
class ActAnimal {
    public static Map<String, Animal> map = new HashMap<>();                               /* карта животных по имени */

    /**
     * Добавление животного в Карту с проверкой на наличие дубликата
     * @param animal животное
     * @return обновленная Карта
     */
    public Map<String, Animal> addAnimal(Animal animal) {
        try {
            Animal existsAnimal = map.get(animal.name);
            if ((existsAnimal != null)
                    && (existsAnimal.name == animal.name)
//                && (existsAnimal.master == animal.master)                                /* чрезмерно */
//                && (existsAnimal.weight == animal.weight)
            ) {
                throw new AnimalException();
            }
            else {
                map.put(animal.name, animal);
            }
        } catch (AnimalException e) {
            System.err.println(e.getLocalizedMessage() + " exception. Animal " + animal.name +
                    " with id " + animal.id + ", weight = " + animal.weight +
                    " with master " + animal.master.name.toString() + " is aready in the Map");
        }

        return map;
    }

    public Animal findAnimal(String name) {
        return map.get(name);
    }

    /**
     * Изменение Животного по его идентификатору id
     * @param id индентификатор
     * @param name новое имя
     * @param master новый Хозяин
     * @param weight новый вес
     * @return обновленное Животное
     */
    public Animal changeAnimal(int id, String name, AnimalMaster master, int weight) {
        Animal newAnimal = new Animal().generateAnimal(name, master, weight);

        Map<Integer, Animal> animals = new HashMap<>(map.size());
        for (Animal animal : map.values()) {
            animals.put(animal.id, animal);
        }

        if (animals.get(id) != null) {
            map.remove(animals.get(id).name);
        }
        map.put(name, newAnimal);

        return map.get(name);
    }

    public Animal printAnimal(Animal animal){
        System.out.println("Your animal is " + map.get(animal.name).name.toString() +
                "; weight = " + map.get(animal.name).weight +
                "; master is " + map.get(animal.name).master.name +
                ", who is " + map.get(animal.name).master.gender +
                " " + map.get(animal.name).master.age + " years old.");
        return map.get(animal.name);
    }

    /**
     * Сортируем и печатаем всю карту.
     * Поля для сортировки –  хозяин, кличка животного, вес.
     */
    public void sortAndPrint() {
        List<Animal> animals = new ArrayList<>(map.size());
        animals.addAll(map.values());

        Comparator<Animal> comp = new MasterNameComparator().thenComparing(new AnimalNameComparator()
                .thenComparing(new AnimalWeightComparator()));
        Collections.sort(animals, comp);

        System.out.printf("\n%15s%15s%10s\n","Master", "Name", "Weight");
        for (Animal animal : animals) {
            System.out.printf("%15s%15s%10d\n", animal.master.name, animal.name, animal.weight);
        }
    }
}
