package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        String sId, str;
        int id, idMax = 0, count = -1;
        int[] len = {2, 8, 30, 8, 4};
        ArrayList<String> list = new ArrayList<>();
        if (!"-u".equals(args[0]) && !"-d".equals(args[0]) && args.length < 2)
            return;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();
            BufferedReader reader2 = new BufferedReader(new FileReader(fileName));
            while (reader2.ready()) {
                str = sId = reader2.readLine();
                count++;
                list.add(sId);
                if (sId.equals(""))
                    sId = "0       ";
                sId = sId.substring(0, 8);
                if (sId.contains(" "))
                    sId = sId.substring(0, sId.indexOf(" "));
                if (args[1].equals(sId))
                {
                    if ("-d".equals(args[0]))
                        list.remove(count);
                    else if ("-u".equals(args[0])) {
                        for (int i = 1; i < 5; i++)
                            args[i] = cutOrFillToSize(args[i], len[i]);
                        list.remove(count);
                        list.add(count, args[1] + args[2] + args[3] + args[4]);
                    }
                }
            }
            reader2.close();
            FileWriter writer = new FileWriter(fileName);
            for (String st :list)
                writer.write(st + "\n");
            writer.flush();
            writer.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }
    static String cutOrFillToSize (String str, int len) {
        if (str.length() > len)
            str = str.substring(0, len);
        for (int i = str.length(); i < len; i++)
            str += " ";
        return str;
    }
}
