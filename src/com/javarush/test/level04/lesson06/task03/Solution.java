package com.javarush.test.level04.lesson06.task03;

/* Сортировка трех чисел
Ввести с клавиатуры три числа, и вывести их в порядке убывания.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int a, b, c, d, e, f;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        a = Integer.parseInt(reader.readLine());
        b = Integer.parseInt(reader.readLine());
        c = Integer.parseInt(reader.readLine());

        d = max(max(a, b), max(b, c));
        f = min(min(a, b), min(b, c));
        e = (a + b + c) - (d + f);
        System.out.println(d + " " + e + " " + f);

        //напишите тут ваш код

    }

    public static int max(int a, int b){
        if (a >= b) return a;
        else return b;
    }
    public static int min(int a, int b){
        if (a <= b) return a;
        else return b;
    }
}
