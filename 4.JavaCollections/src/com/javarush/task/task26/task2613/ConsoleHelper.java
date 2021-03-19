package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle
            .getBundle(CashMachine.class.getPackage().getName()
                    + ".resources.common_en");

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String text = null;
        try {
            text = bis.readLine();

            if (text.toLowerCase().contains("exit")) {
                throw new InterruptOperationException();
            }

        } catch (IOException ignored) {
            /* NOP */
        }
        return text;
    }

    public static void printExitMessage() {
        writeMessage("See you soon...");
    }

    /**
     * Спросить у пользователя операцию.
     * Если пользователь вводит 1, то выбирается команда INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT;
     * Используйте метод, описанный в п.1.
     * Обработай исключение - запроси данные об операции повторно.
     * @return
     */
    public static Operation askOperation() throws InterruptOperationException  {
        while (true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage("1 - " + res.getString("operation.INFO"));
            writeMessage("2 - " + res.getString("operation.DEPOSIT"));
            writeMessage("3 - " + res.getString("operation.WITHDRAW"));
            writeMessage("4 - " + res.getString("operation.EXIT"));
            while (true) {
                String userInput = readString();
                if (userInput == null || ! userInput.matches("[1-4]")) {
                    ConsoleHelper.writeMessage(res.getString("invalid.data"));
                }
                else {
                    int operationNumber = Integer.parseInt(userInput);
                    return Operation.getAllowableOperationByOrdinal(operationNumber);
                }
            }
        }
    }

    /*
    public static Operation askOperation() {
        while (true) {
            ConsoleHelper.writeMessage("Please choose an operation desired or type 'EXIT' for exiting");
            ConsoleHelper.writeMessage("\t 1 - operation.INFO");
            ConsoleHelper.writeMessage("\t 2 - operation.DEPOSIT");
            ConsoleHelper.writeMessage("\t 3 - operation.WITHDRAW");
            ConsoleHelper.writeMessage("\t 4 - operation.EXIT");
            Integer i = Integer.parseInt(ConsoleHelper.readString().trim());
            try {
                return Operation.getAllowableOperationByOrdinal(i);
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage("Please specify valid data.");
            }
        }
    }
     */

    /**
     * Этот метод должен предлагать пользователю ввести код валюты, проверять, что код содержит 3 символа.
     * Если данные некорректны, то сообщить об этом пользователю и повторить.
     * Если данные валидны, то перевести код в верхний регистр и вернуть.
     * @return
     */
    public static String askCurrencyCode() throws InterruptOperationException  {
        while (true) {
            ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
            String currencyCode = ConsoleHelper.readString();
            if (currencyCode == null || currencyCode.trim().length() != 3) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
                continue;
            }
            return currencyCode.trim().toUpperCase();
        }
    }

    /**
     * Этот метод должен предлагать пользователю ввести два целых положительных числа.
     * Первое число - номинал, второе - количество банкнот.
     * Никаких валидаторов на номинал нет. Т.е. 1200 - это нормальный номинал.
     * Если данные некорректны, то сообщить об этом пользователю и повторить.
     * @param currencyCode
     * @return
     */
    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException  {
        while (true) {
            ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), "conventional units" ));
            String s = ConsoleHelper.readString();
            String[] split;
            if (s == null || (split = s.split(" ")).length != 2) {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            } else {
                try {
                    if (Integer.parseInt(split[0]) <= 0 || Integer.parseInt(split[1]) <= 0)
                        ConsoleHelper.writeMessage(res.getString("invalid.data"));
                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage(res.getString("invalid.data"));
                    continue;
                }
                return split;
            }
        }
    }

//    private static boolean isNotValidTwoNumbers(String twoNumbers) {
//        return twoNumbers == null || !twoNumbers.matches("\\d+ \\d+");
//    }

}
