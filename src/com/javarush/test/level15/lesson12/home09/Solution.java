package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException{
        //add your code here

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine(), tempStr = "";
        List<String> list = new ArrayList<>();
        List<String> tempList = new ArrayList<>();

        for (int i = 0; i < url.length(); i++)
        {
            if (url.charAt(i) == '?')
            {
                url = url.substring(i+1);
                break;
            }
        }

        for (String st : url.split("\\&"))
        {
            tempList.add(st);
        }
        for (String st : tempList)
        {
            if (st.startsWith("obj"))
            {
                 tempStr =  st.substring(st.indexOf('=')+1);
            }
            if (st.contains("="))
            {
                list.add(st.substring(0, st.indexOf('=')));
            }
            else list.add(st);
        }

        for (String st : list)
            System.out.print(st + " ");
        System.out.println("");

        if (!"".equals(tempStr))
            try
            {
                alert(Double.parseDouble(tempStr));
            }
            catch (Exception exc)
            {
                alert(tempStr);
            }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
