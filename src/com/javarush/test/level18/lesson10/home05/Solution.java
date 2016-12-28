package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        int num;
        String strnum = "";
        String[] sNums;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file1Name = reader.readLine();
            String file2Name = reader.readLine();
            FileReader fr = new FileReader(file1Name);
            FileWriter fw = new FileWriter(file2Name);
            while ((num = fr.read()) != -1)
                strnum +=(char)num;
            sNums = strnum.split(" ");
            for (String s : sNums)
                fw.write(String.valueOf (Math.round(Double.parseDouble(s))).toString() + " ");
            reader.close();
            fr.close();
            fw.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
