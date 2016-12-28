package com.javarush.test.level18.lesson03.task01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;/* Максимальный байт
Ввести с консоли имя файла
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        String fileName = "";
        int max = 0, temp;
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
            if (fis.available() > 0) max = fis.read();
            while (fis.available() > 0)
            {
                temp = fis.read();
                max = (max < temp) ? temp : max;
            }
            System.out.println(max);
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
