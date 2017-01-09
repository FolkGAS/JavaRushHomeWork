package com.javarush.test.level26.lesson15.big01;

import java.util.Collection;
import java.util.HashMap;

public class CurrencyManipulatorFactory
{
    private CurrencyManipulatorFactory(){}

    public static HashMap<String, CurrencyManipulator> hmap = new HashMap();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        if (!hmap.containsKey(currencyCode))
            hmap.put(currencyCode, new CurrencyManipulator(currencyCode));
        return hmap.get(currencyCode);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()
    {return hmap.values();}
}
