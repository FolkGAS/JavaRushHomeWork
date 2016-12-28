package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine(), temp = "", result = "";
        int spaceCount = 0;

        //напишите тут ваш код
        if (Character.toString(s.charAt(0)).equals(" "))
            for (int i = 0; i < s.length(); i++)
            {
                temp = Character.toString(s.charAt(i));
                if (temp.equals(" "))
                {
                    result += temp;
                    spaceCount++;
                } else if (spaceCount > 0)
                {
                    result += temp.toUpperCase();
                    spaceCount = 0;
                } else
                    result += temp;
            }

        else
        {
            result += Character.toString(s.charAt(0)).toUpperCase();
            for (int i = 1; i < s.length(); i++)
            {
                temp = Character.toString(s.charAt(i));
                if (temp.equals(" "))
                {
                    result += temp;
                    spaceCount++;
                } else if (spaceCount > 0)
                {
                    result += temp.toUpperCase();
                    spaceCount = 0;
                } else
                    result += temp;
            }
        }

        System.out.println(result);

    }


}
