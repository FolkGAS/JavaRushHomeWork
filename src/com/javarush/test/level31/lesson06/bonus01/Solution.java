package com.javarush.test.level31.lesson06.bonus01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.ZipInputStream;

/* Разархивируем файл
В метод main приходит список аргументов.
Первый аргумент - имя результирующего файла resultFileName, остальные аргументы - имена файлов fileNamePart.
Каждый файл (fileNamePart) - это кусочек zip архива. Нужно разархивировать целый файл, собрав его из кусочков.
Записать разархивированный файл в resultFileName.
Архив внутри может содержать файл большой длины, например, 50Mb.
Внутри архива может содержаться файл с любым именем.

Пример входных данных. Внутри архива находится один файл с именем abc.mp3:
C:/result.mp3
C:/pathToTest/test.zip.003
C:/pathToTest/test.zip.001
C:/pathToTest/test.zip.004
C:/pathToTest/test.zip.002
*/
public class Solution {
    public static void main(String[] args) throws Exception{
        String tempFile = args[0] + ".zip";
        ArrayList<String> zipParts = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(args, 1, args.length)));
        Collections.sort(zipParts);
        FileOutputStream fosTemp = new FileOutputStream(tempFile);
        for (String partName : zipParts){
            FileInputStream fis = new FileInputStream(partName);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fosTemp.write(buffer);
            fosTemp.flush();
        }
        fosTemp.close();
        ZipInputStream zis = new ZipInputStream(new FileInputStream(tempFile));
        zis.getNextEntry();
        FileOutputStream fos = new FileOutputStream(args[0]);
        int count;
        byte[] buffer = new byte[1024*1000];
        while ((count = zis.read(buffer)) != -1){
            fos.write(buffer, 0, count);
            fos.flush();
        }
        zis.close();
        Files.delete(Paths.get(tempFile));
        fos.close();
    }
}
