package part1.lesson05;

/**
 * Хозяин животного с именем, возрастом и полом.
 */
public class AnimalMaster {
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
                throw new AnimalException();
            }
        }
        catch (AnimalException e) {
            System.err.println(e.getLocalizedMessage() + " exception. Masters parameters: name " +
                    amaster.name + "; age = " + amaster.age);
        }

        return amaster;
    }
}
