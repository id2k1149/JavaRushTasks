package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.yellow);

        int xPlayer = getX();
        int yPlayer = getY();
        int height = getHeight();
        int width = getWidth();

        graphics.fillOval(xPlayer - width / 2, yPlayer - height / 2, width, height);


    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
