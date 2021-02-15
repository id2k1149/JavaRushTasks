package com.javarush.task.task25.task2515;

import java.util.ArrayList;
import java.util.List;

public class Space {
    private int width;
    private int height;
    private SpaceShip ship; // космический корабль
    private List<Ufo> ufos = new ArrayList<>(); // список для хранения всех НЛО
    private List<Rocket> rockets = new ArrayList<>(); // список для хранения всех ракет
    private List<Bomb> bombs = new ArrayList<>(); // список для хранения всех бомб

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SpaceShip getShip() {
        return ship;
    }

    public List<Ufo> getUfos() {
        return ufos;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public Space(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // управляет всей логикой игры
    public void run() {

    }

    // отвечает за отрисовку очередного "кадра".
    public void draw() {

    }

    public void sleep(int ms) {

    }

    public static void main(String[] args) {

    }
}
