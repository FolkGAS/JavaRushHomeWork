package com.javarush.test.level08.lesson03.task02;

/* HashMap из 10 пар
Создать коллекцию HashMap<String, String>, занести туда 10 пар строк:
арбуз - ягода, банан - трава, вишня - ягода, груша - фрукт, дыня - овощ, ежевика - куст, жень-шень - корень, земляника - ягода, ирис - цветок, картофель - клубень.
Вывести содержимое коллекции на экран, каждый элемент с новой строки.
Пример вывода (тут показана только одна строка):
картофель - клубень
*/

import java.util.HashMap;
import java.util.Map;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //напишите тут ваш код
        HashMap<String, String> ha = new HashMap<String, String>();

        ha.put("арбуз", "ягода");
        ha.put("банан", "трава");
        ha.put("вишня", "ягода");
        ha.put("груша", "фрукт");
        ha.put("дыня", "овощ");
        ha.put("ежевика", "куст");
        ha.put("жень-шень", "корень");
        ha.put("земляника", "ягода");
        ha.put("ирис", "цветок");
        ha.put("картофель", "клубень");

        for (Map.Entry<String, String> en : ha.entrySet())
        {
            String key = en.getKey();
            String value = en.getValue();
            System.out.println(key + " - " + value);
        }

    }
}
