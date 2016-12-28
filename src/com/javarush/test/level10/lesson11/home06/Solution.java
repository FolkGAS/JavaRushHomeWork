package com.javarush.test.level10.lesson11.home06;

/* Конструкторы класса Human
Напиши класс Human с 6 полями. Придумай и реализуй 10 различных конструкторов для него. Каждый конструктор должен иметь смысл.
*/

public class Solution
{
    public static void main(String[] args)
    {

    }

    public static class Human
    {
        //напишите тут ваши переменные и конструкторы
        String name, address;
        int age, weight, height;
        boolean sex;

        Human()
        {
            this.name = "";
            this.address = "";
            this.age = 0;
            this.weight = 0;
            this.height = 0;
            this.sex = true;
        }

        Human(Human human)
        {
            this.name = human.name;
            this.address = human.address;
            this.age = human.age;
            this.weight = human.weight;
            this.height = human.height;
            this.sex = human.sex;
        }

        Human(String name, int weight, String address, boolean sex, int age)
        {
            this.name = name;
            this.address = address;
            this.age = age;
            this.weight = weight;
            this.height = 0;
            this.sex = sex;
        }

        Human(String name, int weight, String address, boolean sex)
        {
            this.name = name;
            this.address = address;
            this.age = 0;
            this.weight = weight;
            this.height = 0;
            this.sex = sex;
        }

        Human(String name, int weight, String address, int age, int height)
        {
            this.name = name;
            this.address = address;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.sex = true;
        }

        Human(String name, int weight, boolean sex, int age, int height)
        {
            this.name = name;
            this.address = "";
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.sex = sex;
        }

        Human(String name, boolean sex, int age, int height)
        {
            this.name = name;
            this.address = "";
            this.age = age;
            this.weight = 0;
            this.height = height;
            this.sex = sex;
        }

        Human(String name, String address, boolean sex, int age, int height)
        {
            this.name = name;
            this.address = address;
            this.age = age;
            this.weight = 0;
            this.height = height;
            this.sex = sex;
        }

        Human(int weight, String address, boolean sex, int age, int height)
        {
            this.name = "";
            this.address = address;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.sex = sex;
        }

        Human(String name, int weight, String address, boolean sex, int age, int height)
        {
            this.name = name;
            this.address = address;
            this.age = age;
            this.weight = weight;
            this.height = height;
            this.sex = sex;
        }
    }
}
