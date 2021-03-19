package com.javarush.task.task34.task3410.model;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

//        int x = Model.FIELD_CELL_SIZE + Model.FIELD_CELL_SIZE / 2;
//        int y = Model.FIELD_CELL_SIZE + Model.FIELD_CELL_SIZE / 2;

        int x = Model.FIELD_CELL_SIZE / 2;
        int y = Model.FIELD_CELL_SIZE / 2;

        if (level > 60) {
            level %= 60;
            if (level == 0) level = 1;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(levels)))) {
            String currentLine = reader.readLine();

            int sizeX = 0;
            int sizeY = 0;

            while (currentLine != null) {
                if (currentLine.equals("Maze: " + level)) {
                    while (currentLine != null) {
                        currentLine = reader.readLine();
                        if (currentLine.contains("Size X:")) {
                            sizeX = Integer.parseInt(currentLine.substring(8));
                        }
                        if (currentLine.contains("Size Y:")) {
                            sizeY = Integer.parseInt(currentLine.substring(8));
                            currentLine = reader.readLine();
                            currentLine = reader.readLine();
                            currentLine = reader.readLine();

                            int currentY = y;

                            for (int row = 0; row < sizeY; row++) {
                                currentLine = reader.readLine();

                                int currentX = x;

                                for (int column = 0; column < sizeX; column++) {
                                    char currentChar = currentLine.charAt(column);
                                    switch (currentChar) {
                                        case 'X':
                                            walls.add(new Wall(currentX, currentY));
                                            break;
                                        case '*':
                                            boxes.add(new Box(currentX, currentY));
                                            break;
                                        case '&':
                                            boxes.add(new Box(currentX, currentY));
                                        case '.':
                                            homes.add(new Home(currentX, currentY));
                                            break;
                                        case '@':
                                            player = new Player(currentX, currentY);
                                            break;
                                    }
                                    currentX += Model.FIELD_CELL_SIZE;
                                }
                                currentY += Model.FIELD_CELL_SIZE;
                            }
                        }
                        if (currentLine.contains("*************************************")) {
                            break;
                        }

                    }
                    return new GameObjects(walls, boxes, homes, player);
                } else {
                    currentLine = reader.readLine();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
