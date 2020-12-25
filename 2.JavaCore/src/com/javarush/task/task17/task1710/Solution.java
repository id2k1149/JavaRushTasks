package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        //start here - начни тут
        String sex;
        Person person;
        SimpleDateFormat outFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        if (args.length != 0) {
            switch (args[0]) {
                case "-c" : {

                    if (args[2].equals("м")) {
                        allPeople.add(Person.createMale(args[1],
                                new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3])));
                    }
                    else {
                        allPeople.add(Person.createFemale(args[1],
                                new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3])));
                    }
                    System.out.println(allPeople.size() - 1);
                    break;
                }

                case "-u" : {
                    int id = Integer.parseInt(args[1]);
                    if (args[3].equals("м")) {
                        person = Person.createMale(args[2],
                                new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[4]));
                    }
                    else {
                        person = Person.createFemale(args[2],
                                new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[4]));
                    }
                    allPeople.set(id, person);
                    break;
                }
                case "-d" : {
                    int id = Integer.parseInt(args[1]);
                    allPeople.get(id).setName(null);
                    allPeople.get(id).setSex(null);
                    allPeople.get(id).setBirthDate(null);

                    break;
                }
                case "-i" : {
                    int id = Integer.parseInt(args[1]);
                    System.out.print(allPeople.get(id).getName() + " ");
                    if (allPeople.get(id).getSex().equals(Sex.MALE)) sex = "м";
                    else sex = "ж";
                    System.out.print(sex + " ");
                    String bd = outFormatter.format(allPeople.get(id).getBirthDate());
                    System.out.print(bd);
                    break;
                }
            }
        }
    }
}
