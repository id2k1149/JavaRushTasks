package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.male.MaleFactory;
import com.javarush.task.task37.task3702.male.Man;

public class Solution {

    public static void main(String[] args) {

        // создай фабрику
        MaleFactory maleFactory = new MaleFactory();

        // вызови у нее метод getPerson три раза
        int[] ages = {99, 4, 15};
        for (int i = 0; i < ages.length; i++) {
            System.out.println(maleFactory.getPerson(ages[i]).toString());
        }

    }
}
