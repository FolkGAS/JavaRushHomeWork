package com.javarush.test.level18.lesson10.home07;

/* Поиск данных внутри файла
Считать с консоли имя файла
Найти в файле информацию, которая относится к заданному id, и вывести ее на экран в виде, в котором она записана в файле.
Программа запускается с одним параметром: id (int)
Закрыть потоки. Не использовать try-with-resources

В файле данные разделены пробелом и хранятся в следующей последовательности:
id productName price quantity

где id - int
productName - название товара, может содержать пробелы, String
price - цена, double
quantity - количество, int

Информация по каждому товару хранится в отдельной строке
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        String idString = "";
        if (args.length != 1) return;
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();

            BufferedReader reader2 = new BufferedReader(new FileReader(fileName));
            while ((idString = reader2.readLine()) != null) {
                if (idString.startsWith((args[0]) + " "))
                {
                    System.out.println(idString);
                    break;
                }
            }
            reader.close();
            reader2.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
