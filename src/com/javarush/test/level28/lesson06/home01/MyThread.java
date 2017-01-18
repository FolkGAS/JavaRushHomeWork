package com.javarush.test.level28.lesson06.home01;

public class MyThread extends Thread
{
    static int priority = 0;

    public MyThread()
    {
        super();
        this.setPriority(initPriority());
    }

    public MyThread(Runnable target)
    {
        super(target);
        this.setPriority(initPriority());
    }

    public MyThread(ThreadGroup group, Runnable target)
    {
        super(group, target);
        this.setPriority(initPriority(group));
    }

    public MyThread(String name)
    {
        super(name);
        this.setPriority(initPriority());
    }

    public MyThread(ThreadGroup group, String name)
    {
        super(group, name);
        this.setPriority(initPriority(group));
    }

    public MyThread(Runnable target, String name)
    {
        super(target, name);
        this.setPriority(initPriority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name)
    {
        super(group, target, name);
        this.setPriority(initPriority(group));
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
    {
        super(group, target, name, stackSize);
        this.setPriority(initPriority(group));
    }

    private int initPriority(){
        priority++;
        if (priority > 10)
            priority = 1;
        return priority;
    }

    private int initPriority(ThreadGroup group){
        int groupPriority = priority;
        priority++;
        if (priority > 10)
            priority = 1;
        if (priority > group.getMaxPriority())
            return groupPriority;
        return priority;
    }
}
