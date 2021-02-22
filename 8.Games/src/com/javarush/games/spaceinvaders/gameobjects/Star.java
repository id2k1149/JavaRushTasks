package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.*;

public class Star extends GameObject {
    private static final String STAR_SIGN = "\u2605";

    public Star(double x, double y) {
        super(x, y);
    }

    public void draw(Game game) {
        // передадим координаты звезды, цвет фона,
        // символ звезды, цвет и размер этого символа.
        int intX = (int) x;
        int intY = (int) y;
        game.setCellValueEx(intX, intY, Color.NONE, STAR_SIGN, Color.WHITE, 100);

    }
}
