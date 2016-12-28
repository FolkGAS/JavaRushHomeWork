package com.javarush.test.level04.lesson04.task04;

/* Время года
Реализовать метод checkSeason. По номеру месяца, метод должен определить время года (зима, весна, лето, осень) и вывести на экран.
Пример для номера месяца 2:
зима
Пример для номера месяца 5:
весна
*/

public class Solution
{
    public static void main(String[] args) {
        checkSeason(12);
        checkSeason(4);
        checkSeason(7);
        checkSeason(10);
    }

    public static void checkSeason(int mount){
        switch (mount){
            case (12):
            case (1):
            case (2):
                System.out.println("зима");
                break;
            case (3):
            case (4):
            case (5):
                System.out.println("весна");
                break;
            case (6):
            case (7):
            case (8):
                System.out.println("лето");
                break;
            case (9):
            case (10):
            case (11):
                System.out.println("осень");
                break;

        }

        //::CODE:
    }
}