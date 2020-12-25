package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);



    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        if (args == null || args.length < 1)
            throw new RuntimeException();

        Date birthdayDate;
        Person person;
        switch (args[0]) {
            case "-c":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i+=3) {
                        birthdayDate = simpleDateFormat.parse(args[i+2]);
                        if (args[i+1].equals("м"))
                            person = Person.createMale(args[i], birthdayDate);
                        else
                            person = Person.createFemale(args[i], birthdayDate);
                        allPeople.add(person);
                        System.out.println(allPeople.size() - 1);
                    }
                }
                break;
            case "-u":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i+=4) {
                        birthdayDate = simpleDateFormat.parse(args[i+3]);
                        int id = Integer.parseInt(args[i]);
                        person = allPeople.get(id);
                        if (person == null)
                            throw new IllegalArgumentException();
                        person.setName(args[i+1]);
                        person.setSex(getSex(args[i+2]));
                        person.setBirthDate(birthdayDate);
                        allPeople.set(id, person);
                    }
                }
                break;
            case "-d":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        int id = Integer.parseInt(args[i]);
                        allPeople.get(id).setName(null);
                        allPeople.get(id).setSex(null);
                        allPeople.get(id).setBirthDate(null);
                    }
                }
                break;
            case "-i":
                synchronized (allPeople) {
                    for (int i = 1; i < args.length; i++) {
                        person = allPeople.get(Integer.parseInt(args[i]));
                        if (person != null)
                            System.out.println(person.getName() + " "
                                    + (person.getSex() == Sex.MALE ? "м" : "ж") + " "
                                    + simpleDateFormat2.format(person.getBirthDate()));
                    }
                }
                break;
        }
    }

    private static Sex getSex(String sexParam) {
        return sexParam.equals("м") ? Sex.MALE : Sex.FEMALE;
    }

}
