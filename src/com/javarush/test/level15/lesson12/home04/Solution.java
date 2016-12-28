package com.javarush.test.level15.lesson12.home04;

/* Закрепляем Singleton pattern
1. Найти в гугле пример для - Singleton pattern Lazy initialization.
2. По образу и подобию в отдельных файлах создать три синглтон класса Sun, Moon, Earth.
3. Реализовать интерфейс Planet для классов Sun, Moon, Earth.
4. В статическом блоке класса Solution вызвать метод readKeyFromConsoleAndInitPlanet.
5. Реализовать функционал метода readKeyFromConsoleAndInitPlanet:
5.1. С консоли считать один параметр типа String.
5.2. Если параметр равен одной из констант интерфейса Planet, то создать соответствующий объект и присвоить его Planet thePlanet, иначе обнулить Planet thePlanet.
5.3. Сравнивать введенный параметр можно только с константами из Planet, нельзя создавать свои строки.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static Planet thePlanet;
    public static String item;

    //add static block here - добавьте статический блок тут
    static
    {
        readKeyFromConsoleAndInitPlanet();
    }

    public static void readKeyFromConsoleAndInitPlanet() {
        // implement step #5 here - реализуйте задание №5 тут

        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            item = reader.readLine();
            reader.close();
        }
        catch (IOException exc)
        {
            exc.printStackTrace();
        }
        if (Planet.SUN.equals(item))
            thePlanet = Sun.getInstance();
        else if (Planet.EARTH.equals(item))
            thePlanet = Earth.getInstance();
        else if (Planet.MOON.equals(item))
            thePlanet = Moon.getInstance();
        else thePlanet = null;
    }
}
