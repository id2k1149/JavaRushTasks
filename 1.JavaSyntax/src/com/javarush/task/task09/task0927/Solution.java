package com.javarush.task.task09.task0927;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* 
Десять котов
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        //напишите тут ваш код
        Map<String, Cat> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String name = "name_" + i;
            map.put(name, new Cat(name));

            //Java
//            Cat cat = new Cat("Кот_" + i);
//            map.put(cat.name, cat);
        }
        return map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        //напишите тут ваш код
        Set<Cat> set = new HashSet<>();
        for (Cat each: map.values()) {
            set.add(each);
        }
        return set;
    }

    // Java
//    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
//        return new HashSet<>(map.values());
//    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }


}
