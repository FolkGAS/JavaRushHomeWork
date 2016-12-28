package com.javarush.test.level04.lesson07.task03;

/* Положительные числа
Ввести с клавиатуры три целых числа. Вывести на экран количество положительных чисел в исходном наборе.
Пример для чисел -4 6 6:
2
Пример для чисел -6 -6 -3:
0
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int a, b, c, q = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if ((Integer.parseInt(reader.readLine())) > 0)  q++;
        if ((Integer.parseInt(reader.readLine())) > 0)  q++;
        if ((Integer.parseInt(reader.readLine())) > 0)  q++;
        System.out.println(q);
    }
        //напишите тут ваш код
}
