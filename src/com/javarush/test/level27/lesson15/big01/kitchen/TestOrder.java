package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class TestOrder extends Order
{
    public TestOrder(Tablet tablet) throws IOException
    {
        super(tablet);
    }

    @Override
    protected void initDishes()
    {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        dishes = new ArrayList<>();
        for (int i = 0; i < Dish.values().length; i++){
            int j = random.nextInt(0, Dish.values().length - 1);
            if (j >= Dish.values().length)
                j = Dish.values().length - 1;
            dishes.add(Dish.values()[j]);
        }
    }

}
