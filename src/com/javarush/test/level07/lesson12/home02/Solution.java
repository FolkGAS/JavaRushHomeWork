package com.javarush.test.level07.lesson12.home02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Переставить M первых строк в конец списка
Ввести с клавиатуры 2 числа N  и M.
Ввести N строк и заполнить ими список.
Переставить M первых строк в конец списка.
Вывести список на экран, каждое значение с новой строки.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<String>();
        int n = 0, m = 0;
        String temp = "";
        n = Integer.parseInt(reader.readLine());
        m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++)
            list.add(reader.readLine());
        for (int i = 0; i < m; i++)
        {
            temp = list.get(0);
            list.add(temp);
            list.remove(0);
        }
        for (String s : list)
            System.out.println(s);
    }
}
