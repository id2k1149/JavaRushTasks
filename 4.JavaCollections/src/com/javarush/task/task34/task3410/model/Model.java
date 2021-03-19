package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;

    // gameObjects будет хранить наши игровые объекты
    private GameObjects gameObjects;
    // Поле отвечающее за текущий уровень
    private  int currentLevel = 1;
    //  Поле отвечающие за загрузчик уровней
    LevelLoader levelLoader;

    public Model() {
        try {
            Path levels = Paths.get(getClass().getResource("../res/levels.txt").toURI());
            levelLoader = new LevelLoader(levels);

        } catch (URISyntaxException ignored) {
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
//        if (checkWallCollision(player, direction)) {
//            System.out.println("false - collision [player");
//            return;
//        }
//        if (checkBoxCollisionAndMoveIfAvailable(direction)) {
//            System.out.println("collision box");
//            return;
//        }

        if (checkWallCollision(player, direction) || checkBoxCollisionAndMoveIfAvailable(direction)) {
            return;
        }

        switch (direction) {
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_CELL_SIZE);
                break;
        }
        checkCompletion();
    }

    // метод проверяет столкновение со стеной.
    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall: gameObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    /*
    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {

        Player player = gameObjects.getPlayer();

        for(Box box : gameObjects.getBoxes()){
            if (player.isCollision(box,direction)){
                for(Box box2 : gameObjects.getBoxes()){
                    if (box.isCollision(box2,direction)){
                        return true;
                    }
                }
                if (checkWallCollision(box,direction)){
                    return true;
                }

                switch (direction) {
                    case LEFT:
                        box.move(-FIELD_CELL_SIZE, 0);
                        break;
                    case RIGHT:
                        box.move(FIELD_CELL_SIZE, 0);
                        break;
                    case UP:
                        box.move(0, -FIELD_CELL_SIZE);
                        break;
                    case DOWN:
                        box.move(0, FIELD_CELL_SIZE);
                        break;
                }
            }
        }

        if (checkWallCollision(player, direction)) {
            return true;
        }

        return false;
    }

     */

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();

        Box nextBox = null;
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                nextBox = box;
                break;
            }
        }

        if (nextBox == null) {
            return false;
        }

        if (checkWallCollision(player, direction) || checkWallCollision(nextBox, direction)) {
            return true;
        }

        for (Box box : gameObjects.getBoxes()) {
            if (box == nextBox) {
                continue;
            }

            if (nextBox.isCollision(box, direction)) {
                return true;
            }
        }

        switch (direction) {
            case LEFT:
                nextBox.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                nextBox.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                nextBox.move(0, -FIELD_CELL_SIZE);
                break;
            case DOWN:
                nextBox.move(0, FIELD_CELL_SIZE);
                break;
        }
        return false;
    }

    public void checkCompletion() {
        List<Box> boxList = new ArrayList<>(gameObjects.getBoxes());

        for (Home home : gameObjects.getHomes()) {
            for (Box box: boxList) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    boxList.remove(box);
                    break;
                }
            }
        }

        if (boxList.size() == 0) {
            eventListener.levelCompleted(currentLevel);
        }
    }
}

