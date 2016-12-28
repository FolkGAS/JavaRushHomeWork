package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileWriter fileWriter = new FileWriter(fileName, false);
        ArrayList<String> strings = new ArrayList<>();
        String text;
        do
        {
            text = reader.readLine();
            strings.add(text);;
        } while(!"exit".equals(text));

        for (int i = 0; i < strings.size()-1; i++)
            {
                fileWriter.write(strings.get(i));
                fileWriter.append('\n');
            }
        fileWriter.write(strings.get(strings.size()-1));
        reader.close();
        fileWriter.close();
    }
}
