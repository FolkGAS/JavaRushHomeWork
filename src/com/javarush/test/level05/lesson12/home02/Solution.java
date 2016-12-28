package com.javarush.test.level05.lesson12.home02;

/* Man and Woman
1. Внутри класса Solution создай public static классы Man и Woman.
2. У классов должны быть поля: name(String), age(int), address(String).
3. Создай конструкторы, в которые передаются все возможные параметры.
4. Создай по два объекта каждого класса со всеми данными используя конструктор.
5. Объекты выведи на экран в таком формате [name + " " + age + " " + address].
*/

public class Solution
{
    public static void main(String[] args)
    {
        //создай по два объекта каждого класса тут
        Man man1 = new Man("Uasya", 22, "Bobruysk");
        Man man2 = new Man("Uasisualiy", 33, "Kaloproboysk");
        Woman woman1 = new Woman("Marusya", 14, "Loliyobsk");
        Woman woman2 = new Woman("Parasha", 41, "Govnomesevo");

        //выведи их на экран тут
        System.out.println(man1.name + " " + man1.age + " " + man1.address);
        System.out.println(man2.name + " " + man2.age + " " + man2.address);
        System.out.println(woman1.name + " " + woman1.age + " " + woman1.address);
        System.out.println(woman2.name + " " + woman2.age + " " + woman2.address);

    }

    //добавьте тут ваши классы
    public static class Man
    {
        public Man(String name)
        {
            this.name = name;
        }

        public Man(String name, int age)
        {
            this.name = name;
            this.age = age;
        }

        public Man(String name, int age, String adsress)
        {
            this.name = name;
            this.age = age;
            this.address = adsress;
        }

        public Man(int age, String adsress)
        {
            this.age = age;
            this.address = adsress;
        }

        public Man(String name, String adsress)
        {
            this.name = name;
            this.address = adsress;
        }

        public Man(Man newMan)
        {
            this.name = newMan.name;
            this.age = newMan.age;
            this.address = newMan.address;
        }
        String name, address;
        int age;
    }

    public static class Woman
    {
        public Woman(String name)
        {
            this.name = name;
        }

        public Woman(String name, int age)
        {
            this.name = name;
            this.age = age;
        }

        public Woman(String name, int age, String adsress)
        {
            this.name = name;
            this.age = age;
            this.address = adsress;
        }

        public Woman(int age, String adsress)
        {
            this.age = age;
            this.address = adsress;
        }

        public Woman(String name, String adsress)
        {
            this.name = name;
            this.address = adsress;
        }

        public Woman(Woman newWoman)
        {
            this.name = newWoman.name;
            this.age = newWoman.age;
            this.address = newWoman.address;
        }
        String name, address;
        int age;
    }


    
}
