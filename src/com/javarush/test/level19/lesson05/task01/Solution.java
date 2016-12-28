package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {

        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file1Name = reader.readLine();
            String file2Name = reader.readLine();
            reader.close();
            FileInputStream fis = new FileInputStream(file1Name);
            FileOutputStream fos = new FileOutputStream(file2Name);
            while (fis.available() > 1) {
                fis.read();
                fos.write(fis.read());
            }
            fis.close();
            fos.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
