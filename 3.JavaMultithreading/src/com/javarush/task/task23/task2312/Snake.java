package com.javarush.task.task23.task2312;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeSection> sections;
    private boolean isAlive;
    private SnakeDirection direction;

    public Snake(int x, int y) {
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
        if (!isAlive) return;
        else {
            if (direction == SnakeDirection.UP) move(0, -1);
            else if (direction == SnakeDirection.RIGHT) move(1, 0);
            else if (direction == SnakeDirection.DOWN) move(0, 1);
            else if (direction == SnakeDirection.LEFT) move(-1, 0);
        }
    }


    public void move(int dx, int dy){
        SnakeSection newHead = new SnakeSection(dx, dy);
        checkBorders(newHead);
        checkBody(newHead);
        if (isAlive) {
            if (getSections().get(0).getX() == Room.game.getMouse().getX()
                    && getSections().get(0).getY() == Room.game.getMouse().getY())
            {
                Room.game.eatMouse();
                sections.add(0, new SnakeSection(dx, dy));
            }
            else {
                sections.add(0, new SnakeSection(dx, dy));
                sections.remove(sections.size()-1);
            }
        }
    }

    public void checkBorders(SnakeSection head) {
        if (head.getX() >= Room.game.getWidth()
        || head.getX() < 0
        || head.getY() >= Room.game.getHeight()
        || head.getY() < 0) isAlive = false;
    }

    public void checkBody(SnakeSection head) {
        if (sections.contains(head)) isAlive = false;
    }
}
