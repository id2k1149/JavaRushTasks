package com.javarush.task.task28.task2802;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
http://wilddiary.com/understanding_custom_threadfactory_in_java/
https://www.youtube.com/watch?v=cJF0nQmNJZE
https://www.youtube.com/watch?v=Bn4XARHucQI
Фабрика AmigoThreadFactory должна создавать имена
для трэдов по шаблону "GN-pool-A-thread-B".
*/

public class Solution {

    public static class AmigoThreadFactory implements ThreadFactory {

        private final ThreadGroup group;
        private final static AtomicInteger poolNumber_or_factoryCount = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String name;

        public AmigoThreadFactory() {
            group = Thread.currentThread().getThreadGroup();
            String GN = group.getName();
            int A = poolNumber_or_factoryCount.getAndIncrement();
            name = GN + "-pool-" + A + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(group, r, name + threadNumber.getAndIncrement(), 0);
            if (thread.isDaemon())
                thread.setDaemon(false);

            if (thread.getPriority() != Thread.NORM_PRIORITY)
                thread.setPriority(Thread.NORM_PRIORITY);

            return thread;
        }
    }

    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
