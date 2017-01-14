package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Cook;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant
{
    private static boolean working = true;
    private static final int ORDER_CREATING_INTERVAL = 400;
    private static final LinkedBlockingQueue<Order> QUEUE = new LinkedBlockingQueue<>();
    private static final LinkedBlockingQueue<Order> READY_QUEUE = new LinkedBlockingQueue<>();
    public static void main(String[] args)
    {
        List<Tablet> tablets = new ArrayList<>();
        List<Cook> cooks = new ArrayList<>();
        List<Waiter> waiters = new ArrayList<>();
        String[] cookNames = {"Silver", "Shurf"};

        for (int i = 1; i <=5; i++){
            Tablet tablet = new Tablet(i);
            tablet.setQueue(QUEUE);
            tablets.add(tablet);
        }

        for (int i = 0; i < 2; i++){
            Waiter waiter = new Waiter();
            waiter.setQueue(QUEUE);
            waiter.setReadyQueue(READY_QUEUE);
            Thread runWaiter = new Thread(waiter);
            runWaiter.start();
        }

        for (int i = 0; i < cookNames.length; i++){
            Cook cook = new Cook(cookNames[i]);
            cook.setQueue(QUEUE);
            cook.setReadyQueue(READY_QUEUE);
            Thread runCook = new Thread(cook);
            runCook.start();
        }

            Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
            thread.start();
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        working = false;
        ConsoleHelper.writeMessage("\n+++++++++++++++++++++++++++++++++++++++++++");
        ConsoleHelper.writeMessage("\nWe are closing. See you later!\n");
        ConsoleHelper.writeMessage("+++++++++++++++++++++++++++++++++++++++++++\n");

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();

    }

    public static boolean isWorking()
    {
        return working;
    }
}
