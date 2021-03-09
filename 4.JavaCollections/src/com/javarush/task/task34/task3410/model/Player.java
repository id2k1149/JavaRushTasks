package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable {
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {


        int width = getWidth();
        int height = getHeight();
        int x = getX() - width / 2;
        int y = getY() - height / 2;

        graphics.setColor(Color.YELLOW);
        graphics.fillOval(x, y, width, height);


    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
