package com.javarush.test.level05.lesson12.bonus03;

import java.io.*;

/* Задача по алгоритмам
Написать программу, которая:
1. вводит с консоли число N > 0
2. потом вводит N чисел с консоли
3. выводит на экран максимальное из введенных N чисел.
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maximum = 0, num = 0;

        //напишите тут ваш код
        int N = Integer.parseInt(reader.readLine());
        for (int i = 1; i <= N; i++){
            if (i == 1) maximum = Integer.parseInt(reader.readLine());
            else{
                num = Integer.parseInt(reader.readLine());
                if (maximum < num) maximum = num;
            }
        }
        System.out.println(maximum);
    }
}
