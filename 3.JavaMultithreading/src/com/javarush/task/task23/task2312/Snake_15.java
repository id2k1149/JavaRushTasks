package com.javarush.task.task23.task2312;

import java.util.ArrayList;
import java.util.List;

public class Snake_15 {
    private List<SnakeSection> sections;
    private boolean isAlive;
    private SnakeDirection direction;

    public Snake_15(int x, int y) {
        sections = new ArrayList<>();
        SnakeSection snakeHead = new SnakeSection(x, y);
        sections.add(snakeHead);
        isAlive = true;
    }


    public List<SnakeSection> getSections() {
        return sections;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public int getX(){
        return sections.get(0).getX();
    }

    public int getY(){
        return sections.get(0).getY();
    }

    public void move(){
        if (isAlive == false) return;
        else {
            if (direction == SnakeDirection.UP) move(0, -1);
            if (direction == SnakeDirection.RIGHT) move(1, 0);
            if (direction == SnakeDirection.DOWN) move(0, 1);
            if (direction == SnakeDirection.LEFT) move(-1, 0);
        }
    }

    public void move(int x, int y){

    }
}
