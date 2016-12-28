package com.javarush.test.level06.lesson11.bonus03;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* Задача по алгоритмам
Задача: Написать программу, которая вводит с клавиатуры 5 чисел и выводит их в возрастающем порядке.
Пример ввода:
3
2
15
6
17
Пример вывода:
2
3
6
15
17
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader  = new BufferedReader(new InputStreamReader(System.in));

        //напишите тут ваш код
        int temp, i, j;
        int array[] = new int[5];
        for(i = 1; i <= 5; i++)
            array[i-1] = Integer.parseInt(reader.readLine());

        for (j = 1; j < array.length; j++)
            for(i = array.length-1; i >= j; i--)
                if (array[i] < array[i-1])
                {
                    temp = array[i];
                    array[i] = array[i-1];
                    array[i-1] = temp;
                }

        for(i = 1; i <= array.length; i++)
            System.out.println(array[i-1]);

    }
}
