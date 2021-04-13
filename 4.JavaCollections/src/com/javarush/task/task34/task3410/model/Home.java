package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject {
    private static final int SIZE = 2;

    public Home(int x, int y) {
        super(x, y, SIZE, SIZE);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);

        int xHome = getX();
        int yHome = getY();
        int height = getHeight();
        int width = getWidth();

        graphics.drawOval(xHome - width / 2, yHome - height / 2, width, height);
    }
}