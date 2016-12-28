package com.javarush.test.level19.lesson05.task04;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Заменить все точки "." на знак "!", вывести во второй файл.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        int ch;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String file1Name = reader.readLine();
            String file2Name = reader.readLine();
            reader.close();
            FileReader fr = new FileReader(file1Name);
            FileWriter fw = new FileWriter(file2Name);
            while (fr.ready()) {
                ch = fr.read();
                if ((char)ch == '.')
                    ch = (int)'!';
                fw.write(ch);
            }
            fr.close();
            fw.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
