package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        boolean contains = false;
        String first = "", second = "";
        ArrayList<String> arr = new ArrayList<>();
        try
        {
            Scanner scanner = new Scanner(new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine()));
            while (scanner.hasNext())
                arr.add(scanner.next());
            scanner.close();
        }catch (IOException exc){exc.getMessage();}
        for (int i = 0; i < arr.size();){
            for (int j = i + 1; j < arr.size();){
                contains = false;
                first = arr.get(i);
                second = arr.get(j);
                StringBuilder sb = new StringBuilder(second).reverse();
                if (first.equals(sb.toString())){
                    Pair p = new Pair();
                    p.first = first;
                    p.second = second;
                    for (Pair pr : result)
                        if (pr.first.equals(first) || pr.second.equals(first)) contains = true;
                    if (!contains) result.add(p);
                }
                j++;
            }
            i++;
        }
        for (Pair p : result)
            System.out.println(p);
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
