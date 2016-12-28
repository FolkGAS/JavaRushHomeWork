package com.javarush.test.level18.lesson03.task04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Самые редкие байты
Ввести с консоли имя файла
Найти байт или байты с минимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        String fileName = "";
        HashMap<Integer, Integer> bytes = new HashMap<>();
        int min = 0, temp = 0;
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
            while (fis.available() > 0)
            {
                temp = fis.read();
                if (bytes.containsKey(temp))
                    bytes.put(temp, bytes.get(temp) + 1);
                else bytes.put(temp, 1);
            }
            min = bytes.get(temp);
            for (Map.Entry<Integer, Integer> entry : bytes.entrySet())
                min = (min > entry.getValue()) ? entry.getValue() : min;
            for (Map.Entry<Integer, Integer> entry : bytes.entrySet())
            {
                if (entry.getValue().equals(min))
                    System.out.print(entry.getKey() + " ");
            }
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
