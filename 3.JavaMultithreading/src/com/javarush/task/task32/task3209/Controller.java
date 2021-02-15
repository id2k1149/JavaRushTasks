package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void init() {
        // вызывать метод создания нового документа.
        createNewDocument();
    }

    public void exit() {
        System.exit(0);
    }

    // метод, который будет сбрасывать текущий документ.
    public void resetDocument() {
        // Слушателя нужно запросить у представления (метод getUndoListener()).
        UndoListener listener = view.getUndoListener();

        // проверить, что текущий документ существует (не null).
        if (document != null) {

            // Удалять у текущего документа document слушателя правок, которые можно отменить/вернуть
            document.removeUndoableEditListener(listener);
        }

        // Создавать новый документ по умолчанию и присваивать его полю document.
        HTMLEditorKit kit = new HTMLEditorKit();
        document = (HTMLDocument) kit.createDefaultDocument();

        // Добавлять новому документу слушателя правок.
        document.addUndoableEditListener(listener);

        // Вызывать у представления метод update().
        view.update();
    }

    public void setPlainText(String text) {
        resetDocument();

        StringReader stringReader = new StringReader(text);
        HTMLEditorKit kit = new HTMLEditorKit();
        try {
            kit.read(stringReader, document, 0);
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        HTMLEditorKit kit = new HTMLEditorKit();
        try {
            kit.write(stringWriter, document, 0, document.getLength());
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public void createNewDocument() {
        // Выбирать html вкладку у представления
        view.selectHtmlTab();

        // Сбрасывать текущий документ.
        resetDocument();

        // Устанавливать новый заголовок окна, например: "HTML редактор".
        view.setTitle("new HTML редактор");

        // Обнулить переменную currentFile
        currentFile = null;
    }

    public void openDocument() {
        // Переключать представление на html вкладку
        view.selectHtmlTab();

        // Создавать новый объект для выбора файла JFileChooser.
        JFileChooser chooser = new JFileChooser();

        // Устанавливать ему в качестве фильтра объект HTMLFileFilter.
        chooser.setFileFilter(new HTMLFileFilter());

        // Показывать диалоговое окно "Open File" для выбора файла.
        chooser.setDialogTitle("Open File");

        int result = chooser.showOpenDialog(view);

        // Если пользователь подтвердит выбор файла
        if (result == 0) {
            // Сбрасывать текущий документ.
            resetDocument();

            // Сохранять выбранный файл в поле currentFile.
            currentFile = chooser.getSelectedFile();

            // Устанавливать имя файла в качестве заголовка окна представления.
            view.setTitle(currentFile.getName());

            // Создавать FileWriter на базе currentFile.
            try (FileReader reader = new FileReader(currentFile)) {
                // используя HTMLEditorKit переписывать данные из документа document
                // в объект FileWriter-а, если пользователь подтвердит выбор файла.
                new HTMLEditorKit().read(reader, document, 0);
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
            view.resetUndo();
        }
    }

    public void saveDocument() {
        // Переключать представление на html вкладку
        view.selectHtmlTab();

        if (currentFile == null)
            saveDocumentAs();
        else {
            try (FileWriter writer = new FileWriter(currentFile)) {
                view.setTitle(currentFile.getName());
                // используя HTMLEditorKit переписывать данные из документа document в объект FileWriter-а
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs() {
        // Переключать представление на html вкладку
        view.selectHtmlTab();

        // Создавать новый объект для выбора файла JFileChooser.
        JFileChooser chooser = new JFileChooser();

        // Устанавливать ему в качестве фильтра объект HTMLFileFilter.
        chooser.setFileFilter(new HTMLFileFilter());

        // Показывать диалоговое окно "Save File" для выбора файла.
        chooser.setDialogTitle("Save File");

        // Если пользователь подтвердит выбор файла
        if (chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            // Сохранять выбранный файл в поле currentFile.
            currentFile = chooser.getSelectedFile();

            // Устанавливать имя файла в качестве заголовка окна представления.
            view.setTitle(currentFile.getName());

            // Создавать FileWriter на базе currentFile.
            try (FileWriter writer = new FileWriter(currentFile)) {
                // используя HTMLEditorKit переписывать данные из документа document
                // в объект FileWriter-а, если пользователь подтвердит выбор файла.
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
}
