package com.javarush.task.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static Map<String, Date> createMap() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        Map<String, Date> map = new HashMap<>();
        map.put("Смирнов", dateFormat.parse("MAY 1 2012"));

        //напишите тут ваш код
        map.put("Патов", dateFormat.parse("AUGUST 19 1980"));
        map.put("Лясин", dateFormat.parse("APRIL 06 1979"));
        map.put("Бибикова", dateFormat.parse("OCTOBER 26 2008"));
        map.put("Хворов", dateFormat.parse("DECEMBER 22 1961"));
        map.put("Науськина", dateFormat.parse("FEBRUARY 13 1959"));
        map.put("Ярцев", dateFormat.parse("MAY 1 2012"));
        map.put("Николаев", dateFormat.parse("JUNE 1 1912"));
        map.put("Филимонов", dateFormat.parse("MAY 19 2002"));
        map.put("Тупов", dateFormat.parse("SEPTEMBER 15 1967"));
        return map;
    }

    public static void removeAllSummerPeople(Map<String, Date> map) {
        //напишите тут ваш код
        Map<String, Date> copy = new HashMap<>(map);

        for (Map.Entry<String, Date> pair : copy.entrySet())
        {
            int month = pair.getValue().getMonth();
            if ((month > 4) && (month  < 8)){
                String summer_key = pair.getKey();
                map.remove(summer_key);
            }
        }
    }

    public static void removeAllSummerPeople_JavaRush(Map<String, Date> map) {
        Map<String, Date> copy = new HashMap<>(map);
        for (String key : copy.keySet()) {
            Date date = copy.get(key);
            int month = date.getMonth() + 1;
            if (month == 6 || month == 7 || month == 8) {
                map.remove(key);
            }
        }
    }

    public static void main(String[] args) {

    }
}
