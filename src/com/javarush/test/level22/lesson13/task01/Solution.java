package com.javarush.test.level22.lesson13.task01;

import java.util.ArrayList;
import java.util.StringTokenizer;

/* StringTokenizer
Используя StringTokenizer разделить query на части по разделителю delimiter.
Пример,
getTokens("level22.lesson13.task01", ".") == {"level22", "lesson13", "task01"}
*/
public class Solution {
    public static String [] getTokens(String query, String delimiter){
        ArrayList<String> arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(query, delimiter);
        while (st.hasMoreTokens())
            arr.add(st.nextToken());
        String[] str = new String[arr.size()];
        return arr.toArray(str);
    }
}
