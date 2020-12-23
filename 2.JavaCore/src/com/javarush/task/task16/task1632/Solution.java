package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    //Java
//    static {
//        threads.add(new Thread1());
//        threads.add(new Thread2());
//        threads.add(new Thread3());
//        threads.add(new Thread4());
//        threads.add(new Thread5());
//    }

    static
    {
        Thread thread_1 = new Thread(new T_1());
        threads.add(thread_1);

        Thread thread_2 = new Thread(new T_2());
        threads.add(thread_2);

        Thread thread_3 = new Thread(new T_3());
        threads.add(thread_3);

        Thread thread_4 = new T_4();
        threads.add(thread_4);

        Thread thread_5 = new Thread(new T_5());
        threads.add(thread_5);

    }

    public static class T_1 implements Runnable {
        @Override
        public void run() {
            while (true);
        }
    }

    public static class T_2 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class T_3 implements Runnable {
        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("Ура");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static class T_4 extends Thread implements Message {
        @Override
        public void run() {
            while(!this.isInterrupted()) {
            }
        }

        @Override
        public void showWarning() {
            this.interrupt();
        }
    }

    public static class T_5 implements Runnable {
        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = null;
            int sum = 0;
            try {
                s = reader.readLine();
                while (!s.equals("N")) {
                    sum += Integer.parseInt(s);
                    s = reader.readLine();
                }
                System.out.println(sum);
            } catch (IOException e) {
            }
        }
    }

    //Java
//    public static class Thread1 extends Thread {
//        public void run() {
//            while (true) {
//            }
//        }
//    }

//    public static class Thread2 extends Thread {
//        public void run() {
//            try {
//                throw new InterruptedException();
//            } catch (InterruptedException e) {
//                System.out.println(e);
//            }
//        }
//    }

//    public static class Thread3 extends Thread {
//        public void run() {
//            while (true) {
//                System.out.println("Ура");
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

//    public static class Thread4 extends Thread implements Message {
//        public void run() {
//            while (!isInterrupted()) {
//            }
//        }
//
//        public void showWarning() {
//            this.interrupt();
//        }
//    }

//    public static class Thread5 extends Thread {
//        public void run() {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            int sum = 0;
//            while (true) {
//                try {
//                    String str = reader.readLine();
//                    if (str.equals("N"))
//                        break;
//                    sum += Integer.parseInt(str);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.print(sum);
//        }
//    }


    public static void main(String[] args) {
    }
}