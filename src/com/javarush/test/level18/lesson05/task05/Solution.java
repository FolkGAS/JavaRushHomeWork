package com.javarush.test.level18.lesson05.task05;

/* DownloadException
1 Считывать с консоли имена файлов.
2 Если файл меньше 1000 байт, то:
2.1 Закрыть потоки
2.2 выбросить исключение DownloadException
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws DownloadException {
        FileInputStream fis = null;
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while ((fis = new FileInputStream(reader.readLine())).available() >= 1000)  {}
            reader.close();
            fis.close();
            throw new DownloadException();
        }catch (IOException exc) {exc.printStackTrace();}
    }

    public static class DownloadException extends Exception{

    }
}
