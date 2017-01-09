package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class DepositCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage((String) res.getObject("before"));
        String code = ConsoleHelper.askCurrencyCode();
        String[] money = null;
        while (money == null){
            try{
                money = ConsoleHelper.getValidTwoDigits(code);
            }catch (NumberFormatException exc)
            {
                money = null;
                ConsoleHelper.writeMessage((String) res.getObject("invalid.data"));
            }
        }
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        manipulator.addAmount(Integer.parseInt(money[0]), Integer.parseInt(money[1]));
        ConsoleHelper.writeMessage(String.format((String) res.getObject("success.format"), (Integer.parseInt(money[0]) * Integer.parseInt(money[1])), code));
        ConsoleHelper.writeMessage("");

    }
}
