package part1.lesson06;

import java.util.*;
import java.io.*;

/**
 * Написать программу, читающую текстовый файл. Программа должна составлять отсортированный по алфавиту
 * список слов, найденных в файле и сохранять его в файл-результат. Найденные слова не должны повторяться,
 * регистр не должен учитываться. Одно слово в разных падежах – это разные слова.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
 */
public class task01 {
    public static void main(String[] args) {
        String fileInput = "src/part1/lesson06/fileInput.txt";
        String fileOutput = "src/part1/lesson06/fileOutput.txt";
        fileWrite(fileOutput, sortAlphabet(formArray(fileRead(fileInput))));
    }

    protected static String fileRead(String file) {
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            int i = -1;
            while ((i = fis.read()) != -1) {
                str += (char)i;
            }
            fis.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    protected static String[] formArray(String str) {
        String[] arr;
        arr = str.split(" ");
        char[] chr;
        for (int i = 0; i < arr.length; i++) {
            chr = arr[i].toCharArray();
            arr[i] = "";
            for (int j = 0; j < chr.length; j++) {
                if ((chr[j] != ',')
                    && (chr[j] != ';')
                    && (chr[j] != '.')
                    && (chr[j] != ':')
                    && (chr[j] != '?')
                    && (chr[j] != '!')
                    && (chr[j] != '-')
                    && (chr[j] != ' ')
                    )
                        arr[i]+=chr[j];
            }
        }

        return arr;
    }

    protected static String[] sortAlphabet(String[] arr) {
        Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);
//        Arrays.sort(arr, new Comparator<String>() {
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });
        return arr;
    }

    protected static void fileWrite(String file, String[] arr){
        String exist = " ";
        byte[] buffer;
        byte[] endOfLine = "\n".getBytes();
        try {
            FileOutputStream fos = new FileOutputStream(file);

            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(exist)
                    || (arr[i].equals(""))
                    ) {
                      continue;
                }
                else {
                    System.out.println(arr[i]);
                    buffer = arr[i].getBytes();
                    fos.write(buffer);
                    fos.write(endOfLine);
                    exist = arr[i];
                }
            }
            fos.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}