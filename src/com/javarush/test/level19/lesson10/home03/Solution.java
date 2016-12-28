package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        String[] entry;
        String name = "";
        int i;
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(args[0])));
            while (reader.ready()) {
                entry = reader.readLine().split("[ ]");
                for (i = 0; i < entry.length-4; i++)
                    name += entry[i] + " ";
                name.trim();
                name += entry[entry.length-4];
                PEOPLE.add(new Person(name, new Date(Integer.parseInt(entry[entry.length-1])-1900, Integer.parseInt(entry[entry.length-2])-1, Integer.parseInt(entry[entry.length-3]))));
                name = "";
            }
            reader.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }

}
