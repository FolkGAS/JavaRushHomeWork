package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        String[] entry;
        TreeMap<String, Double> entries = new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            while (reader.ready()) {
                entry = reader.readLine().split(" ");
                if (entries.containsKey(entry[0]))
                    entries.put(entry[0], entries.get(entry[0]) + Double.valueOf(entry[1]));
                else entries.put(entry[0], Double.valueOf(entry[1]));
            }
            reader.close();
        } catch (IOException exc) {exc.printStackTrace();}
        for (Map.Entry<String, Double> en : entries.entrySet())
            System.out.println(en.getKey() + " " + en.getValue());
    }
}
