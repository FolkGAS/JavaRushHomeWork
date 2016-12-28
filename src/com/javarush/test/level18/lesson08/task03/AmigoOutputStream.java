package com.javarush.test.level18.lesson08.task03;

import java.io.*;

/* AmigoOutputStream
1 Измените класс AmigoOutputStream так, чтобы он стал Wrapper-ом для класса FileOutputStream. Используйте наследование.
2 При вызове метода close() должны выполняться следующая последовательность действий:
2.1 вызвать метод flush()
2.2 дописать следующий текст [JavaRush © 2012-2013 All rights reserved.], используйте метод getBytes()
2.3 закрыть поток методом close()
*/

public class AmigoOutputStream extends FileOutputStream{
    public static String fileName = "C:/tmp/result.txt";
    private FileOutputStream fos;

    public AmigoOutputStream(String name) throws FileNotFoundException
    {
        super(name);
    }

    public AmigoOutputStream(String name, boolean append) throws FileNotFoundException
    {
        super(name, append);
    }

    public AmigoOutputStream(File file) throws FileNotFoundException
    {
        super(file);
    }

    public AmigoOutputStream(File file, boolean append) throws FileNotFoundException
    {
        super(file, append);
    }

    public AmigoOutputStream(FileDescriptor fdObj)
    {
        super(fdObj);
    }

    public AmigoOutputStream (FileOutputStream fos) throws FileNotFoundException{
        super(fileName);
        this.fos = fos;
    }

    @Override
    public void close() throws IOException
    {
        fos.flush();
        fos = new FileOutputStream(fileName, true);
        fos.write("JavaRush © 2012-2013 All rights reserved.".getBytes());
        fos.close();
    }

    public static void main(String[] args) throws IOException {
        new AmigoOutputStream(new FileOutputStream(fileName));
    }

}
