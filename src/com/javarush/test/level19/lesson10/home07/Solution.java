package com.javarush.test.level19.lesson10.home07;

/* Длинные слова
В метод main первым параметром приходит имя файла1, вторым - файла2
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6
Закрыть потоки. Не использовать try-with-resources

Пример выходных данных:
длинное,короткое,аббревиатура
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        String word = "";
        int count = 0;
        try {
            Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(args[0])));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
            while (scanner.hasNext()) {
                word = scanner.next();
                if (word.length() > 6)
                {
                    count++;
                    if (count != 1)
                        writer.write(",");
                    writer.write(word);
                }
            }
            scanner.close();
            writer.flush();
            writer.close();
            count = 0;
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
