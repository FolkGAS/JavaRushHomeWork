package com.javarush.test.level05.lesson12.home05;

/* Вводить с клавиатуры числа и считать их сумму
Вводить с клавиатуры числа и считать их сумму, пока пользователь не введёт слово «сумма». Вывести на экран полученную сумму.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        int sum = 0;
        String exit = "0";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!(exit.equals("сумма"))) {
            exit = reader.readLine();
            if (!(exit.equals("сумма"))) sum += Integer.parseInt(exit);
        }

        System.out.println(sum);

        //напишите тут ваш код
    }

}
