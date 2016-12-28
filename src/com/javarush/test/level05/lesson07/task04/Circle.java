package com.javarush.test.level05.lesson07.task04;

/* Создать класс Circle
Создать класс (Circle) круг, с тремя инициализаторами:
- centerX, centerY, radius
- centerX, centerY, radius, width
- centerX, centerY, radius, width, color
*/

public class Circle
{
    private int x, y, r, d;
    private String color;

    public void initialize(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void initialize(int x, int y, int r, int d){
        this.x = x;
        this.y = y;
        this.r = r;
        this.d = d;
    }

    public void initialize(int x, int y, int r, int d, String color){
        this.x = x;
        this.y = y;
        this.r = r;
        this.d = d;
        this.color = color;
    }

    //напишите тут ваш код

}
