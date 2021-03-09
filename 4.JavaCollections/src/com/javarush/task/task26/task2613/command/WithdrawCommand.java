package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;

class WithdrawCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        Integer expectedAmount;
        while (true) {
            try {
                ConsoleHelper.writeMessage("Enter amount: ");
                expectedAmount = Integer.parseInt(ConsoleHelper.readString());
                if (!currencyManipulator.isAmountAvailable(expectedAmount)) {
                    ConsoleHelper.writeMessage("Not enough money");
                }
                break;

            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Please specify valid data.");
            }
        }

        try {
            Map<Integer, Integer> result = currencyManipulator.withdrawAmount(expectedAmount);
            ConsoleHelper.writeMessage("Success! Here is your money...");
            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
            }
        } catch (NotEnoughMoneyException e) {
            ConsoleHelper.writeMessage("Please enter another amount");
        }

    }
}
