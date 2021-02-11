package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

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

    }

    public void openDocument() {

    }

    public void saveDocument() {

    }

    public void saveDocumentAs() {

    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
}
