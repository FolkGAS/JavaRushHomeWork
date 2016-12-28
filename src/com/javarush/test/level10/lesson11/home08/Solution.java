package com.javarush.test.level10.lesson11.home08;

import java.util.ArrayList;
import java.util.StringTokenizer;

/* Массив списков строк
Создать массив, элементами которого будут списки строк. Заполнить массив любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<String>[] arrayOfStringList =  createList();
        printList(arrayOfStringList);
    }

    public static ArrayList<String>[] createList()
    {
        //напишите тут ваш код
        ArrayList<String>[] arStr = new ArrayList[5];
        ArrayList<String> a0 = new ArrayList<String>();
        ArrayList<String> a1 = new ArrayList<String>();
        ArrayList<String> a2 = new ArrayList<String>();
        ArrayList<String> a3 = new ArrayList<String>();
        ArrayList<String> a4 = new ArrayList<String>();

        arStr[0] = a0;
        arStr[1] = a1;
        arStr[2] = a2;
        arStr[3] = a3;
        arStr[4] = a4;

        for (int i = 0; i < 5; i++)
            for (int j =0; j < Math.random()*10; j++)
                arStr[i].add(String.valueOf((int)(Math.random()*9)));

        return arStr;
    }

    public static void printList(ArrayList<String>[] arrayOfStringList)
    {
        for (ArrayList<String> list: arrayOfStringList)
        {
            for (String s : list)
            {
                System.out.println(s);
            }
        }
    }
}