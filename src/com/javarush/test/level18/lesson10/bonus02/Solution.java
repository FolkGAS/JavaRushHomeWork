package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        String sId;
        int id, idMax = 0;
        int[] len = {2, 30, 8, 4};
        if (!"-c".equals(args[0]) || args.length != 4)
            return;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();
            BufferedReader reader2 = new BufferedReader(new FileReader(fileName));
            while (reader2.ready()) {
                sId = reader2.readLine();
                if (sId.equals(""))
                    sId = "0       ";
                sId = sId.substring(0, 8);
                if (sId.contains(" "))
                    sId = sId.substring(0, sId.indexOf(" "));
                id = Integer.parseInt(sId);
                idMax = (idMax < id) ? id : idMax;
            }
            reader2.close();
            sId = String.valueOf(idMax+1);
            sId = cutOrFillToSize(sId, 8);
            for (int i = 1; i < 4; i++)
                args[i] = cutOrFillToSize(args[i], len[i]);
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(sId + args[1] + args[2] + args[3] + "\n");
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
