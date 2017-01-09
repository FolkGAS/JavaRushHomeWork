package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login_en");
    String userLogin = null, userPIN = null;
    boolean validated = false;
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage((String) res.getObject("before"));
        ConsoleHelper.writeMessage((String) res.getObject("specify.data"));
        while (!validated){
            validated = false;
            userLogin = ConsoleHelper.readString();
            userPIN = ConsoleHelper.readString();
            if (!userLogin.matches("\\d{12}") || !userPIN.matches("\\d{4}")){
                ConsoleHelper.writeMessage((String) res.getObject("try.again.with.details"));
                continue;
            }else{
                for (String key : validCreditCards.keySet())
                    if (userLogin.equals(key) && userPIN.equals(validCreditCards.getObject(key)))
                    {
                        validated = true;
                        ConsoleHelper.writeMessage(String.format((String) res.getObject("success.format"), key));
                        break;
                    }
            }
            if (!validated)
            {
                ConsoleHelper.writeMessage(String.format((String) res.getObject("not.verified.format"), userLogin));
                ConsoleHelper.writeMessage((String) res.getObject("try.again.or.exit"));
            }
        }
    }
}
