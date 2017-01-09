package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator
{
    String currencyCode;
    Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void addAmount(int denomination, int count){
        if (denominations.containsKey(denomination))
            denominations.put(denomination, denominations.get(denomination) + count);
        else denominations.put(denomination, count);
    }

    public int getTotalAmount(){
        int money = 0;
        for (Map.Entry <Integer, Integer> pair : denominations.entrySet())
            money += pair.getKey() * pair.getValue();
        return money;
    }

    boolean hasMoney(){
        return getTotalAmount() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount){
        return expectedAmount <= getTotalAmount();
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException{
        Integer spent = expectedAmount;
        Map<Integer, Integer> sortDenom = new TreeMap<>(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return Integer.compare(o2, o1);
            }
        });
        sortDenom.putAll(denominations);

        Map<Integer, Integer> expect = new TreeMap<>(new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return Integer.compare(o2, o1);
            }
        });
        Integer value = null, count = null, max = 0;

        while (spent > 0 && sortDenom.size() > 0)
        {
            expect.clear();
            for (Map.Entry<Integer, Integer> denom : sortDenom.entrySet())
            {
                value = denom.getKey();
                count = denom.getValue();
                max = max < value ? value : max;
                for (int i = 0; i < count; i++)
                    if (spent - value >= 0 && count > 0 && value > 0)
                    {
                        spent -= value;
                        if (expect.containsKey(value))
                            expect.put(value, expect.get(value) + 1);
                        else expect.put(value, 1);
                    }
                    else break;
            }
            if (spent > 0){
                sortDenom.remove(max);
                spent = expectedAmount;
                max = 0;
            }
        }

        if (spent != 0)
            throw new NotEnoughMoneyException();
        else
        {
            for (Map.Entry<Integer, Integer> ex : expect.entrySet())
            {
                value = ex.getKey();
                count = ex.getValue();
                if (denominations.containsKey(value)){
                    denominations.put(value, denominations.get(value) - count);
                    if (denominations.get(value) < 0)
                        throw new NotEnoughMoneyException();
                    if (denominations.get(value) == 0)
                        denominations.remove(value);
                }else throw new NotEnoughMoneyException();
            }



        }
        return expect;
    }
}
