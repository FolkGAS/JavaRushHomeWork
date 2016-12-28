package com.javarush.test.level08.lesson08.task05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить людей, имеющих одинаковые имена
Создать словарь (Map<String, String>) занести в него десять записей по принципу «фамилия» - «имя».
Удалить людей, имеющих одинаковые имена.
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
        hmap.put("йфя", "пролдж");
        hmap.put("увс", "кам");
        hmap.put("епи", "каи");
        hmap.put("гоь", "шлб");
        hmap.put("йыс", "цвм");
        hmap.put("уам", "каи");
        hmap.put("епт", "пролдж");
        return hmap;
    }

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map)
    {
        //напишите тут ваш код
        Iterator<Map.Entry<String, String>> itt = map.entrySet().iterator();
        ArrayList<String> list = new ArrayList<String>();
        Map.Entry out, in;
        while (itt.hasNext())
        {
            Iterator<Map.Entry<String, String>> itt2 = map.entrySet().iterator();
            out = itt.next();
            while (itt2.hasNext())
            {
                in = itt2.next();
                if (!out.getKey().equals(in.getKey()) && !list.contains(in.getValue().toString()) && in.getValue().equals(out.getValue()))
                    list.add(in.getValue().toString());
            }
        }
        if (!list.isEmpty())
            for (int i = 0; i < list.size(); i++)
                removeItemFromMapByValue(map, list.get(i));
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value)
    {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair: copy.entrySet())
        {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }
}
