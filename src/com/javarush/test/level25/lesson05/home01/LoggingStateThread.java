package com.javarush.test.level25.lesson05.home01;

public class LoggingStateThread extends Thread
{
    Thread thread;
    LoggingStateThread(Thread thread){
        this.thread = thread;
        this.setDaemon(true);
    }

    @Override
    public void run()
    {
        Thread.State state = thread.getState();
        Thread.State state2 = null;
        while (state != State.TERMINATED){
            if (state != state2){
                System.out.println(state);
                state2 = state;
            }
            state = thread.getState();
        }
        System.out.println(state);
    }
}
