package com.javarush.test.level14.lesson06.home01;

class RussianHen extends Hen
{
    int getCountOfEggsPerMonth()
    {
        return 30;
    }
    String getDescription()
    {
        return (super.getDescription() + " Моя страна - " + Country.RUSSIA + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.");
    }
}