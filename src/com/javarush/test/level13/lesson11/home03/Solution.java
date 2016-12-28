package com.javarush.test.level13.lesson11.home03;

/* Чтение файла
1. Считать с консоли имя файла.
2. Вывести в консоль(на экран) содержимое файла.
3. Не забыть освободить ресурсы. Закрыть поток чтения с файла и поток ввода с клавиатуры.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader reader1 = new BufferedReader(new FileReader(reader.readLine()));
        int chi = reader1.read();
        while (chi != -1)
        {
            System.out.print((char)chi);
            chi = reader1.read();
        }

        reader1.close();
        reader.close();

    }
}