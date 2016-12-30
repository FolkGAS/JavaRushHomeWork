package com.javarush.test.level22.lesson13.task02;

import java.io.*;
import java.nio.charset.Charset;

/* Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        try(FileInputStream fis =  new FileInputStream(args[0]);
            FileOutputStream fos = new FileOutputStream(args[1]))
        {
            while (fis.available() > 0){
                byte[] b = new byte[fis.available()];
                fis.read(b);
                String str = new String(b, Charset.forName("UTF-8"));
                b = str.getBytes(Charset.forName("Windows-1251"));
                fos.write(b);
            }
        }catch (IOException exc){exc.printStackTrace();}
    }
}
