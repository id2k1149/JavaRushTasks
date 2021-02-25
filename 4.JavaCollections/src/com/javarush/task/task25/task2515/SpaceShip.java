package com.javarush.task.task25.task2515;

import java.util.List;

public class SpaceShip extends BaseObject {
    private double dx = 0;

    public SpaceShip(double x, double y) {
        super(x, y, 3);
    }

    public void moveLeft() {
        dx = -1;
    }

    public void moveRight() {
        dx = 1;
    }

    public void move() {
        x += dx;
        checkBorders(0, Space.game.getWidth(), 0, Space.game.getHeight());
    }

    public void draw(Canvas canvas) {

    }

    public void fire() {
        Rocket rocket_1 = new Rocket(x - 2, y);
        Rocket rocket_2 = new Rocket(x + 2, y);
        List<Rocket> rockets = Space.game.getRockets();
        rockets.add(rocket_1);
        rockets.add(rocket_2);
    }

}
