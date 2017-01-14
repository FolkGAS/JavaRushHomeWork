package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper
{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder()throws IOException{
        List<Dish> dishList = new ArrayList<>();
        Dish[] dishes = Dish.values();
        boolean isAddedDish = false;
        String choice;
        writeMessage(Dish.allDishesToString());
        while (!(choice = readString()).equalsIgnoreCase("exit"))
        {
            isAddedDish = false;
            for (Dish dish : dishes)
                if (dish.name().equals(choice)){
                    dishList.add(dish);
                    isAddedDish = true;
                    break;
                }
            if (!isAddedDish)
                writeMessage(choice + " is not detected");
        }
        return dishList;
    }
}
