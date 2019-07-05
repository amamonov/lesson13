package part1.lesson05;

/**
 * Животное с именем, хозяином и весом
 * */
public class Animal {
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
