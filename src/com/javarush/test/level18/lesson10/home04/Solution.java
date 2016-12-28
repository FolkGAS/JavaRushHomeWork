package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        String file1Name, file2Name, tempFileName;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            FileInputStream fis1 = new FileInputStream(file1Name = reader.readLine());
            FileInputStream fis2 = new FileInputStream(file2Name = reader.readLine());
            FileOutputStream fosTemp = new FileOutputStream(tempFileName = (file1Name + ".temp"));
            while (fis1.available() > 0)
                fosTemp.write(fis1.read());
            fis1.close();
            fosTemp.close();
            reader.close();
            FileOutputStream fos1 = new FileOutputStream(file1Name);
            FileInputStream fisTemp = new FileInputStream(tempFileName);
            while (fis2.available() > 0)
                fos1.write(fis2.read());
            while (fisTemp.available() > 0)
                fos1.write(fisTemp.read());
            fos1.close();
            fis2.close();
            fisTemp.close();
        } catch (IOException exc) {exc.printStackTrace();}

    }
}
