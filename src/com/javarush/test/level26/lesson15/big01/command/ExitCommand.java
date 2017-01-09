package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "exit_en");
    @Override
    public void execute() throws InterruptOperationException
    {
        String input = null;
        ConsoleHelper.writeMessage((String) res.getObject("exit.question.y.n"));
        {input = ConsoleHelper.readString();}
        if (input.equalsIgnoreCase((String) res.getObject("yes")))
            ConsoleHelper.writeMessage((String) res.getObject("thank.message"));
    }
}
