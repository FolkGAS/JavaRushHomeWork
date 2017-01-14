package com.javarush.test.level27.lesson15.big01.kitchen;

public enum Dish
{
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;
    Dish(int duration){
        this.duration = duration;
    }

    public int getDuration()
    {
        return duration;
    }

    public static String allDishesToString(){
        Dish[] dishes = values();
        if (dishes.length == 0) return "";
        String strDishes = "";
        for (Dish dish : dishes)
            strDishes += dish + ", ";
        strDishes = strDishes.substring(0, strDishes.length() - 2);
        return strDishes;
    }
}
