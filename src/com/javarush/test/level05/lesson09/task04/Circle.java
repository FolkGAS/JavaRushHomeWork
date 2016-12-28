package com.javarush.test.level05.lesson09.task04;

/* Создать класс Circle
Создать класс (Circle) круг, с тремя конструкторами:
- centerX, centerY, radius
- centerX, centerY, radius, width
- centerX, centerY, radius, width, color
*/

public class Circle
{
    public Circle(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Circle(int x, int y, int r, int d){
        this.x = x;
        this.y = y;
        this.r = r;
        this.d = d;
    }

    public Circle(int x, int y, int r, int d, String  color){
        this.x = x;
        this.y = y;
        this.r = r;
        this.d = d;
        this.color = color;
    }



    int x, y, r, d;
    String color;

    //напишите тут ваш код

}
