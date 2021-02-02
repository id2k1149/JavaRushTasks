package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipExtractCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("распаковывать архив.");

            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите полное имя архива");

            ConsoleHelper.writeMessage("Введите полное имя директории, куда будем распаковывать:");
            Path outputFolder = Paths.get(ConsoleHelper.readString());
            zipFileManager.extractAll(outputFolder);

            ConsoleHelper.writeMessage("Архив распаков.");

        } catch (PathIsNotFoundException e) {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }

    }
}
