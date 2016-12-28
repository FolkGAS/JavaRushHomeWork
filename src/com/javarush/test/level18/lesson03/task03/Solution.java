package com.javarush.test.level18.lesson03.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;/* Самые частые байты
Ввести с консоли имя файла
Найти байт или байты с максимальным количеством повторов
Вывести их на экран через пробел
Закрыть поток ввода-вывода
*/
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        String fileName = "";
        HashMap<Integer, Integer> bytes = new HashMap<>();
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
            while (fis.available() > 0)
            {
                temp = fis.read();
                if (bytes.containsKey(temp))
                    bytes.put(temp, bytes.get(temp) + 1);
                else bytes.put(temp, 1);
            }
            for (Map.Entry<Integer, Integer> entry : bytes.entrySet())
                max = (max < entry.getValue()) ? entry.getValue() : max;
            for (Map.Entry<Integer, Integer> entry : bytes.entrySet())
            {
                    if (entry.getValue().equals(max))
                    System.out.print(entry.getKey() + " ");
            }
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
