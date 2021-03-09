package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject {
    private static final int SIZE = 2;

    public Home(int x, int y) {
        super(x, y, SIZE, SIZE);
    }

    @Override
    public void draw(Graphics graphics) {
        int width = getWidth();
        int height = getHeight();
        int x = getX() - width / 2;
        int y = getY() - height / 2;

        graphics.setColor(Color.RED);
        graphics.fillOval(x, y, width, height);
    }
}
