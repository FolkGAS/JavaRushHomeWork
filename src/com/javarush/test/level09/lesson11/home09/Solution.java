package com.javarush.test.level09.lesson11.home09;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap()
    {
        //напишите тут ваш код
        HashMap<String, Cat> map = new HashMap<String, Cat>();
        map.put("qwe", new Cat("qaz"));
        map.put("asd", new Cat("wsx"));
        map.put("zxc", new Cat("edc"));
        map.put("tyu", new Cat("rfv"));
        map.put("ghj", new Cat("tgb"));
        map.put("vbn", new Cat("yhn"));
        map.put("iop", new Cat("ujm"));
        map.put("jkl", new Cat("ikl"));
        map.put("bnm", new Cat("pol"));
        map.put("pol", new Cat("rfd"));
        return map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map)
    {
        //напишите тут ваш код
        HashSet set = new HashSet();
        for (Map.Entry m : map.entrySet())
            set.add(m.getValue());
        return set;
    }

    public static void printCatSet(Set<Cat> set)
    {
        for (Cat cat:set)
        {
            System.out.println(cat);
        }
    }

    public static class Cat
    {
        private String name;

        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }


}
