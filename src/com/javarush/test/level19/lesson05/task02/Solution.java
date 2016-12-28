package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        String word = null;
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();
            Scanner scanner = new Scanner(new FileInputStream(fileName)).useDelimiter(Pattern.compile("[^\\w]"));
            while (scanner.hasNext()) {
                word = scanner.next();
                if ("world".equals(word))
                    count++;
            }
            scanner.close();
        }catch (IOException exc) {exc.printStackTrace();}
        System.out.println(count);
    }
}
