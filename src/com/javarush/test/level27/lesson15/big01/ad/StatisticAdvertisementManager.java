package com.javarush.test.level27.lesson15.big01.ad;

import java.util.*;

public class StatisticAdvertisementManager
{
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance()
    {
        return ourInstance;
    }

    private StatisticAdvertisementManager()
    {
    }

    AdvertisementStorage storage = AdvertisementStorage.getInstance();

    public List<Advertisement> getActiveVideos(){
        List<Advertisement> activeVideos = new ArrayList<>();
        for (Advertisement adv : storage.list())
            if (adv.getHits() > 0)
                activeVideos.add(adv);

        Collections.sort(activeVideos, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return activeVideos;
    }

    public List<Advertisement> getUnactiveVideos(){
        List<Advertisement> unActiveVideos = new ArrayList<>();
        for (Advertisement adv : storage.list())
            if (adv.getHits() == 0)
                unActiveVideos.add(adv);

        Collections.sort(unActiveVideos, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        return unActiveVideos;
    }
}
