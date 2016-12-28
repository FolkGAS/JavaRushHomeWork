package com.javarush.test.level08.lesson08.task04;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", new Date("JUNE 1 1980"));
        map.put("Stallione", new Date("JULY 1 1980"));
        map.put("Stalin", new Date("MAY 1 1920"));
        map.put("Still", new Date("OCTOBER 21 1990"));
        map.put("Stone", new Date("SEPTEMBER 11 1880"));
        map.put("Sione", new Date("DECEMBER 22 1953"));
        map.put("Se", new Date("JANUARY 1 2000"));
        map.put("Stalle", new Date("APRIL 4 1945"));
        map.put("Staillione", new Date("NOVEMBER 26 2086"));
        map.put("Stallie", new Date("MARCH 23 1985"));

        //напишите тут ваш код
        return map;
   }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        //напишите тут ваш код
        Iterator<Map.Entry<String, Date>> itt = map.entrySet().iterator();
        while (itt.hasNext())
        {
            int i = itt.next().getValue().getMonth();
            if (i > 4 && i < 8)
                itt.remove();
        }
    }
}
