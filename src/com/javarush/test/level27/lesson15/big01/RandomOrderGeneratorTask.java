package com.javarush.test.level27.lesson15.big01;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable
{
    private List<Tablet> tablets = new ArrayList<>();
    private int orderCreatingInterval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval)
    {
        this.tablets = tablets;
        this.orderCreatingInterval = interval;
    }

    @Override
    public void run()
    {
        Tablet tablet = null;
        if(tablets.isEmpty()) return;
        while (Restaurant.isWorking())
        {
            int i = (int)(Math.random()*tablets.size());
            if (i >= tablets.size())
                i = tablets.size() - 1;
            tablet = tablets.get(i);
            tablet.createTestOrder();
            try
            {
                Thread.sleep(orderCreatingInterval);
            }
            catch (InterruptedException exc){
                Thread.currentThread().interrupt();
            }
        }

    }
}
