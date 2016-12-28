package com.javarush.test.level04.lesson13.task05;

import java.io.*;

/* Все любят Мамбу
Ввести с клавиатуры имя и используя цикл for 10 раз вывести: [*имя* любит меня.]
Пример текста:
Света любит меня.
Света любит меня.
…
*/

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        String name = new BufferedReader(new InputStreamReader(System.in)).readLine();
        for (int i = 1; i < 11; i++) System.out.println(name + " любит меня.");

        //напишите тут ваш код

    }
}
