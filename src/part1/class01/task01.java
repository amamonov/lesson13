package part1.class01;

/**
 * Lesson 2, task 1
 * Hello World program with different exceptions
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
*/
public class task01 {
    /**
     * Main
     * @param args Input String array
     */
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
     * Generating exception
     * @param input Users String array
     * @throws NullPointerException
     * @throws ArrayIndexOutOfBoundsException
     * @throws Task01Exception
     */
    static void method(String[] input) throws NullPointerException, ArrayIndexOutOfBoundsException, Task01Exception {
        //Selecting your exception: 0 = NullPointerException, 1 = ArrayIndexOutOfBoundsException, 2 = Task01Exception;
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
     * Selecting exception type
     * @param i 0 = NullPointerException, 1 = ArrayIndexOutOfBoundsException, 2 = Task01Exception
     * @param input Users String array
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

/**
 * Users exception
 */
class Task01Exception extends Exception {}