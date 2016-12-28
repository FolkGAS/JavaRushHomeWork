package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        String htmlString = "", tag = args[0],
                openTag = "<" + tag + ">|<" + tag + "\\s", closeTag = "</" + args[0] + ">";
        ArrayList<Integer> open = new ArrayList<>();
        ArrayList<Integer> close = new ArrayList<>();
        TreeMap<Integer, Integer> result = new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader htmlReader = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())));
            while(htmlReader.ready())
                htmlString += htmlReader.readLine();
            reader.close();
            htmlReader.close();
        }catch (IOException exc) {exc.printStackTrace();}
        Pattern patternOp = Pattern.compile(openTag);
        Pattern patternCl = Pattern.compile(closeTag);
        Matcher matcherOp = patternOp.matcher(htmlString);
        Matcher matcherCl = patternCl.matcher(htmlString);
        while (matcherOp.find())
            open.add(matcherOp.start());
        while (matcherCl.find())
            close.add(matcherCl.start());
        for (int cl : close)
            for (int op = open.size() - 1; op >= 0; op--)
                if (cl - open.get(op) > 0){
                    result.put(open.get(op), cl);
                    open.remove(op);
                    break;
        }
        for (Map.Entry<Integer, Integer> pair : result.entrySet())
            System.out.println(htmlString.substring(pair.getKey(), pair.getValue() + closeTag.length()));
    }
}