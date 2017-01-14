package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.Advertisement;
import com.javarush.test.level27.lesson15.big01.ad.StatisticAdvertisementManager;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class DirectorTablet
{
    public void printAdvertisementProfit(){
        long total = 0;
        Map<Date, Long> map = StatisticEventManager.getInstance().getAdDayPays();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (Map.Entry<Date, Long> day : map.entrySet()){
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f" , format.format(day.getKey()), day.getValue()/100.0));
            total += day.getValue();
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f\n" , total/100.0));
        total = 0;
    }
    public void printCookWorkloading(){
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<Date, Map<String, Integer>> mapMap = StatisticEventManager.getInstance().cookLoadPerDay();
        for (Map.Entry<Date, Map<String, Integer>> entry : mapMap.entrySet()){
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s" ,format.format(entry.getKey())));
            for (Map.Entry<String, Integer> cook : entry.getValue().entrySet())
                ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %d min" , cook.getKey(), (int)Math.ceil(cook.getValue()/60)));
            ConsoleHelper.writeMessage("");
        }
    }
    public void printActiveVideoSet(){
        for (Advertisement adv : StatisticAdvertisementManager.getInstance().getActiveVideos())
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %d" , adv.getName(), adv.getHits()));
        ConsoleHelper.writeMessage("");
    }
    public void printArchivedVideoSet(){
        for (Advertisement adv : StatisticAdvertisementManager.getInstance().getUnactiveVideos())
            ConsoleHelper.writeMessage(adv.getName());
        ConsoleHelper.writeMessage("");
    }
}
