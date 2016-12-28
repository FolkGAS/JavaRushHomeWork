package com.javarush.test.level18.lesson10.home09;

/* Файлы и исключения
Читайте с консоли имена файлов
Если файла не существует (передано неправильное имя файла), то
перехватить исключение FileNotFoundException, вывести в консоль переданное неправильное имя файла и завершить работу программы.
Закрыть потоки. Не использовать try-with-resources
Не используйте System.exit();
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        String fileName = "";
        BufferedReader reader = null;
        FileInputStream fis = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            while (!"".equals(fileName = reader.readLine()))
                fis = new FileInputStream(fileName);
            reader.close();
            fis.close();
        } catch (FileNotFoundException exc) {
            System.out.println(fileName);
        }
        catch (IOException exc) {exc.printStackTrace();}
        finally
        {
            try
            {
                if (reader != null) reader.close();
                if (fis != null) fis.close();
            } catch (IOException exc) {exc.printStackTrace();}
        }
    }
}
