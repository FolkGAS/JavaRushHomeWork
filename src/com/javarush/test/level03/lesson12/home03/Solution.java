package com.javarush.test.level03.lesson12.home03;

/* Я буду зарабатывать $50 в час
Ввести с клавиатуры число n.
Вывести на экран надпись «Я буду зарабатывать $n в час».
Пример:
Я буду зарабатывать $50 в час
*/
import java.io.*;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        int money;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        money = Integer.parseInt(reader.readLine());
        System.out.println("Я буду зарабатывать $" + money + " в час");
        //напишите тут ваш код
    }
}