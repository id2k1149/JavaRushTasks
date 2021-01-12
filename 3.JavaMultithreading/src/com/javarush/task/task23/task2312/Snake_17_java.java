package com.javarush.task.task23.task2312;

import java.util.ArrayList;
import java.util.List;

public class Snake_17_java {
    private List<SnakeSection> sections;
    private boolean isAlive;
    private SnakeDirection direction;

    public Snake_17_java(int x, int y) {
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
        //Создаем новую голову - новый "кусочек змеи".
        SnakeSection head = sections.get(0);
        head = new SnakeSection(head.getX() + dx, head.getY() + dy);

        //Проверяем - не вылезла ли голова за границу комнаты
        checkBorders(head);
        if (!isAlive) return;

        //Проверяем - не пересекает ли змея  саму себя
        checkBody(head);
        if (!isAlive) return;

        //Проверяем - не съела ли змея мышь.
        Mouse mouse = Room.game.getMouse();
        if (head.getX() == mouse.getX() && head.getY() == mouse.getY()) //съела
        {
            sections.add(0, head);                  //Добавили новую голову
            Room.game.eatMouse();                   //Хвост не удаляем, но создаем новую мышь.
        } else //просто движется
        {
            sections.add(0, head);                  //добавили новую голову
            sections.remove(sections.size() - 1);   //удалили последний элемент с хвоста
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
