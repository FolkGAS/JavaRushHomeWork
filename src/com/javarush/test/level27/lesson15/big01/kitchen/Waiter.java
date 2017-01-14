package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.Restaurant;

import java.util.concurrent.LinkedBlockingQueue;

public class Waiter implements Runnable
{
    private LinkedBlockingQueue<Order> queue;
    private LinkedBlockingQueue<Order> readyQueue;

    public void takeReadyOrder(Object order)
    {
        ConsoleHelper.writeMessage(order + " was cooked by " + ((Order)order).getCook());
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
        while (Restaurant.isWorking() || !queue.isEmpty() || !readyQueue.isEmpty())
        {
            if (!readyQueue.isEmpty()){
                try
                {
                    takeReadyOrder(readyQueue.take());
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }
            else
                while (readyQueue.isEmpty())
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
