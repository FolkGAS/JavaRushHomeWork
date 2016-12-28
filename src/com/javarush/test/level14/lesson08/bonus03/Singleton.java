package com.javarush.test.level14.lesson08.bonus03;

public class Singleton
{
    static Singleton singleton;

    private Singleton()
    {
    }

    public static Singleton getInstance()
    {
        if (singleton == null)
            singleton = new Singleton();
        return  singleton;
    }
}
