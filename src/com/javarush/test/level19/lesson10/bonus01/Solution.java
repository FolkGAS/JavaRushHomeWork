package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        ArrayList<String> f1List = new ArrayList<>();
        ArrayList<String> f2List = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader f1reader = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())));
            BufferedReader f2reader = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())));
            while (f1reader.ready()) f1List.add(f1reader.readLine());
            while (f2reader.ready()) f2List.add(f2reader.readLine());
            while ((f1List.size() > 0) && f2List.size() > 0)
            {
                if (f1List.get(0).equals(f2List.get(0))){
                    lines.add(new LineItem(Type.SAME, f1List.get(0)));
                    f1List.remove(0);
                    f2List.remove(0);
                }else if(f2List.size() > 1 && f1List.get(0).equals(f2List.get(1))){
                    lines.add(new LineItem(Type.ADDED, f2List.get(0)));
                    f2List.remove(0);
                }else{
                    lines.add(new LineItem(Type.REMOVED, f1List.get(0)));
                    f1List.remove(0);
                }
            }
            while (f1List.size() > 0){
                lines.add(new LineItem(Type.REMOVED, f1List.get(0)));
                f1List.remove(0);
            }
            while (f2List.size() > 0){
                lines.add(new LineItem(Type.ADDED, f2List.get(0)));
                f2List.remove(0);
            }
            reader.close();
            f1reader.close();
            f2reader.close();
        } catch (Exception exc) {exc.printStackTrace();}
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
