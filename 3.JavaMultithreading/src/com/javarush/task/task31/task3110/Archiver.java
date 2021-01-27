package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;
import com.javarush.task.task31.task3110.exception.WrongZipFileException;
import java.io.IOException;

public class Archiver {
    public static Operation askOperation() throws IOException {
        System.out.println("Выберите операцию:");
        for(Operation operation : Operation.values())
            ConsoleHelper.writeMessage(String.format("%s - %s", operation.ordinal(), operation));

        int operationNumber = ConsoleHelper.readInt();
        return Operation.values()[operationNumber];
    }

    public static void main(String[] args) throws Exception {
        Operation operation = null;
        while (operation != Operation.EXIT) {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException exception) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл.");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте введенные данные.");
            }
        }
    }
}
