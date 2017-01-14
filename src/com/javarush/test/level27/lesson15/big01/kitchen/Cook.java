package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Restaurant;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;

import java.util.concurrent.LinkedBlockingQueue;

public class Cook implements Runnable
{
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;
    private LinkedBlockingQueue<Order> readyQueue;
    public Cook(String name){
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name ;
    }

    public void startCookingOrder(Order order){
        busy = true;
        order.setCook(this);
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + ((Order)order).getTotalCookingTime() + "min");
        StatisticEventManager.getInstance().register(new CookedOrderEventDataRow(((Order)order).getTablet().toString(),
                name, ((Order)order).getTotalCookingTime()*60, ((Order)order).getDishes()));
        try
        {
            Thread.sleep(((Order)order).getTotalCookingTime()*10);
        }
        catch (InterruptedException e){Thread.currentThread().interrupt();}
        try
        {
            readyQueue.put(order);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        busy = false;
    }

    public boolean isBusy()
    {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }

    public void setReadyQueue(LinkedBlockingQueue<Order> readyQueue)
    {
        this.readyQueue = readyQueue;
    }

    @Override
    public void run()
    {
        while (Restaurant.isWorking() || !queue.isEmpty())
        {
            if (!queue.isEmpty()){
                try
                {
                    startCookingOrder(queue.take());
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
            else
                while (queue.isEmpty())
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                    }
        }
    }
}
