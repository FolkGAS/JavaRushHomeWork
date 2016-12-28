package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {

        try {
            FileInputStream fis = null;
            FileOutputStream fos = null;
            if (args.length != 3) return;
            if ("-e".equals(args[0])) {
                fis = new FileInputStream(args[1]);
                fos = new FileOutputStream(args[2]);
                while (fis.available() > 0)
                    fos.write(Byte.valueOf((byte)fis.read())+1);
                fis.close();
                fos.close();
            }
            else if ("-d".equals(args[0])) {
                fis = new FileInputStream(args[1]);
                fos = new FileOutputStream(args[2]);
                while (fis.available() > 0)
                    fos.write(Byte.valueOf((byte)fis.read())-1);
                fis.close();
                fos.close();
            }
            else return;
        } catch (IOException exc) {exc.printStackTrace();}
    }

}
