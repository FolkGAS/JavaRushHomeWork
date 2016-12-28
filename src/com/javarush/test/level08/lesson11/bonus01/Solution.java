package com.javarush.test.level08.lesson11.bonus01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* Номер месяца
Программа вводит с клавиатуры имя месяца и выводит его номер на экран в виде: «May is 5 month».
Используйте коллекции.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, String> months = new HashMap<String, String>();
        String choise = "";

        months.put("January", "1");
        months.put("February", "2");
        months.put("March", "3");
        months.put("April", "4");
        months.put("May", "5");
        months.put("June", "6");
        months.put("July", "7");
        months.put("August", "8");
        months.put("September", "9");
        months.put("October", "10");
        months.put("November", "11");
        months.put("December", "12");

        while (true)
        {
            choise = reader.readLine();
            if (months.containsKey(choise))
            {
                System.out.println(choise + " is " + months.get(choise) + " month");
                break;
            }
        }
    }

}
