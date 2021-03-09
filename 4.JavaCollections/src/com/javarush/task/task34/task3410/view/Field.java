package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.model.Box;
import com.javarush.task.task34.task3410.model.Home;
import com.javarush.task.task34.task3410.model.Player;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    private View view;

    public Field(View view) {
        this.view = view;
    }

    public void paint(Graphics g) {

        Player player = new Player(25, 47);
        player.draw(g);

        Box box = new Box(67, 74);
        box.draw(g);

        Home home = new Home(7,8);
        home.draw(g);

    }
}
