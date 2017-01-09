package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException{
        String input = null;
        try
        {input = reader.readLine();}
        catch (IOException e){}
        if (input.equalsIgnoreCase("exit")){
            throw new InterruptOperationException();
        }
        return input;
    }

    public static String askCurrencyCode() throws InterruptOperationException{
        String code = null;
        while (code == null || code.length() != 3)
        {
            ConsoleHelper.writeMessage((String) res.getObject("choose.currency.code"));
            code = ConsoleHelper.readString();
        }
        return code.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException{
        String input = null;
        String[] money = null;
        while (money == null || money.length != 2){
            ConsoleHelper.writeMessage(String.format((String) res.getObject("choose.denomination.and.count.format"), currencyCode));
            input = ConsoleHelper.readString();
            money = input.split(" ");
            if (money.length != 2 || !money[0].matches("\\d+") || !money[1].matches("\\d+"))
                throw new NumberFormatException();
        }
        return money;
    }

    public static Operation askOperation() throws InterruptOperationException{
        int op = 0;
        Operation operation = null;
        boolean retry = true;
        while (retry || op < 1 || op > 4){
            retry = false;
            ConsoleHelper.writeMessage("");
            ConsoleHelper.writeMessage((String) res.getObject("choose.operation"));
            ConsoleHelper.writeMessage("1 - " + res.getObject("operation.INFO"));
            ConsoleHelper.writeMessage("2 - " + res.getObject("operation.DEPOSIT"));
            ConsoleHelper.writeMessage("3 - " + res.getObject("operation.WITHDRAW"));
            ConsoleHelper.writeMessage("4 - " + res.getObject("operation.EXIT"));
            ConsoleHelper.writeMessage("");
            try
            {   op = Integer.valueOf(ConsoleHelper.readString());
                operation = Operation.getAllowableOperationByOrdinal(op);}
            catch (IllegalArgumentException e){
                retry = true;
                ConsoleHelper.writeMessage((String) res.getObject("invalid.data"));
            }
            if (retry) continue;
        }
        return operation;
    }
    public static void printExitMessage(){
        ConsoleHelper.writeMessage((String) res.getObject("the.end"));
    }
}
