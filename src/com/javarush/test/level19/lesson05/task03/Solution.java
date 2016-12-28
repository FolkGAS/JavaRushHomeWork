package com.javarush.test.level19.lesson05.task03;

/* Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки. Не использовать try-with-resources

Пример тела файла:
12 text var2 14 8v 1

Результат:
12 14 1
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        String text = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file1Name = reader.readLine();
            String file2Name = reader.readLine();
            reader.close();
            FileWriter fw = new FileWriter(file2Name);
            Scanner scanner = new Scanner(new FileInputStream(file1Name));
            while (scanner.hasNext()) {
                text = scanner.next();
                if (text.matches("^[ 0-9]+$"))
                    fw.write(text + " ");
            }
            scanner.close();
            fw.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
