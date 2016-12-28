package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.TreeSet;




/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        String fileName = "";
        TreeSet<Integer> bytes = new TreeSet<>();
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
                bytes.add(fis.read());
            }
            for (int i : bytes)
                System.out.print(i + " ");
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
