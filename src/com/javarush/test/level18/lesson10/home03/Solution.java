package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file1Name = reader.readLine();
            String file2Name = reader.readLine();
            String file3Name = reader.readLine();
            FileOutputStream fos1 = new FileOutputStream(file1Name, true);
            FileInputStream fis2 = new FileInputStream(file2Name);
            FileInputStream fis3 = new FileInputStream(file3Name);
            while (fis2.available() > 0)
                fos1.write(fis2.read());
            while (fis3.available() > 0)
                fos1.write(fis3.read());
            reader.close();
            fos1.close();
            fis2.close();
            fis3.close();
        } catch (IOException exc) {exc.printStackTrace();}

    }
}
