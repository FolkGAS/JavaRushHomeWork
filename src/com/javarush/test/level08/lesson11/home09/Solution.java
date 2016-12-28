package com.javarush.test.level08.lesson11.home09;

import java.util.Date;

/* Работа с датой
1. Реализовать метод isDateOdd(String date) так, чтобы он возвращал true, если количество дней с начала года - нечетное число, иначе false
2. String date передается в формате MAY 1 2013
Не забудьте учесть первый день года.
Пример:
JANUARY 1 2000 = true
JANUARY 2 2020 = false
*/

public class Solution
{
    public static void main(String[] args)
    {
        isDateOdd("JANUARY 1 2000");
    }

    public static boolean isDateOdd(String date)
    {
        Date dateThis = new Date(date);
        Date dateStart = new Date();
        dateStart.setSeconds(0);
        dateStart.setMinutes(0);
        dateStart.setHours(0);
        dateStart.setDate(0);
        dateStart.setMonth(0);
        dateStart.setYear(dateThis.getYear());
        long ms = dateThis.getTime() - dateStart.getTime();
        int days = (int) (ms / (24 * 60 * 60 * 1000));
        if (days % 2 == 0)
            return true;
        else
            return false;
    }
}
