package com.javarush.test.level25.lesson07.home01;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    String threadName = "";
    Thread thread;
    @Override
    public void run() {
        thread = new Thread(threadName){
            @Override
            public void run()
            {
                while (!isInterrupted())
                {
                    System.out.println(Thread.currentThread().getName());
                    try
                    {
                        Thread.sleep(90);
                    }
                    catch (InterruptedException e)
                    {
                        interrupt();
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    public void start(String threadName)
    {
        this.threadName = threadName;
        run();
    }

    @Override
    public void stop()
    {
            thread.interrupt();
    }
}
