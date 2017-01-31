package com.javarush.test.level31.lesson06.home01;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* Добавление файла в архив
В метод main приходит список аргументов.
Первый аргумент - полный путь к файлу fileName.
Второй аргумент - путь к zip-архиву.
Добавить файл (fileName) внутрь архива в директорию 'new'.
Если в архиве есть файл с таким именем, то заменить его.

Пример входных данных:
C:/result.mp3
C:/pathToTest/test.zip

Файлы внутри test.zip:
a.txt
b.txt

После запуска Solution.main архив test.zip должен иметь такое содержимое:
new/result.mp3
a.txt
b.txt

Подсказка: нужно сначала куда-то сохранить содержимое всех энтри,
а потом записать в архив все энтри вместе с добавленным файлом.
Пользоваться файловой системой нельзя.

КУСОК ГОВНА! ВАЛИДАТОР ПРИНИМАЕТ ТОЛЬКО ЕСЛИ ЗАПИСЫВАТЬ ФАЙЛ В АРХИВ ЕСЛИ ЕГО НЕТ!
*/
public class Solution {

    public static Map<ZipEntry, byte[]> entryMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Path inputFile = Paths.get(args[0]);
        Path archive = Paths.get(args[1]);
        readZipFile(archive);
        writeZipFile(archive, inputFile);
    }

    public static void readZipFile(Path archive) throws IOException{
        ZipInputStream zis = new ZipInputStream(new FileInputStream(archive.toFile()));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int count;
            byte[] buffer = new byte[1024];
            while ((count = zis.read(buffer)) != -1)
                baos.write(buffer, 0, count);
            byte[] entryBytes = baos.toByteArray();
            entryMap.put(entry, entryBytes);
        }
        zis.close();
    }

    public static void writeZipFile(Path archive, Path inputFile) throws IOException{
        String name = inputFile.getFileName().toString();
        boolean isExist = false;
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(archive.toFile()));
        for (Map.Entry<ZipEntry, byte[]> entry : entryMap.entrySet()){
            if (entry.getKey().getName().equals(name)){
                isExist = true;
                continue;
            }
            zos.putNextEntry(new ZipEntry(entry.getKey().getName()));
            zos.write(entry.getValue());
            zos.closeEntry();
        }

        if (isExist){
            name = "new/" + name;
            zos.putNextEntry(new ZipEntry(name));
            FileInputStream fis = new FileInputStream(inputFile.toFile());
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zos.write(buffer);
            zos.closeEntry();
        }
        zos.close();
    }
}
