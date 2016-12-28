package com.javarush.test.level18.lesson03.task02;

/* Минимальный байт
Ввести с консоли имя файла
Найти минимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        String fileName = "";
        int min = 0, temp;
        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(isr))
        {
            fileName = reader.readLine();
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream(fileName))
        {
            if (fis.available() > 0) min = fis.read();
            while (fis.available() > 0)
            {
                temp = fis.read();
                min = (min > temp) ? temp : min;
            }
            System.out.println(min);
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
