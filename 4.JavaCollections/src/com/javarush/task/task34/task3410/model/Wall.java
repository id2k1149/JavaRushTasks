package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(new Color(210, 105, 30));

        int xWall = getX();
        int yWall = getY();
        int height = getHeight();
        int width = getWidth();

        graphics.fillRect(xWall - width / 2, yWall - height / 2, width, height);
    }
}
