package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        while(true) {
            try {
                int number = (int) (Math.random() * tablets.size());
                Tablet randomTablet = tablets.get(number);
                randomTablet.createTestOrder();
                Thread.sleep(interval);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                break;
            }
        }


    }
}
