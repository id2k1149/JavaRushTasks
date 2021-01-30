package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        // java
//        try {
//            for (int i = 1; i < 10; i++) {
//                System.out.format("Элемент 'ShareItem-%d' добавлен\n", i);
//                queue.offer(new ShareItem("ShareItem-" + i, i));
//                Thread.sleep(100);
//                if (queue.hasWaitingConsumer()) {
//                    System.out.format("Consumer в ожидании!\n");
//                }
//            }
//        } catch (InterruptedException ex) {
//        }

        for (int i = 1; i <= 9; i++) {
            String description = "ShareItem-" + i;
            ShareItem item = new ShareItem(description, i);
            System.out.format("Элемент 'ShareItem-%d' добавлен%n", i);
            queue.offer(item);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (queue.hasWaitingConsumer()) {
                System.out.format("Consumer в ожидании!%n");
            }
        }
    }
}
