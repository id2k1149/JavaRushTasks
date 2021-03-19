package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand_12m implements Command {
    private ResourceBundle validCreditCards  = ResourceBundle
            .getBundle(CashMachine.class.getPackage().getName()
            + ".resources.verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        String userCardNumber;
        String userPin;
        while (true) {
            ConsoleHelper.writeMessage("Enter card number and pin");
            userCardNumber = ConsoleHelper.readString();
            userPin = ConsoleHelper.readString();
            try {
                if (isValidCardNumber(userCardNumber) && isValidPinCode(userPin)) {
                    if (validCreditCards.containsKey(userCardNumber) && userPin.equals(validCreditCards.getString(userCardNumber))) {
                        ConsoleHelper.writeMessage("Verification passed");
                        break;
                    } else {
                        ConsoleHelper.writeMessage("Please specify valid data.");
                    }
                }
            }
            catch(IllegalArgumentException e){
                ConsoleHelper.writeMessage("Please specify valid data.");
            }
        }
    }

    private boolean isValidCardNumber(String userCardNumber) {
        return userCardNumber.matches("\\d{12}");
    }

    private boolean isValidPinCode(String userPin) {
        return userPin.matches("\\d{4}");
    }
}
