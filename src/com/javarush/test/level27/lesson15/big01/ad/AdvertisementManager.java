package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;
import com.javarush.test.level27.lesson15.big01.statistic.StatisticEventManager;
import com.javarush.test.level27.lesson15.big01.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.test.level27.lesson15.big01.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;
    private long[][] costs;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void getMaxExpensiveAd(List<Integer> avl, List<Advertisement> videos, int k, int s){
        if (k <= 0 || s <= 0) return;
        if (costs[k][s] == 0) return;
        if (costs[k][s] == costs[k - 1][s])
            getMaxExpensiveAd(avl, videos, k - 1, s);
        else{
            getMaxExpensiveAd(avl, videos, k - 1, s - videos.get(k - 1).getDuration());
            avl.add(k - 1);
        }
    }

    public void processVideos(){
        boolean foundMax = false;
        long amount = 0;
        int totalDuration = 0;
        ArrayList<Integer> avl = new ArrayList<>();
        List<Advertisement> videos = new ArrayList<>();
        for (Advertisement avd : storage.list())
            if (avd.getDuration() <= timeSeconds && avd.getHits() > 0)
                videos.add(avd);

        List<Advertisement> available = new ArrayList<>();
        long max = 0;

        costs = new long[videos.size() + 1][timeSeconds + 1];
        for (int k = 1; k <= videos.size(); k++)
            for (int s= 1; s <= timeSeconds; s++)
                if (s >= videos.get(k - 1).getDuration())
                    costs[k][s] = Math.max (costs[k - 1][s], costs[k - 1][s - videos.get(k - 1).getDuration()] +
                            videos.get(k - 1).getAmountPerOneDisplaying());
                else costs[k][s] = costs[k - 1][s];

        for (long[] l : costs)
            for (long ll : l)
                max = max < ll ? ll : max;

        for (int j = timeSeconds; j > 0 && !foundMax; j--)
            for (int i = 1; i <= videos.size() && !foundMax; i++)
                if (costs[i][j] == max){
                    getMaxExpensiveAd(avl, videos, i, j);
                    foundMax = true;
        }

        for (Integer i : avl)
                available.add(videos.get(i));
        avl = null;

        if (available.isEmpty()) {
            StatisticEventManager.getInstance().register(new NoAvailableVideoEventDataRow(timeSeconds));
            throw new NoVideoAvailableException();
        }

        Collections.sort(available, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                int compare = Long.compare(o2.getAmountPerOneDisplaying(), o1.getAmountPerOneDisplaying());
                if (compare == 0){
                    long costPerSecond1 = o1.getAmountPerOneDisplaying()*10000/o1.getDuration();
                    long costPerSecond2 = o2.getAmountPerOneDisplaying()*10000/o1.getDuration();
                    compare = Long.compare(costPerSecond1, costPerSecond2);
                }
                return compare;
            }
        });

        for (Advertisement advert : available){
            amount += advert.getAmountPerOneDisplaying();
            totalDuration += advert.getDuration();
        }

        StatisticEventManager.getInstance().register(new VideoSelectedEventDataRow(available, amount, totalDuration));
        for (Advertisement advert : available){
            advert.revalidate();
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
                    advert.getName(), advert.getAmountPerOneDisplaying() ,
                    (advert.getAmountPerOneDisplaying()*1000/advert.getDuration())));
        }

    }
}