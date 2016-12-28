package com.javarush.test.level19.lesson10.home05;

/* Слова с цифрами
В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со слов, разделенные пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        String word = "";
        int count = 0;
        Pattern pattern = Pattern.compile("\\d");
        try {
            Scanner scanner = new Scanner(new FileInputStream(args[0]), "windows-1251");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "windows-1251"));
            while (scanner.hasNext()) {
                word = scanner.next();
                Matcher matcher =pattern.matcher(word);
                if (matcher.find()) {
                    count++;
                    if (count != 1) writer.write(" ");
                    writer.write(word);
                }
            }
            scanner.close();
            writer.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
