package com.javarush.test.level18.lesson05.task02;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки. Не использовать try-with-resources

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        String fileName = "";
        int ch, count = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            fileName = reader.readLine();
            FileInputStream fis = new FileInputStream(fileName);
            while (fis.available() > 0) {
                ch = fis.read();
                if (ch == ',') count++;
            }
            System.out.println(count);
            reader.close();
            fis.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
