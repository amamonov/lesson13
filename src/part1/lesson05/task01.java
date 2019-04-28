package part1.lesson05;

import java.util.*;

/**
 * Разработать программу – картотеку домашних животных. У каждого животного есть уникальный идентификационный номер,
 * кличка, хозяин (объект класс Person с полями – имя, возраст, пол), вес.
 *
 * Реализовать:
 *
 *     метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной
 *     ситуации)
 *     поиск животного по его кличке (поиск должен быть эффективным)
 *     изменение данных животного по его идентификатору
 *     вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 *
 * @author Alexander.Mamonov#protonmail.ch
 * @version 1.0
 */
public class task01 {
    public static void main(String[] args) {
        AnimalMaster master = new AnimalMaster();
        Animal animal = new Animal();
        AnimalsList animalList = new AnimalsList();

        master = master.generateMaster("Alexander", 36, "male");

        animalList.addAnimal(animal.generateAnimal("Druzhok", master, 14));
        animalList.addAnimal(animal.generateAnimal("Dinka", master, 12));
        animalList.addAnimal(animal.generateAnimal("Dinka", master, 12));     /* Для проверки исключения*/
        animalList.addAnimal(animal.generateAnimal("Bobik", master, 10));
        animalList.addAnimal(animal.generateAnimal("Mus'ka", master, 6));
        animalList.addAnimal(animal.generateAnimal("Homka", master, 0));
        animalList.addAnimal(animal.generateAnimal("Kesha", master, 1));

        master = master.generateMaster("Zinaida", 31, "female");

        animalList.addAnimal(animal.generateAnimal("Cherniy", master, 14));
        animalList.addAnimal(animal.generateAnimal("Tasha", master, 6));
        animalList.addAnimal(animal.generateAnimal("Yurok", master, 0));

        System.out.println("\nLooking for the animal...");
        animalList.printAnimal(animalList.findAnimal("Mus'ka"));
        System.out.println("\nChanging for the animal...");                                 /* Перезаписываем id = 1 */
        animalList.printAnimal(animalList.changeAnimal(1, "Pleshivik", master, 6));

        animalList.sortAndPrint();
    }
}

class Animal {
    private static int globalId = 0;
    public int id = 0;
    public String name = "";
    public AnimalMaster master = new AnimalMaster();
    public int weight = 0;

    /**
     * Генерация Животного
     * @param name кличка Животного
     * @param master хозяин Животного
     * @param weight вес Животного
     * @return сгенерированное Животное
     */
    public Animal generateAnimal(String name, AnimalMaster master, int weight){
        Animal animal = new Animal();
        animal.id = ++globalId;
        animal.name = name;
        animal.master = master;
        animal.weight = weight;
        try {
            if ((animal.id == 0))
                throw new AnimalException();
        }
        catch (AnimalException e) {
            System.err.println(e.getLocalizedMessage() +" exception. Animals parameters: name = " + animal.name +
                               "; weight = " + animal.weight + "; master = " + animal.master.toString());
        }

        return animal;
    }
}

class AnimalMaster {
    public String name = "";
    public int age = 0;
    public String gender = "";

    /**
     * Генерация Хозяина
     * @param name имя Хозяина
     * @param age возраст Хозяина
     * @param gender пол Хозяина - нигде не проверяем, проявляем толерантность
     * @return сгенерированный Хозяин
     */
    public AnimalMaster generateMaster(String name, int age, String gender){
        AnimalMaster amaster = new AnimalMaster();
        amaster.name = name;
        amaster.age = age;
        amaster.gender = gender;
        try {
            if (amaster.name.equals("") || (amaster.age < 0)) {
                throw new AnimalMasterException();
            }
        }
        catch (AnimalMasterException e) {
            System.err.println(e.getLocalizedMessage() + " exception. Masters parameters: name " +
                               amaster.name + "; age = " + amaster.age);
        }

        return amaster;
    }
}

class AnimalsList {
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

    class MasterNameComparator implements Comparator<Animal>{
        public int compare(Animal a, Animal b){
            return a.master.name.compareTo(b.master.name);
        }
    }

    class AnimalNameComparator implements Comparator<Animal>{
        public int compare(Animal a, Animal b){
            return a.name.compareTo(b.name);
        }
    }

    class AnimalWeightComparator implements Comparator<Animal>{
        public int compare(Animal a, Animal b){
            if(a.weight > b.weight)
                return 1;
            else if(a.weight < b.weight)
                return -1;
            else
                return 0;
        }
    }
}

class AnimalException extends Exception{}
class AnimalMasterException extends Exception{}