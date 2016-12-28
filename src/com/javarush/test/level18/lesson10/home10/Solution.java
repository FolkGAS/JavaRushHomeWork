package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileInputStream fis = null;
            String fileName, name = "";
            int part;
            ArrayList <Integer> list = new ArrayList<>();
            while (!"end".equals(fileName = reader.readLine())) {
                if ("".equals(name))
                    name = fileName.substring(0, fileName.lastIndexOf(".part"));
                part = Integer.parseInt(fileName.substring(fileName.lastIndexOf(".part") + 5, fileName.length()));
                list.add(part);
            }
            Collections.sort(list);
            FileOutputStream fos = new FileOutputStream(name);
            for (int n : list) {
                fis = new FileInputStream(name + ".part" + n);
                while (fis.available() > 0)
                    fos.write(fis.read());
                fis.close();
            }
            fos.close();
            reader.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
