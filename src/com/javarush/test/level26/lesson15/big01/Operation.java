package com.javarush.test.level26.lesson15.big01;

public enum Operation
{
    LOGIN, INFO, DEPOSIT, WITHDRAW, EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i){
        Operation op = null;
        switch (i){
            case 0 : throw new IllegalArgumentException();
            case 1 : op = Operation.INFO;
            break;
            case 2 : op = Operation.DEPOSIT;
            break;
            case 3 : op = Operation.WITHDRAW;
            break;
            case 4 : op = Operation.EXIT;
            break;
            default: throw new IllegalArgumentException();
        }
        return op;
    }
}
