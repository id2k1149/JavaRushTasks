package com.javarush.task.task08.task0814;

import java.util.HashSet;
import java.util.Set;

/* 
Больше 10? Вы нам не подходите
*/

public class Solution {
    public static Set<Integer> createSet() {
        // напишите тут ваш код
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            set.add(i);
        }
        return set;
    }

    public static Set<Integer> removeAllNumbersGreaterThan10(Set<Integer> set) {
        // напишите тут ваш код
        Set<Integer> set_2 = new HashSet<>();
        for (int each: set) {
            if (each > 10) set_2.add(each);
        }
        set.removeAll(set_2);
        return set;
    }

    public static Set<Integer> removeAllNumbersGreaterThan10_2(Set<Integer> set) {
        Set<Integer> copy = new HashSet<>(set);
        for (Integer number : copy) {
            if (number > 10) {
                set.remove(number);
            }
        }
        return set;
    }



    public static void main(String[] args) {
//        System.out.println(createSet());
//        System.out.println(removeAllNumbersGreaterThan10(createSet()));
    }
}
