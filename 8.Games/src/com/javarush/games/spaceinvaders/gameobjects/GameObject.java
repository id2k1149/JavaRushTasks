package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.*;

public class GameObject {
    public double x;
    public double y;
    public int[][] matrix;
    public int width;
    public int height;


    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
        width = matrix[0].length;
        height = matrix.length;
    }

    public void draw(Game game) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int intX = (int) x;
                int intY = (int) y;
                game.setCellValueEx(intX + i, intY + j, Color.values()[matrix[j][i]], "");
            }
        }
    }
}
