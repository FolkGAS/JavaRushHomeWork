package com.javarush.test.level19.lesson08.task05;

/* Дублируем текст
Считайте с консоли имя файла
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна дублировать вывод всего текста в файл, имя которого вы считали
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток
Закройте поток файла

Пример вывода на экран:
it's a text for testing

Пример тела файла:
it's a text for testing
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        String filename = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            filename = reader.readLine();
        } catch (IOException exc) {}
        PrintStream console = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(bos);
        System.setOut(printStream);
        System.setOut(printStream);
        testString.printSomething();
        System.setOut(console);
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            bos.writeTo(fos);
            bos.flush();
            bos.close();

            fos.close();
        } catch (IOException exc) {exc.printStackTrace();}
        System.out.println(bos.toString());
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

