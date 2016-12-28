package com.javarush.test.level14.lesson08.bonus02;

/* НОД
Наибольший общий делитель (НОД).
Ввести с клавиатуры 2 целых положительных числа.
Вывести в консоль наибольший общий делитель.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num1 = Integer.parseInt(reader.readLine());
        int num2 = Integer.parseInt(reader.readLine());
        reader.close();
        ArrayList<Integer> list1 = nod(num1);
        ArrayList<Integer> list2 = nod(num2);
        int nod = 1;

        for (int numNod1 : list1)
        {
            for (int numNod2 : list2)
            {
                if (numNod1 == numNod2 && nod < numNod1)
                    if (nod < numNod1)
                        nod = numNod1;
            }
        }
        System.out.println(nod);
    }
    public static ArrayList<Integer> nod (int num)
    {
        int nod = 1;
        ArrayList<Integer> nods = new ArrayList<>();
        for (int i = 1; i <= num; i++)
        {
            if (num % i == 0)
            {
                nods.add(i);
            }
        }
        return nods;
    }
}
