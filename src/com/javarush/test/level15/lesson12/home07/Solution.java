package com.javarush.test.level15.lesson12.home07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Файл в статическом блоке
1. Инициализируй константу Constants.FILE_NAME полным путем к файлу с данными, который содержит несколько строк.
2. В статическом блоке считай из файла с именем Constants.FILE_NAME все строки и добавь их по-отдельности в List lines.
3. Закрой поток ввода методом close().
*/

public class Solution {
    public static List<String> lines = new ArrayList<String>();

    static
    {
        String str;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(Constants.FILE_NAME));

            while ((str = reader.readLine()) != null)
                lines.add(str);
            reader.close();
        }
        catch (IOException exc)
        {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(lines);
    }
}
