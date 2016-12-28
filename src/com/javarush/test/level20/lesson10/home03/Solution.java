package com.javarush.test.level20.lesson10.home03;

import java.io.*;

/* Найти ошибки
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найдите проблему и исправьте ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Сигнатура класса В не содержит ошибку :)
Метод main не участвует в тестировании.
*/
public class Solution implements Serializable {
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        Solution s = new Solution();
        B b = s.new B("b");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(b);
        byte[] bbb = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bbb);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        B bb = (B)objectInputStream.readObject();
        System.out.println(b.name);
        System.out.println(bb.name);
    }
    public static class A {
        protected String name = "A";
        public A(){}

        public A(String name) {
            this.name += name;
        }
    }

    public class B extends A implements Serializable {
        public String name = super.name;
        public B(String name) {
            super(name);
            this.name += name;
        }
    }
}
