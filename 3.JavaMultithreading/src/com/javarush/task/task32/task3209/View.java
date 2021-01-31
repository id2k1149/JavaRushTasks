package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;

import javax.swing.*;
import java.awt.*;
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

    public View(){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }


    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    public void initMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);
        getContentPane().add(jMenuBar, BorderLayout.NORTH);
    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");

        JScrollPane jScrollPane_1 = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", jScrollPane_1);

        JScrollPane jScrollPane_2 = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", jScrollPane_2);

        tabbedPane.setPreferredSize(new Dimension(600, 300));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
