package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {
    View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void paint(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        GameObjects gameObjects = view.getGameObjects();

        for (GameObject gameObject : gameObjects.getAll()) {
            gameObject.draw(g);
        }
    }

    /*public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT : {
                    eventListener.move(Direction.LEFT);
                    break;
                }
                case KeyEvent.VK_RIGHT : {
                    eventListener.move(Direction.RIGHT);
                    break;
                }
                case KeyEvent.VK_UP: {
                    eventListener.move(Direction.UP);
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    eventListener.move(Direction.DOWN);
                    break;
                }
                case KeyEvent.VK_R: {
                    eventListener.restart();
                    break;
                }
            }
        }
    }*/

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                eventListener.move(Direction.LEFT);
            } else if (key == KeyEvent.VK_RIGHT) {
                eventListener.move(Direction.RIGHT);
            } else if (key == KeyEvent.VK_UP) {
                eventListener.move(Direction.UP);
            } else if (key == KeyEvent.VK_DOWN) {
                eventListener.move(Direction.DOWN);
            } else if (key == KeyEvent.VK_R) {
                eventListener.restart();
            }
        }
    }
}
