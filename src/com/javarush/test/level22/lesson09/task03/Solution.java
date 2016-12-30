package com.javarush.test.level22.lesson09.task03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/* Составить цепочку слов
В методе main считайте с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставить все слова в таком порядке,
чтобы последняя буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
В файле не обязательно будет много слов.

Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн

Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
*/
public class Solution {
    public static void main(String[] args) {
        //...
        ArrayList<String> arr = new ArrayList<>();
        try(Scanner scanner = new Scanner(new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine()))){
            while (scanner.hasNext())
                arr.add(scanner.next());
        }catch (IOException exc){exc.printStackTrace();}
        String[] str = new String[arr.size()];
        arr.toArray(str);
        StringBuilder result = getLine(str);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0 || words == null) return new StringBuilder();
        if (words.length == 1) return new StringBuilder(words[0]);
        boolean is = true;
        ArrayList<String> arr = new ArrayList(Arrays.asList(words));
        Collections.shuffle(arr);
        StringBuilder sb = new StringBuilder(arr.get(0));
        arr.set(0, "");
        while (is){
            is = false;
            for (int i = 0; i < arr.size(); i++){
                if (!arr.get(i).equals("") && (sb.toString().substring(sb.length() - 1)).toLowerCase().equals(arr.get(i).substring(0, 1).toLowerCase())){
                    sb.append(" " + arr.get(i));
                    arr.set(i, "");
                    i = 0;
                    is = true;
                    Collections.shuffle(arr);
                }
            }
        }
        is = true;
        while (is){
            is = false;
            for (int i = 0; i < arr.size(); i++){
                if (!arr.get(i).equals("") && (sb.toString().substring(0, 1).toLowerCase().equals(arr.get(i).substring(arr.get(i).length() - 1).toLowerCase()))){
                    sb.insert(0, arr.get(i) + " ");
                    arr.set(i, "");
                    i = 0;
                    is = true;
                    Collections.shuffle(arr);
                }
            }
        }
        return sb;
    }
}
