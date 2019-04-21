package part1.lesson01;

/**
 * Урок 1, задание 1
 * Написать программу ”Hello, World!”. В ходе выполнения программы она должна выбросить исключение и завершиться с ошибкой.
 *
 *         Смоделировав ошибку «NullPointerException»
 *         Смоделировав ошибку «ArrayIndexOutOfBoundsException»
 *         Вызвав свой вариант ошибки через оператор throw
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
*/
public class task01 {
    public static void main(String[] args) {
        String[] input = new String[2];

        try {
            method(input);
        }
        catch (NullPointerException e) {
            System.err.println("NullPointerException");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("ArrayIndexOutOfBoundsException");
        }
        catch (Task01Exception e) {
            System.err.println("Task01Exception");
        }
        catch (Exception e) {
            System.err.println("Exception");
        }
    }

    /**
     * Генерация исключения
     * @param input Пользовательский строковый массив
     * @throws NullPointerException
     * @throws ArrayIndexOutOfBoundsException
     * @throws Task01Exception
     */
    static void method(String[] input) throws NullPointerException, ArrayIndexOutOfBoundsException, Task01Exception {
        /** @value Выбор исключения: 0 = NullPointerException, 1 = ArrayIndexOutOfBoundsException, 2 = Task01Exception; */
        int exception = 1;

        exception_select(exception, input);

        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("Task01")) {
                throw new Task01Exception();
            }
            System.out.println(input[i]);
        }
    }

    /**
     * Метод выбора исключения
     * @param i 0 = NullPointerException, 1 = ArrayIndexOutOfBoundsException, 2 = Task01Exception
     * @param input Пользовательский строковый массив
     */
    static void exception_select(int i, String[] input){
        switch(i){
            //NullPointerException;
            case 0:
                for (String s : input = null)
                break;
            //ArrayIndexOutOfBoundsException;
            case 1:
                input[3] = "";
                break;
            //Task01Exception;
            case 2:
                input[0] = "Task01";
                break;
            default:
                input[0] = "Hello";
                input[1] = "World ";
                input[2] = "!";
        }
    }
}

class Task01Exception extends Exception {}