package com.javarush.test.level14.lesson06.home01;

class UkrainianHen extends Hen
{
    int getCountOfEggsPerMonth()
    {
        return 20;
    }
    String getDescription()
    {
        return (super.getDescription() + " Моя страна - " + Country.UKRAINE + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.");
    }
}