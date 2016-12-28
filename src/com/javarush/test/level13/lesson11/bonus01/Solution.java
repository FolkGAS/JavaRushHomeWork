package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        ArrayList<Integer> nums = new ArrayList<>();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader reader1 = new BufferedReader(new FileReader(fileName));

        String s = reader1.readLine();
        while (s != null)
        {
            if (Integer.parseInt(s) % 2 == 0)
                nums.add(Integer.parseInt(s));
            s = reader1.readLine();
        }
        Collections.sort(nums);
        for (int i : nums)
            System.out.println(i);

    }
}
