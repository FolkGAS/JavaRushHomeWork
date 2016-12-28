package com.javarush.test.level19.lesson10.home02;

/* Самый богатый
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Вывести в консоль имена, у которых максимальная сумма
Имена разделять пробелом либо выводить с новой строки
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        TreeMap<String, Double> map = new TreeMap<>();
        String[] str;
        double max = 0;
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(args[0])));
            while (reader.ready()) {
                str = reader.readLine().split(" ");
                if (map.containsKey(str[0]))
                    map.put(str[0], map.get(str[0]) + Double.valueOf(str[1]));
                else map.put(str[0], Double.valueOf(str[1]));
            }
            reader.close();
        } catch (IOException exc) {exc.printStackTrace();}
        for (Map.Entry<String, Double> entry : map.entrySet())
            max = max > entry.getValue() ? max : entry.getValue();
        for (Map.Entry<String, Double> entry : map.entrySet())
            if (max == entry.getValue()) System.out.println(entry.getKey());
    }
}
