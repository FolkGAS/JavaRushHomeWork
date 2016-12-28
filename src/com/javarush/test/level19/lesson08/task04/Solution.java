package com.javarush.test.level19.lesson08.task04;

/* Решаем пример
В методе main подмените объект System.out написанной вами ридер-оберткой по аналогии с лекцией
Ваша ридер-обертка должна выводить на консоль решенный пример
Вызовите готовый метод printSomething(), воспользуйтесь testString
Верните переменной System.out первоначальный поток

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream console = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
        testString.printSomething();
        System.setOut(console);
        String result = calc(baos.toString());
        System.out.println(result);
    }

    public static String calc (String str) {
        String res = "";
        String[] temp = new String[5];
        temp = str.split(" ");
        int a = Integer.valueOf(temp[0]);
        int b = Integer.valueOf(temp[2]);
        char op =temp[1].charAt(0);
        switch (op)
        {
            case '+': res =  String.valueOf(a) + " + " + String.valueOf(b) + " = " + String.valueOf(a + b); break;
            case '-': res =  String.valueOf(a) + " - " + String.valueOf(b) + " = " + String.valueOf(a - b); break;
            case '*': res =  String.valueOf(a) + " * " + String.valueOf(b) + " = " + String.valueOf(a * b); break;
            default: res = "";
        }
        return res;
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

