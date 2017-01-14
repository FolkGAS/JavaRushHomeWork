package com.javarush.test.level27.lesson15.big01.statistic;

import com.javarush.test.level27.lesson15.big01.statistic.event.CookedOrderEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.EventType;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class StatisticEventManager
{
    private static StatisticEventManager ourInstance = new StatisticEventManager();

    public static StatisticEventManager getInstance()
    {
        return ourInstance;
    }

    private StatisticStorage storage = new StatisticStorage();

    private StatisticEventManager()
    {
    }

    public void register(EventDataRow data) {
        storage.put(data);
    }

    public Map<Date, Long> getAdDayPays(){
        List<EventDataRow> adv = storage.getMap().get(EventType.SELECTED_VIDEOS);
        Map<Date, Long> amount = new TreeMap<>(new Comparator<Date>()
        {
            @Override
            public int compare(Date o1, Date o2)
            {
                return o2.compareTo(o1);
            }
        });
        for (EventDataRow edr : adv){
            Date date = edr.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            date = cal.getTime();
            if (!amount.containsKey(date))
                amount.put(date, 0L);
            amount.put(date, amount.get(date) + ((VideoSelectedEventDataRow)edr).getAmount());
        }
        return amount;
    }

    public Map<Date, Map<String, Integer>> cookLoadPerDay(){
        List<EventDataRow> adv = storage.getMap().get(EventType.COOKED_ORDER);
        Map<Date, Map<String, Integer>> cookTimeWork = new TreeMap<>(new Comparator<Date>()
        {
            @Override
            public int compare(Date o1, Date o2)
            {
                return o2.compareTo(o1);
            }
        });
        for (EventDataRow edr : adv){
            String name = ((CookedOrderEventDataRow)edr).getCookName();
            int time = ((CookedOrderEventDataRow)edr).getTime();
            Date date = edr.getDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            date = cal.getTime();
            if (!cookTimeWork.containsKey(date))
                cookTimeWork.put(date, new TreeMap<String, Integer>());
            Map<String, Integer> cook = cookTimeWork.get(date);
            if (!cook.containsKey(name))
                cook.put(name, 0);
            cook.put(name, cook.get(name) + time);
        }
        return cookTimeWork;
    }

    private static class StatisticStorage{

        public Map<EventType, List<EventDataRow>> map = new HashMap<>();
        private StatisticStorage(){
            for (EventType eventType : EventType.values())
                map.put(eventType, new ArrayList<EventDataRow>());
        }

        private void put(EventDataRow data){
            map.get(data.getType()).add(data);
        }

        public Map<EventType, List<EventDataRow>> getMap()
        {
            return map;
        }
    }
}
