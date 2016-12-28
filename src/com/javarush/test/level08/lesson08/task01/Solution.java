package com.javarush.test.level08.lesson08.task01;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* 20 слов на букву «Л»
Создать множество строк (Set<String>), занести в него 20 слов на букву «Л».
*/

public class Solution
{
    public static HashSet<String> createSet()
    {
        //напишите тут ваш код
        HashSet<String> sstr = new HashSet<String>();
        Collections.addAll(sstr, "Лыба", "Лак", "Лолбаса", "Ладио", "Лабота", "Лавиатура", "Лышка", "Лонитор", "Лелевизор", "Лиск", "Лесня", "Лечь", "Лтол", "Лтул", "Локно", "Лветок", "Ллово", "Литор", "Лакароны", "Ляблоко");
        return sstr;
    }
}
