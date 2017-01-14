package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Tablet;
import java.io.IOException;
import java.util.List;

public class Order
{
    private Cook cook;
    private Tablet tablet;
    protected List<Dish> dishes;
    public Order(Tablet tablet) throws IOException
    {
        this.tablet = tablet;
        initDishes();
    }

    public boolean isEmpty(){
        return dishes.isEmpty();
    }

    public int getTotalCookingTime(){
        int timeForCooking = 0;
        for (Dish dish : dishes)
            timeForCooking += dish.getDuration();
        return timeForCooking;
    }

    @Override
    public String toString(){
        if (dishes.isEmpty())
            return "";
        return "Your order: " + dishes.toString() + " of " + tablet.toString();
    }

    public List<Dish> getDishes()
    {
        return dishes;
    }

    protected void initDishes() throws IOException{
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Tablet getTablet()
    {
        return tablet;
    }

    public Cook getCook()
    {
        return cook;
    }

    public void setCook(Cook cook)
    {
        this.cook = cook;
    }
}
