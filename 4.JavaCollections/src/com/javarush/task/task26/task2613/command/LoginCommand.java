package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

public class LoginCommand implements Command {
    private String testCardNumber = "123456789012";
    private String testPin = "1234";

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
                    if (userCardNumber.equals(testCardNumber) && userPin.equals(testPin)) {
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
