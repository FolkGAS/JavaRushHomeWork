package com.javarush.test.level19.lesson05.task05;

/* Пунктуация
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Удалить все знаки пунктуации, включая символы новой строки. Результат вывести во второй файл.
http://ru.wikipedia.org/wiki/%D0%9F%D1%83%D0%BD%D0%BA%D1%82%D1%83%D0%B0%D1%86%D0%B8%D1%8F
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
                if (!((char)ch == '.' || (char)ch == ',' || (char)ch == ';' || (char)ch == ':' || (char)ch == '!' ||
                        (char)ch == '?' || (char)ch == '-' || (char)ch == ' ' || ch == 10 || ch == 13 ||
                        (char)ch == '"' || (char)ch == '[' || (char)ch == ']' || (char)ch == '(' || (char)ch == '\'' ||
                        (char)ch == ')' || (char)ch == '{' || (char)ch == '}' || (char)ch == '('))
                    fw.write(ch);
            }
            fr.close();
            fw.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
