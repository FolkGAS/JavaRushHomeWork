package com.javarush.test.level07.lesson06.task01;

import java.util.ArrayList;

/* 5 различных строчек в списке
1. Создай список строк.
2. Добавь в него 5 различных строчек.
3. Выведи его размер на экран.
4. Используя цикл выведи его содержимое на экран, каждое значение с новой строки.
*/
public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        ArrayList<String> yarr = new ArrayList<>();
        yarr.add("YO");
        yarr.add("HO");
        yarr.add("HO!");
        yarr.add("YARR");
        yarr.add("!");
        System.out.println(yarr.size());
        for(String i : yarr)
            System.out.println(i);



    }
}
