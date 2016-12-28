package com.javarush.test.level04.lesson13.task03;

import java.io.*;

/* Рисуем треугольник
Используя цикл for вывести на экран прямоугольный треугольник из восьмёрок со сторонами 10 и 10.
Пример:
8
88
888
...
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int i = 1;
        for (int j = 1; j <= i && i < 11; j++){
            System.out.print("8");
            if (i == j)
            {
                System.out.println("");
                i++;
                j = 0;
            }
        }

        //напишите тут ваш код

    }
}
