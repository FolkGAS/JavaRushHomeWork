package com.javarush.test.level08.lesson11.home06;

/* Вся семья в сборе
1. Создай класс Human с полями имя (String), пол (boolean), возраст (int), дети (ArrayList<Human>).
2. Создай объекты и заполни их так, чтобы получилось: два дедушки, две бабушки, отец, мать, трое детей.
3. Вывести все объекты Human на экран.
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Human child1 = new Human("ik,mjuyh", true, 22, new ArrayList());
        Human child2 = new Human("k,opijjoij", false, 20, new ArrayList());
        Human child3 = new Human("pkjmjijjrd", true, 10, new ArrayList());

        ArrayList<Human> child = new ArrayList<Human>();
        Collections.addAll(child, child1, child2, child3);
        Human father = new Human("piooi", true, 44, child);
        Human mother = new Human("hybyb", true, 33, child);

        ArrayList<Human> parents = new ArrayList<Human>();
        Collections.addAll(parents, father, mother);
        Human gF1 = new Human("qwed", true, 90, parents);
        Human gF2 = new Human("fgr", true, 80, parents);
        Human gM1 = new Human("vb", false, 88, parents);
        Human gM2 = new Human("lk", false, 77, parents);

        System.out.println(gF1);
        System.out.println(gF2);
        System.out.println(gM1);
        System.out.println(gM2);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);
    }

    public static class Human
    {
        //напишите тут ваш код
        String name = "";
        boolean sex = true;
        int age = 0;
        ArrayList<Human> children;
        Human(String name, boolean sex, int age, ArrayList children)
        {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0)
            {
                text += ", дети: "+this.children.get(0).name;

                for (int i = 1; i < childCount; i++)
                {
                    Human child = this.children.get(i);
                    text += ", "+child.name;
                }
            }

            return text;
        }
    }

}
