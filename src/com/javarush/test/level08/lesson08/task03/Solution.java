package com.javarush.test.level08.lesson08.task03;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("цйу", "кенг");
        hmap.put("фыва", "пролдж");
        hmap.put("ячс", "мит");
        hmap.put("йфя", "цыч");
        hmap.put("увс", "кам");
        hmap.put("епи", "нрт");
        hmap.put("гоь", "шлб");
        hmap.put("йыс", "цвм");
        hmap.put("уам", "каи");
        hmap.put("епт", "нрь");
        return hmap;

    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        //напишите тут ваш код
        int count = 0;
        for (Map.Entry check : map.entrySet())
            if (check.getValue().equals(name))
                count++;
        return count;

    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName)
    {
        //напишите тут ваш код
        int count = 0;
        for (Map.Entry check : map.entrySet())
            if (check.getKey().equals(lastName))
                count++;
        return count;

    }
}
