package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        int count = 0, intChar;
        try
        {
            FileInputStream fis = new FileInputStream(args[0]);
            while (fis.available() > 0)
            {
                intChar = fis.read();
                for (int i = 65; i <= 90; i++)
                    if (intChar == i)
                        count++;
                for (int i = 97; i <= 122; i++)
                    if (intChar == i)
                        count++;
            }
            System.out.println(count);
            fis.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
