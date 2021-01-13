package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread implements Runnable {
    Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    public void run()
    {
        Thread.State state = this.thread.getState();
        System.out.println(state.toString());
        while (true) {
            Thread.State new_state = this.thread.getState();
            if (new_state == state) {
                continue;
            }
            else if (new_state.toString().equals("TERMINATED")) {
                System.out.println(new_state.toString());
                return;
            }
            else {
                System.out.println(new_state.toString());
                state = new_state;
                continue;
            }
        }
    }
}
