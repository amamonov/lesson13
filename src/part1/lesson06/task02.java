package part1.lesson06;

import java.io.*;
import java.security.SecureRandom;

/**
 * Создать генератор текстовых файлов, работающий по следующим правилам:
 *
 *     Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
 *     Слово состоит из 1<=n2<=15 латинских букв
 *     Слова разделены одним пробелом
 *     Предложение начинается с заглавной буквы
 *     Предложение заканчивается (.|!|?)+" "
 *     Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
 *     Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
 *
 * Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability), который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 *
 * @author Alexander.Mamonov@protonmail.ch
 * @version 1.0
 */
public class task02 {
    public static void main(String[] args) {
        String path = "src/part1/lesson06/";                                                /* путь выводимых файлов */
        int n = 3;                                                                          /* количество файлов */
        int size = 1024;                                                                    /* размер файла */
        String words[];                                                                     /* массив ген. слов */
        int maxWords = 1000;                                                                /* число слов в массиве */
        int maxWordLength = 15;                                                             /* макс. длина слова */

        words = generateWords(maxWordLength, maxWords);
        getFiles(path, n, size, words);
    }

    /**
     * Генерируем массив исходных слов
     * @param maxWordLength максимальная длина каждого слова
     * @param maxWords максимальное число слов в массиве
     * @return сгенерированный массив слов
     */
    private static String[] generateWords(int maxWordLength, int maxWords) {
        String[] words = new String[maxWords];
        char[] letters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < maxWords; i++) {
            words[i] = "";
            for (int j = 0; j < (1 + rand.nextInt(maxWordLength)); j++) {
                words[i] += letters[rand.nextInt(letters.length)];
            }
//            System.out.println(words[i] + " " + words[i].length());
        }

        return words;
    }

    /**
     * Требуемый в задании метод
     * @param path путь выводимых файлов
     * @param n количество файлов
     * @param size размер файла в байтах
     * @param words массив сгенерированных слов
     */
    private static void getFiles(String path, int n, int size, String[] words) {
        int maxParagraphLength = 20;
        int maxSentenceLength = 15;
        int commaProbability = 10;

        for (int i = 1; i <= n; i++) {
            String text = generateText(maxParagraphLength, maxSentenceLength, commaProbability, size, words);
            System.out.println();
            writeText(path, i, text);
        }
    }

    /**
     * Генерируем финальный текст
     * @param maxParagraphLength максимальная длина абзаца
     * @param maxSentenceLength максимальная длина предложения
     * @param commaProbability вероятность появления запятой
     * @param size размер файла в байтах
     * @param words массив исходных слов
     * @return сгенерированный текст
     */
    private static String generateText(int maxParagraphLength,
                                         int maxSentenceLength,
                                         int commaProbability,
                                         int size,
                                         String[] words) {
        String text = "";

        while (text.getBytes().length < size) {
            text += generateParagraph(maxParagraphLength, maxSentenceLength, commaProbability, words);
        }

        return text;
    }

    /**
     * Генерируем абзац
     * @param maxParagraphLength максимальная длина абзаца
     * @param maxSentenceLength максимальная длина предложения
     * @param commaProbability вероятность появления запятой
     * @param words массив исходных слов
     * @return сгенерированный абзац
     */
    private static String generateParagraph(int maxParagraphLength,
                                             int maxSentenceLength,
                                             int commaProbability,
                                             String[] words) {
        String paragraph = "";
        SecureRandom rand = new SecureRandom();
        int paragaphSize = 2 + rand.nextInt(maxParagraphLength-2);

        for (int i = 0; i <= paragaphSize; i++) {
            if (i == 0) {
                paragraph += "    ";
            }
            else if (i == paragaphSize) {                                                   /* перенос на нов. строку */
                paragraph += "\n";
            }
            else {
                paragraph += generateSentence(maxSentenceLength, commaProbability, words);
            }
        }
//        System.out.print("Your paragraph:" + paragraph);

        return paragraph;
    }

    /**
     * Генерируем предложение
     * @param maxSentenceLength максимальная длина предложения
     * @param commaProbability вероятность появления запятой
     * @param words массив исходных слов
     * @return сгенерированное предложение
     */
    private static String generateSentence(int maxSentenceLength,
                                           int commaProbability,
                                           String[] words) {
        String sentence = "";
        String word = "";
        SecureRandom rand = new SecureRandom();
        int sentenceSize = 3 + rand.nextInt(maxSentenceLength-2);

        for (int i = 0; i < sentenceSize; i++) {                                           /* генерируем предложение */
            word = words[rand.nextInt(words.length)];

            if (i == 0) {                                                                  /* заглавная буква */
                sentence = word.substring(0, 1).toUpperCase();
                if (word.length() > 1) {
                    sentence += word.substring(1, word.length());
                }
            }
            else if (i == (sentenceSize-1)) {                                              /* окончание предложения */
                sentence = sentence.substring(0, sentence.length()-1);

                String end;
                switch (rand.nextInt(5)) {
                    case 1:
                        end = "? ";
                        break;
                    case 2:
                        end = "! ";
                        break;
                    default:
                        end = ". ";
                }
                sentence += end;
            }
            else {
                sentence += word;

                if ((rand.nextInt(100) <= commaProbability) &&                          /* расставляем запятые */
                        (i < (sentenceSize - 2))){
                    sentence += ",";
                }

                sentence += " ";
            }
        }
//        System.out.println("Your sentence: " + sentence);

        return sentence;
    }

    /**
     * Записываем текст в файл
     * @param path путь к файлу
     * @param n номер в названии файла
     * @param text текст в файле
     */
    private static void writeText(String path, int n, String text) {
        try {
            FileOutputStream fos = new FileOutputStream(path + "file" + n + ".txt");
            fos.write(text.getBytes());
            System.out.print("Your file" + n + " text:\n" + text);
            fos.close();                                                                    /* на всякий случай */
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}