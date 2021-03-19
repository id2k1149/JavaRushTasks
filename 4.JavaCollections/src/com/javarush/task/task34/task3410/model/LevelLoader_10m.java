package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader_10m {
    private Path levels;

    public LevelLoader_10m(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Model model = new Model();

        Player player = new Player(model.FIELD_CELL_SIZE / 2, model.FIELD_CELL_SIZE / 2);

        Set<Wall> walls = new HashSet<>();
        for (int i = 2; i < 6; i++) {
            Wall wall = new Wall((model.FIELD_CELL_SIZE / 2) * i, (model.FIELD_CELL_SIZE / 2) * i);
            walls.add(wall);
        }

        Set<Box> boxes = new HashSet<>();
        Box box = new Box((model.FIELD_CELL_SIZE / 2) * 3, (model.FIELD_CELL_SIZE / 2) * 3);
        boxes.add(box);

        Set<Home> homes = new HashSet<>();
        Home home = new Home((model.FIELD_CELL_SIZE / 2) * 4, (model.FIELD_CELL_SIZE / 2) * 4);
        homes.add(home);

        GameObjects objects = new GameObjects(walls, boxes, homes,  player);

        return objects;
    }
}
