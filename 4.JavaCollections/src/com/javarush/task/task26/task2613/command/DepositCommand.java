package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class DepositCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        while (true) {
            String currencyCode = ConsoleHelper.askCurrencyCode();

            try {
                String[] twoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);
                int denomination = Integer.parseInt(twoDigits[0]);
                int count = Integer.parseInt(twoDigits[1]);

                CurrencyManipulator manipulatorByCurrencyCode = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
                manipulatorByCurrencyCode.addAmount(denomination, count);

                break;

            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage("Please specify valid data.");
            }
        }

    }
}
