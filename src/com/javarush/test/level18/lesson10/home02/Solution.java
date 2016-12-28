package com.javarush.test.level18.lesson10.home02;

/* Пробелы
В метод main первым параметром приходит имя файла.
Вывести на экран соотношение количества пробелов к количеству всех символов. Например, 10.45
1. Посчитать количество всех символов.
2. Посчитать количество пробелов.
3. Вывести на экран п2/п1*100, округлив до 2 знаков после запятой
4. Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        int intChar, count = 0, bytes;
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            bytes = fis.available();
            while (fis.available() > 0)
            {
                intChar = fis.read();
                    if (intChar == 32)
                        count++;
            }
            System.out.printf("%.2f", (1.0*count/bytes)*100);
            fis.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
