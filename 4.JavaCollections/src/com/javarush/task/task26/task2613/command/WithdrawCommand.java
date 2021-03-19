package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle
            .getBundle(CashMachine.RESOURCE_PATH
                    + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        Integer expectedAmount;
        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                expectedAmount = Integer.parseInt(ConsoleHelper.readString());
                if (!currencyManipulator.isAmountAvailable(expectedAmount)) {
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                }
                break;

            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
            }
        }

        try {
            Map<Integer, Integer> result = currencyManipulator.withdrawAmount(expectedAmount);
            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), expectedAmount, currencyCode));

            for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
                ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
            }
        } catch (NotEnoughMoneyException e) {
            ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
        }

    }
}
