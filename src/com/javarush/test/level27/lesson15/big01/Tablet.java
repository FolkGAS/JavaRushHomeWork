package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;
import com.javarush.test.level27.lesson15.big01.kitchen.TestOrder;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet
{
    private final int number;
    private LinkedBlockingQueue<Order> queue;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    public Tablet(int num){
        number = num;
    }

    public void createOrder(){
        Order order = null;
        try{
            order = new Order(this);
            createNewOrder(order);
        }catch (IOException exc){
            logger.log(Level.SEVERE, "Console is unavailable.");
        }catch (NoVideoAvailableException exc){
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
    }

    public void createTestOrder(){
        Order order = null;
        try{
            order = new TestOrder(this);
            createNewOrder(order);
        }catch (IOException exc){
            logger.log(Level.SEVERE, "Console is unavailable.");
        }catch (NoVideoAvailableException exc){
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
    }

    private void createNewOrder(Order order)
    {
        ConsoleHelper.writeMessage(order.toString());
        if (!order.isEmpty()){
            AdvertisementManager manager = new AdvertisementManager(order.getTotalCookingTime()*60);
            manager.processVideos();
            try
            {
                queue.put(order);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString()
    {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

    public void setQueue(LinkedBlockingQueue<Order> queue)
    {
        this.queue = queue;
    }
}
