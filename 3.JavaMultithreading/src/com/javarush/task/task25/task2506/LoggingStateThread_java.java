package com.javarush.task.task25.task2506;

public class LoggingStateThread_java extends Thread  {
    private Thread thread;

    public LoggingStateThread_java(Thread thread) {
        this.thread = thread;
        setDaemon(true);
    }

    @Override
    public void run()
    {
        State currentState = thread.getState();
        System.out.println(currentState);

        State newState;
        do {
            if ((newState = thread.getState()) != currentState) {
                currentState = newState;
                System.out.println(newState);
            }
        } while (!currentState.equals(State.TERMINATED));
    }
}
