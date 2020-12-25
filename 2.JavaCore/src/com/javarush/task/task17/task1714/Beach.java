package com.javarush.task.task17.task1714;

/* 
Comparable
*/

public class Beach implements Comparable<Beach>{
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }
    @Override
    public synchronized int compareTo(Beach o) {
        int result_1, result_2;
        result_1 = Float.compare(o.distance, this.distance);
        result_2 = Integer.compare(o.quality, this.quality);
        return result_2 - result_1 ;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    public  static void main(String[] args) {
        Beach a = new Beach("Затон", 7,9);
        Beach b = new Beach("Волга", 6,8);
        System.out.println(a.compareTo(b));
        Beach c = new Beach("Затон", 8,8);
        Beach d = new Beach("Волга", 8,8);
        System.out.println(c.compareTo(d));

    }

}
