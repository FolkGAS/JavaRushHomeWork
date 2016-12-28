package com.javarush.test.level19.lesson10.home04;

        import java.io.*;
        import java.util.ArrayList;
import java.util.List;

/* Ищем нужные строки
Считать с консоли имя файла.
Вывести в консоль все строки из файла, которые содержат всего 2 слова из списка words
Закрыть потоки. Не использовать try-with-resources

Пример: words содержит слова А, Б, В
Строки:
В Б А Д  //3 слова из words, не подходит
Д А Д    //1 слово из words, не подходит
Д А Б Д  //2 слова - подходит, выводим
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) {
        String filename = "", str = "";
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            filename = reader.readLine();
            reader.close();
            BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            while ((str = fr.readLine()) != null)
            {
                for (String check : words)
                    if (str.contains(check + " ") | str.endsWith (" " + check)) count++;
                if (2 == count) System.out.println(str);
                count = 0;
            }
            fr.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
}
