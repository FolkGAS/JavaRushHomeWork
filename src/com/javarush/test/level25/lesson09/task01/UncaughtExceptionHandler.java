package com.javarush.test.level25.lesson09.task01;

public class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler
{
    @Override
    public void uncaughtException(Thread t, Throwable e)
    {
        Throwable thr = e;
        if (thr instanceof Error) System.out.println("Нельзя дальше работать");
        else if (thr instanceof Exception) System.out.println("Надо обработать");
        else System.out.println("ХЗ");
    }
}
