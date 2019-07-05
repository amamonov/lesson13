package part1.lesson12;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.net.MalformedURLException;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.Properties;

/**
 * Необходимо создать программу, которая продемонстрирует утечку памяти в Java. При этом объекты должны не только
 * создаваться, но и периодически частично удаляться, чтобы GC имел возможность очищать часть памяти.
 * Через некоторое время программа должна завершиться с ошибкой OutOfMemoryError c пометкой Java Heap Space.
 *
 * Доработать программу так, чтобы ошибка OutOfMemoryError возникала в Metaspace /Permanent Generation
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 2.0
 *
 * -XX:+UseSerialGC -Xmx100m -XX:MaxPermSize=20m
 */
public class task02 {
    final static int SIZE = 1_000_000_000;
    static String path;
    static String[] lib = {"rt.jar",                                                    /* 53MB */
                           "platform-impl.jar",                                         /* 101MB */
                           "java-impl.jar",                                             /* 45MB */
                           "tools.jar"};                                                /* 17MB */

    public static void main(String[] args) {
        Random rand = new Random();
        int multiplier = 100;

        try {
            String fileprop = "F:\\JavaProjects\\Innopolis\\src\\part1\\lesson12\\resources\\config.properties";
            FileInputStream fis = new FileInputStream(fileprop);
            Properties property = new Properties();
            property.load(fis);
            path = property.getProperty("importedLibs");

            Thread.sleep(10_000);

            for (int i = 0; i < lib.length; i++) {
                Thread.sleep(10_000);

                ZipFile jarF = new ZipFile(path + lib[i]);
                Enumeration en = jarF.entries();

                while (en.hasMoreElements()) {
                    ZipEntry val = (ZipEntry) en.nextElement();
                    System.out.println(val.getName());
                }
            }

            Thread.sleep(10_000);
            for (int i = 0; i < SIZE; i++) {
                Thread.sleep(1);
                Integer[] value = new Integer[multiplier];

                for (int j = 0; j < value.length; j++) {
                    value[j] = rand.nextInt();
                }

                multiplier *= 1.01;                                                      /* генератор утечки памяти */
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}