package com.javarush.task.task26.task2613;

import java.io.IOException;
import java.util.Locale;

public class CashMachine {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        
        String currencyCode = ConsoleHelper.askCurrencyCode();

        String[] twoDigits = ConsoleHelper.getValidTwoDigits(currencyCode);
        int denomination = Integer.parseInt(twoDigits[0]);
        int count = Integer.parseInt(twoDigits[1]);

        CurrencyManipulator manipulatorByCurrencyCode = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        manipulatorByCurrencyCode.addAmount(denomination, count);

        manipulatorByCurrencyCode.getTotalAmount();
    }
}
