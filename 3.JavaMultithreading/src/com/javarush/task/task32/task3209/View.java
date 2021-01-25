package com.javarush.task.task32.task3209;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;

    // панель с двумя вкладками
    private JTabbedPane tabbedPane = new JTabbedPane();

    // компонент для визуального редактирования html
    private JTextPane htmlTextPane = new JTextPane();

    // компонент для редактирования html в виде текста,
    // он будет отображать код html (теги и их содержимое).
    private JEditorPane plainTextPane = new JEditorPane();

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public void setHtmlTextPane(JTextPane htmlTextPane) {
        this.htmlTextPane = htmlTextPane;
    }

    public void setPlainTextPane(JEditorPane plainTextPane) {
        this.plainTextPane = plainTextPane;
    }

    public void init() {
    }

    public void exit() {
        controller.exit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
