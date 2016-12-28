package com.javarush.test.level09.lesson11.home08;

import java.util.ArrayList;

/* Список из массивов чисел
Создать список, элементами которого будут массивы чисел. Добавить в список пять объектов–массивов длиной 5, 2, 4, 7, 0 соответственно. Заполнить массивы любыми данными и вывести их на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList()
    {
        //напишите тут ваш код
        ArrayList<int[]> in = new ArrayList<int[]>();
        int[] i1 = {1, 2, 3, 4 ,5}, i2 = {6, 7}, i3 = {8, 9, 0, 1}, i4 = {2, 3, 4 ,5 ,6 ,7 ,8}, i5 = {};
        in.add(i1);
        in.add(i2);
        in.add(i3);
        in.add(i4);
        in.add(i5);
        return in;
    }

    public static void printList(ArrayList<int[]> list)
    {
        for (int[] array: list )
        {
            for (int x: array)
            {
                System.out.println(x);
            }
        }
    }
}
