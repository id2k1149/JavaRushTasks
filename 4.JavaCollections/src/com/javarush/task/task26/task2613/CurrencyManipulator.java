package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;
import java.util.stream.Collectors;

public class CurrencyManipulator {
    private String currencyCode; // код валюты, например, USD. Состоит из трех букв.
    private Map<Integer, Integer> denominations; //это Map<номинал, количество>.

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new HashMap<>();
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    /**
     * посчитает общую сумму денег для выбранной валюты.
     * @return
     */
    public int getTotalAmount() {
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            totalAmount += entry.getKey() * entry.getValue();
        }
        return totalAmount;
    }

    /**
     * будет показывать, добавлены ли какие-то банкноты или нет.
     * @return
     */
    public boolean hasMoney() {
        return getTotalAmount() > 0;
    }

    /**
     * вернет true, если денег достаточно для выдачи. т.е. если денег больше или равно expectedAmount
     * @param expectedAmount
     * @return
     */
    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    /**
     * Внимание!!! Метод withdrawAmount должен возвращать минимальное количество банкнот, которыми набирается
     * запрашиваемая сумма.
     * Используйте Жадный алгоритм.
     * Если есть несколько вариантов, то использовать тот, в котором максимальное количество банкнот высшего номинала.
     * Если для суммы 600 результат - три банкноты: 500 + 50 + 50 = 200 + 200 + 200, то выдать первый вариант.
     * @param expectedAmount
     * @return
     */
    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {

        Map<Integer, Integer> map = new LinkedHashMap<>();
        List<Integer> m = denominations.keySet().stream().collect(Collectors.toList());
        Collections.sort(m);
        for (int i = m.size() - 1; i >= 0; i--) {
            int curKup = m.get(i);
            if (curKup <= expectedAmount) {
                int kol = expectedAmount / curKup;
                int minkol = Math.min(denominations.get(curKup), kol);
                map.put(curKup, minkol);
                expectedAmount = expectedAmount - minkol * curKup;
                if (expectedAmount == 0) break;
            }
        }
        if (expectedAmount != 0) throw new NotEnoughMoneyException();
        for (int i = m.size() - 1; i >= 0; i--) {
            int curBank = m.get(i);
            if (map.containsKey(curBank)) {
                denominations.put(curBank, denominations.get(curBank) - map.get(curBank));
                if (denominations.get(curBank) == 0) denominations.remove(curBank);
            }
        }
        return map;


    }


}
