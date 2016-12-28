package com.javarush.test.level07.lesson12.home06;

/* Семья
Создай класс Human с полями имя(String), пол(boolean),возраст(int), отец(Human), мать(Human). Создай объекты и заполни их так, чтобы получилось: Два дедушки, две бабушки, отец, мать, трое детей. Вывести объекты на экран.
Примечание:
Если написать свой метод String toString() в классе Human, то именно он будет использоваться при выводе объекта на экран.
Пример вывода:
Имя: Аня, пол: женский, возраст: 21, отец: Павел, мать: Катя
Имя: Катя, пол: женский, возраст: 55
Имя: Игорь, пол: мужской, возраст: 2, отец: Михаил, мать: Аня
…
*/


public class Solution
{
    public static void main(String[] args)
    {
        //напишите тут ваш код
        Human gF1 = new Human();
        gF1.name = "qwe";
        gF1.sex = true;
        gF1.age = 99;
        
        Human gF2 = new Human();
        gF2.name = "rty";
        gF2.sex = true;
        gF2.age = 88;
        
        Human gM1 = new Human();
        gM1.name = "asd";
        gM1.sex = false;
        gM1.age = 90;
        
        Human gM2 = new Human();
        gM2.name = "fgh";
        gM2.sex = false;
        gM2.age = 80;
        
        Human father = new Human();
        father.name = "zxc";
        father.sex = true;
        father.age = 44;
        father.father = gF1;
        father.mother = gM1;
        
        Human mother = new Human();
        mother.name = "vbn";
        mother.sex = false;
        mother.age = 33;
        mother.father = gF2;
        mother.mother = gM2;
        
        Human son1 = new Human();
        son1.name = "uio";
        son1.sex = true;
        son1.age = 22;
        son1.father = father;
        son1.mother = mother;
        
        Human son2 = new Human();
        son2.name = "jkl";
        son2.sex = true;
        son2.age = 22;
        son2.father = father;
        son2.mother = mother;
        
        Human daughter = new Human();
        daughter.name = "m,.";
        daughter.sex = false;
        daughter.age = 20;
        daughter.father = father;
        daughter.mother = mother;

        System.out.println(gF1);
        System.out.println(gF2);
        System.out.println(gM1);
        System.out.println(gM2);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(son1);
        System.out.println(son2);
        System.out.println(daughter);
    }

    public static class Human
    {
        //напишите тут ваш код
        String name;
        boolean sex;
        int age;
        Human father;
        Human mother;

        public String toString()
        {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null)
                text += ", отец: " + this.father.name;

            if (this.mother != null)
                text += ", мать: " + this.mother.name;

            return text;
        }
    }

}
