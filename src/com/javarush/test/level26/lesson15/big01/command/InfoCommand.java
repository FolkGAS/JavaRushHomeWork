package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info_en");
    @Override
    public void execute()
    {
        int money = 0;
        Collection<CurrencyManipulator> map = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        ConsoleHelper.writeMessage((String) res.getObject("before"));
        for (CurrencyManipulator cm : map)
            money += cm.getTotalAmount();
        if (money == 0)
            ConsoleHelper.writeMessage((String) res.getObject("no.money"));
        for (CurrencyManipulator cm : map){
            money = cm.getTotalAmount();
            if (money > 0)
                System.out.println(cm.getCurrencyCode() + " - " + money);
        }
    }
}
