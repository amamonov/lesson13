package part1.lesson01;

import java.security.SecureRandom;
import java.util.*;

/**
 * Урок 1, задание 3
 * Дан массив объектов Person. Класс Person характеризуется полями age (возраст, целое число 0-100), sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN), name (имя - строка). Создать два класса, методы которых будут реализовывать сортировку объектов. Предусмотреть единый интерфейс для классов сортировки. Реализовать два различных метода сортировки этого массива по правилам:
 *
 *         первые идут мужчины
 *         выше в списке тот, кто более старший
 *         имена сортируются по алфавиту
 *
 *     Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
 *     Предусмотреть генерацию исходного массива (10000 элементов и более).
 *     Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
 */
public class task03 {
    public static void main(String[] args) {
        /**  @value number of persons array elements */
        int lim = 10000;

        Person p = new Person(1);
        Person[] pers_list;
        pers_list = p.generate_list(lim);

        Sort fs = new FirstSort();
        Sort ss =  new SecondSort();

        elapseTime(fs, pers_list);
        elapseTime(ss, pers_list);
    }

    private static void elapseTime(Sort mysort, Person[] pers_list){
        long startTime = System.currentTimeMillis();
        Sort.print(mysort.sort(pers_list));
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("First sort time: " + elapsedTime + " ms.\n");
    }
}

/**
* Генерация Персон
* @return Массив сгенерированных Персон
*/
class Person {
    int age;                               //0-100;
    String sex;                            //MEN, WOMAN;
    String name;
    String[] male_names = {"Rinat", "Mikhail", "Pavel", "Alexander", "George", "Stepan", "Ilya", "Fedor", "Viktor", "Boris", "Dmitriy"};
    String[] female_names = {"Nadezhda", "Helen", "Anna", "Yulia", "Indira", "Svetlana", "Zinaida", "Maria", "Tatyana", "Olga", "Kate"};
    private static int lim;

    private Random rand = new SecureRandom();

    Person(int lim) {
        this.lim = lim;
    }

    public Person generate_person() {
        Person p = new Person(1);
        p.age = rand.nextInt(100);

        if (rand.nextInt(2) > 0) {
            p.sex = "MEN";
            p.name = male_names[rand.nextInt(male_names.length)];
        }
        else {
            p.sex = "WOMAN";
            p.name = female_names[rand.nextInt(female_names.length)];
        }
        return p;
    }

    /**
    * Генерация массива Персон
    * @param lim Число Персон
    * @return Массив сгенерированных Персон
    */
    public Person[] generate_list(int lim) {
        Person[] ps = new Person[lim];
        for (int i = 0; i < lim; i++) {
            ps[i] = generate_person();
        }
        try {
            for (int i = 0; i < ps.length; i++)
                for (int j = 0; j < ps.length; j++) {
                    if ((ps[i].age == ps[j].age) && (ps[i].name.equals(ps[j].name)))
                        throw new Task03Exception();
                }
        }
        catch (Task03Exception e) {
            System.err.println("Task03Exception");
        }
        return ps;
    }
    public void print(Person p) {
        System.out.printf("%10s%10d%20s\n", p.sex, p.age, p.name);
    }
}

/**
 * Интерфейс для классов сортировки
 */
interface Sort {
    public Person[] sort(Person[] p);
    public static void print(Person[] ps) {
        System.out.printf("%10s%10s%20s\n","Sex", "Age", "Name" );
        for (Person p : ps)
            p.print(p);
    }
    public Person[] sexSort(Person[] arr);
    public Person[] ageSort(Person[] arr);
    public Person[] nameSort(Person[] arr);
}

/**
* Первая сортировка выбором
* @see Sort
*/
class FirstSort implements Sort {
    @Override
    public Person[] sort(Person[] ps) {
        ps = sexSort(ps);
        ps = ageSort(ps);
        ps = nameSort(ps);
        return ps;
    }

    /**
    * Сортировка по полу Персон выбором
    * @param arr Массив Персон
    * @return Массив отсортированных Персон
    */
    @Override
    public Person[] sexSort(Person[] arr) {
        String female = "WOMAN";
        String male = "MEN";
        int female_pos = -1;
        for (int i = 0; i < arr.length; i++) {
            if((arr[i].sex).equals(female)) {
                female_pos = i;
                for (int j = i; j < arr.length; j++) {
                    if ((arr[j].sex).equals(male) && (female_pos != -1)) {
                        Person p = arr[female_pos];
                        arr[female_pos] = arr[j];
                        arr[j] = p;
                        female_pos = -1;
                        break;
                    }
                }
            }
        }
        return arr;
    }

    /**
     * Сортировка по возрасту Персон
     * @param arr Массив Персон
     * @return Массив отсортированных Персон
     */
    @Override
    public Person[] ageSort(Person[] arr) {
        int start = 0;
        int finish = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i].sex) == "MEN") {
                finish++;
            }
        }
        // Males;
        arr = intSort(arr, start, finish);
        // Females;
        arr = intSort(arr, finish, arr.length);

        return arr;
    }

    /**
    * Сортировка по имени Персон ds,jhjv
    * @param arr Массив Персон
    * @return Массив отсортированных Персон
    */
    @Override
    public Person[] nameSort(Person[] arr) {
        int start = 0;
        int finish = 0;
        for (int i = 0; i < arr.length; i++) {
            Person p = arr[i];
            start = i;
            for (int j = i+1; j < arr.length; j++) {
                if ((arr[j].sex == p.sex) && (arr[j].age == p.age)) {
                    finish = j;
                }
                else {
                    break;
                }
            }
            if (start < finish) {
                arr = StringSort(arr, start, finish);
                i = finish;
                finish = 0;
            }
        }
        return arr;
    }

    /**
    * Сортировка по алфавиту выбором
    * @param arr Массив Персон
    * @param start Позиция массива, с которой начнется сортировка
    * @param finish Позиция массива, на которой закончится сортировка
    * @return Массив отсортированных Персон
    */
    public Person[] StringSort(Person[] arr, int start, int finish) {
        final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int swap = 0;
        for (int i = 0; i < letters.length; i++) {
            for (int j = start; j <= finish; j++) {
                if (arr[j].name.startsWith(letters[i])) {
                    Person p = arr[j];
                    for (int k = start; k < j; k++) {
                        for (int l = i + 1; l < letters.length; l++) {
                            if (arr[k].name.startsWith(letters[l])) {
                                arr[j] = arr[k];
                                arr[k] = p;
                                swap = 1;
                                break;
                            }
                        }
                        if (swap == 1) {
                            swap = 0;
                            break;
                        }
                    }
                }
            }
        }
        return arr;
    }

    /**
    * Сортировка целочисленных значений выбором
    * @param arr Массив персон
    * @param start Позиция массива, с которой начнется сортировка
    * @param finish Позиция массива, на которой закончится сортировка
    * @return Массив отсортированных персон в указанном диапазоне
    */
    public Person[] intSort(Person[] arr, int start, int finish){
        for (int i = start; i < finish; i++) {
            int min = arr[i].age;
            int min_i = i;
            for (int j = i+1; j < finish; j++) {
                if (arr[j].age < min) {
                    min = arr[j].age;
                    min_i = j;
                }
            }
            if (i != min_i) {
                Person p = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = p;
            }
        }
        return arr;
    }
}

/**
 * Вторая сортировка пузырьком
 * @see Sort
 */
class SecondSort implements Sort {
    @Override
    public Person[] sort(Person[] ps) {
        ps = sexSort(ps);
        ps = ageSort(ps);
        ps = nameSort(ps);
        return ps;
    }

    /**
    * Сортировка по полу Персон пузырьком
    * @param arr Массив Персон
    * @return Массив отсортированных Персон
    */
    @Override
    public Person[] sexSort(Person[] arr) {
        String female = "WOMAN";
        String male = "MEN";
        for (int i = arr.length; i > 0; i--) {
            for (int j = 0; j < (i-1); j++) {
                if ((arr[j].sex).equals(female) && ((arr[j+1].sex).equals(male))) {
                    Person p = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = p;
                }
            }
        }
        return arr;
    }

    @Override
    public Person[] ageSort(Person[] arr) {
        return new FirstSort().ageSort(arr);
    }

    @Override
    public Person[] nameSort(Person[] arr) {
        return new FirstSort().nameSort(arr);
    }

    /**
    * Сортировка по алфавиту пузырьком
    * @param arr Массив Персон
    * @param start Позиция массива, с которой начнется сортировка
    * @param finish Позиция массива, на которой закончится сортировка
    * @return Массив отсортированных Персон
    */
    public Person[] StringSort(Person[] arr, int start, int finish) {
        final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        int swap = 0;
        for (int i = 0; i < letters.length; i++) {
            for (int j = finish; j >= start; j--) {
                for (int k = start; k < (j - 1); k++) {
                    for (int l = i + 1; l < letters.length; l++) {
                        if (arr[j].name.startsWith(letters[i]) && (i < l)) {
                        {
                                Person p = arr[j];
                                arr[j] = arr[k];
                                arr[k] = p;
                                swap = 1;
                                break;
                            }
                        }
                        if (swap == 1) {
                            swap = 0;
                            break;
                        }
                    }
                }
            }
        }
        return arr;
    }

    /**
    * Сортировка целочисленных значений пузырьком
    * @param arr Массив персон
    * @param start Позиция массива, с которой начнется сортировка
    * @param finish Позиция массива, на которой закончится сортировка
    * @return Массив отсортированных персон в указанном диапазоне
    */
    public Person[] intSort(Person[] arr, int start, int finish) {
        for (int i = finish - 1; i > start; i--) {
            for (int j = start; j < i; j++) {
                if (arr[j].age > arr[j + 1].age) {
                    Person tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr;
    }
}

class Task03Exception extends Exception {}