package com.javarush.test.level20.lesson10.home07;

import java.io.*;

/* Переопределение сериализации в потоке
Сериализация/десериализация Solution не работает.
Исправьте ошибки не меняя сигнатуры методов и класса.
Метод main не участвует в тестировании.
Написать код проверки самостоятельно в методе main:
1) создать экземпляр класса Solution
2) записать в него данные  - writeObject
3) сериализовать класс Solution  - writeObject(ObjectOutputStream out)
4) десериализовать, получаем новый объект
5) записать в новый объект данные - writeObject
6) проверить, что в файле есть данные из п.2 и п.5
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    String filename;

    public Solution(String fileName) throws FileNotFoundException {
        this.filename = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
//        out.close();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(filename, true);
//        in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }
    public static void main(String args[]){
        try {
            Solution solution = new Solution("c:\\111");
            solution.writeObject("Data");
            FileOutputStream fos = new FileOutputStream("c:\\222");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(solution);
            fos.close();
            oos.close();
            FileInputStream fis = new FileInputStream("c:\\222");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Solution sol = (Solution) ois.readObject();
            sol.writeObject("Info");
            fis.close();
            ois.close();
        }catch (IOException | ClassNotFoundException exc){exc.printStackTrace();}
    }
}
