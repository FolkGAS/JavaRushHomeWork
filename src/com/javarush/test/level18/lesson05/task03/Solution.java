package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        String[] fileName = new String[3];
        int count = 1, fileByte;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            fileName[0] = reader.readLine();
            fileName[1] = reader.readLine();
            fileName[2] = reader.readLine();
            FileInputStream fis = new FileInputStream(fileName[0]);
            FileOutputStream fos2 = new FileOutputStream(fileName[1]);
            FileOutputStream fos3 = new FileOutputStream(fileName[2]);
            int mid = (int)(Math.ceil(fis.available()/2.0));
            while (count <= mid) {
                fileByte = fis.read();
                fos2.write(fileByte);
                count++;
            }
            while (fis.available() > 0) {
                fileByte = fis.read();
                fos3.write(fileByte);
            }
            reader.close();
            fis.close();
            fos2.close();
            fos3.close();
        } catch (IOException exc) {
            exc.printStackTrace();}

    }
}

