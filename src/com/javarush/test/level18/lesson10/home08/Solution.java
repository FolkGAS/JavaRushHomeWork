package com.javarush.test.level18.lesson10.home08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* Нити и байты
Читайте с консоли имена файлов, пока не будет введено слово "exit"
Передайте имя файла в нить ReadThread
Нить ReadThread должна найти байт, который встречается в файле максимальное число раз, и добавить его в словарь resultMap,
где параметр String - это имя файла, параметр Integer - это искомый байт.
Закрыть потоки. Не использовать try-with-resources
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();
    public static void main(String[] args) {
        String fileName = "";
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (!"exit".equals(fileName = reader.readLine()))
                if (!"".equals(fileName))
                    new ReadThread(fileName).start();
            reader.close();
        } catch (IOException exc) {exc.printStackTrace();}
    }

    public static class ReadThread extends Thread {
        String filename = "";
        public ReadThread(String fileName) {
            //implement constructor body
            this.filename = fileName;
        }
        public void run()
        {
            // implement file reading here - реализуйте чтение из файла тут
            try {
                int singleByte, max = 0;
                HashMap <Integer, Integer> bytes = new HashMap<>();
                FileInputStream fis = new FileInputStream(filename);
                while ((singleByte = fis.read()) != -1) {
                    if (bytes.containsKey(singleByte))
                        bytes.put(singleByte, bytes.get(singleByte)+1);
                    else bytes.put(singleByte, 1);
                }
                for (Map.Entry<Integer, Integer> pair : bytes.entrySet())
                    max = (max < pair.getValue()) ? pair.getValue() : max;
                for (Map.Entry<Integer, Integer> pair : bytes.entrySet())
                    if (pair.getValue() == max)
                        resultMap.put(filename, pair.getKey());
                fis.close();
            } catch (IOException exc) {exc.printStackTrace();}
        }
    }
}
