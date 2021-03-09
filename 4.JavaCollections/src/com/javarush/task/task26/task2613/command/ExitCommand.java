package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Are you sure? y/n");

        String result = ConsoleHelper.readString();

        if (result != null && result.toLowerCase().equals("y")) {
            ConsoleHelper.writeMessage("\"See you soon...\"");
        } else {

        }
    }
}
