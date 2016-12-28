package com.javarush.test.level17.lesson10.bonus01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* CRUD
CrUD - Create, Update, Delete
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id
Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c  - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u  - обновляет данные человека с данным id
-d  - производит логическое удаление человека с id
-i  - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке
Все люди должны храниться в allPeople
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat

Пример параметров: -c Миронов м 15/04/1990
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут

        if (args.length < 2 || args.length == 3 || args.length > 5) return;
        Date bd;
        switch (args[0]) {
            case "-c" :
                bd = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3]);
                if ("м".equals(args[2])) allPeople.add(Person.createMale(args[1], bd));
                else if ("ж".equals(args[2])) allPeople.add(Person.createFemale(args[1], bd));
                System.out.println(allPeople.size()-1);
                break;
            case "-u" :
                allPeople.get(Integer.parseInt(args[1])).setName(args[2]);
                allPeople.get(Integer.parseInt(args[1])).setSex("м".equals(args[3]) ? Sex.MALE : "ж".equals(args[3]) ? Sex.FEMALE :
                        allPeople.get(Integer.parseInt(args[1])).getSex());
                allPeople.get(Integer.parseInt(args[1])).setBirthDay(new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[4]));
                break;
            case "-d" :
                allPeople.get(Integer.parseInt(args[1])).setName(null);
                allPeople.get(Integer.parseInt(args[1])).setSex(null);
                allPeople.get(Integer.parseInt(args[1])).setBirthDay(null);
                break;
            case "-i" :
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                Person people = allPeople.get(Integer.parseInt(args[1]));
                System.out.println(people.getName() + " " + (people.getSex().equals(Sex.MALE) ? "м " : "ж ") + dateFormat.format(people.getBirthDay()));
                break;
            default:
                break;
        }
    }
}
