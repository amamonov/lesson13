package part1.lesson09;

import org.apache.commons.jci.ReloadingClassLoader;


import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Дан интерфейс
 *
 * public interface Worker {
 *     void doWork();
 * }
 *
 * Необходимо написать программу, выполняющую следующее:
 *
 *     Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 *     После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле
 * SomeClass.java.
 *     Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 *     Полученный файл подгружается в программу с помощью кастомного загрузчика
 *     Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 */
public class task01 {
    public static void main(String[] args) {
        String readPath = "F:\\JavaProjects\\Innopolis\\src\\part1\\lesson09\\Worker.java";
        String writePath = readPath;

        compileFile(writeFile(writePath, changeFile(readFile(readPath))));
    }

    private static List readFile(String readPath) {
        File file = new File(readPath);
        List arrayOfLines = new ArrayList();

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                arrayOfLines.add(scanner.nextLine());
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Read array: " + arrayOfLines);
        return arrayOfLines;
    }

    private static List changeFile(List arrayOfLines) {
        List inputArrayOfLines = readConsole();
        List finalArrayOfLines = new ArrayList();
        String line = "";
        Iterator iter = arrayOfLines.iterator();

        for (int i = 0; i < arrayOfLines.size(); i++) {
            line = iter.next().toString() ;
//            System.out.println("Line = " + line);

            if (line.endsWith("void doWork();")) {
                finalArrayOfLines.add(line);
                finalArrayOfLines.addAll(inputArrayOfLines);
            }
            else {
                finalArrayOfLines.add(line);
            }
        }

        System.out.println("Changed array: " + finalArrayOfLines);
        return finalArrayOfLines;
    }

    private static String writeFile(String writePath, List arrayOfLines) {
        try {
            FileOutputStream fos = new FileOutputStream(writePath);
            Iterator iter = arrayOfLines.iterator();
            String line = "";

            for (int i = 0; i < arrayOfLines.size(); i++) {
                line = iter.next().toString() + "\n";
                try {
                    fos.write(line.getBytes());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Wroted line: " + line);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return writePath;
    }

    private static void compileFile(String path) {
//        path = "F:\\JavaProjects\\Innopolis\\out\\production\\Innopolis\\part1\\lesson09\\Worker.class";

        String lib = path.substring(0, 44);
        String file = path.substring(45, 56);
        System.out.println("path = " + lib + "; file = " + file);

        Path rootPath = Paths.get(lib);
        Path javaFilePath = rootPath.resolve(file);

        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        jc.run(null, null, null, javaFilePath.toAbsolutePath().toString());

        URLClassLoader classLoader = null;
        try {
            classLoader = URLClassLoader.newInstance(new URL[] { rootPath.toUri().toURL() });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        ReloadingClassLoader rlc = new ReloadingClassLoader(classLoader);
//
//        String lib = path.substring(0, 65);
//        String file = path.substring(66, 78);
//        System.out.println("path = " + lib + "; file = " + file);
//
//        Path rootPath = Paths.get(lib);
//        Path javaFilePath = rootPath.resolve(file);
//
//        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
//        jc.run(null, null, null, javaFilePath.toAbsolutePath().toString());
//
//        URLClassLoader classLoader = null;
//        try {
//            classLoader = URLClassLoader.newInstance(new URL[] { rootPath.toUri().toURL() });
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        Class<?> cls = null;
//        try {
//            cls = Class.forName(
//                    file,
//                    true,
//                        classLoader);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Worker instance = null;
//        try {
//            instance = (Worker) cls.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        instance.doWork();
    }

    private static List readConsole() {
        List arrayOfLines = new ArrayList();
        Scanner input = new Scanner(System.in);
        String line = " ";

        while (!line.equals("")) {
            System.out.println("Put an emplty line to exit. Please input a new line: ");
            line = input.nextLine();
            arrayOfLines.add(line);

//            System.out.println("Your input line is " + line);
        }

        System.out.println("Your input array: " + arrayOfLines);
        return arrayOfLines;
    }
}