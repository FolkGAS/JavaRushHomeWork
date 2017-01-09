package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        String money = null;
        boolean reenter = true;
        Map<Integer, Integer> withdraw = null;
        CurrencyManipulator manipulator = null;
        ConsoleHelper.writeMessage((String) res.getObject("before"));
        while (manipulator == null || manipulator.getTotalAmount() == 0)
        {
            String code = ConsoleHelper.askCurrencyCode();
            manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        }
        ConsoleHelper.writeMessage((String) res.getObject("specify.amount"));
        while(reenter){
            reenter = false;
            money = ConsoleHelper.readString();
            if (money == null || !money.matches("-?\\d+") || Integer.parseInt(money)<= 0){
                ConsoleHelper.writeMessage((String) res.getObject("specify.not.empty.amount"));
                reenter = true;
                continue;
            }
            if (!manipulator.isAmountAvailable(Integer.parseInt(money))){
                ConsoleHelper.writeMessage((String) res.getObject("not.enough.money"));
                reenter = true;
                continue;
            }
            try
            {withdraw = manipulator.withdrawAmount(Integer.parseInt(money));}
            catch (NotEnoughMoneyException exc){
                ConsoleHelper.writeMessage((String) res.getObject("exact.amount.not.available"));
                reenter = true;
            }
        }
        for (Map.Entry<Integer, Integer> pair : withdraw.entrySet())
            ConsoleHelper.writeMessage("\t" + pair.getKey() + " - " + pair.getValue());
        ConsoleHelper.writeMessage(String.format((String) res.getObject("success.format"), Integer.parseInt(money), manipulator.getCurrencyCode()));
        ConsoleHelper.writeMessage("");
    }
}
