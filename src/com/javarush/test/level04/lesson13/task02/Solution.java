package com.javarush.test.level04.lesson13.task02;

import java.io.*;

/* Рисуем прямоугольник
Ввести с клавиатуры два числа m и n.
Используя цикл for вывести на экран прямоугольник размером m на n из восьмёрок.
Пример: m=2, n=4
8888
8888
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int m, n;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(reader.readLine());
        n = Integer.parseInt(reader.readLine());
        for (int j = 1; j <= m; j++){
            for (int i = 1; i <= n; i++) System.out.print(8);
            System.out.println("");
        }

        //напишите тут ваш код

    }
}
