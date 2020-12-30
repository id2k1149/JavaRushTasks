package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);

    public static void main(String[] args) throws IOException, ParseException {
        if (!(args == null || args.length < 1)) {

            String fileName = args[0];

            try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
                while (fileReader.ready()) {
                    Date birthdayDate = new Date();
                    String line = fileReader.readLine();
                    String[] split_line = line.split(" ");
                    int index = 0;
                    for (int i = 0; i < split_line.length; i++) {
                        if (split_line[i].matches(".*\\d+.*")) {
                            index = i;
                            break;
                        }
                    }
                    String name = "";
                    for (int i = 0; i < index; i++) {
                        if (i == index - 1) {
                            name += split_line[i];
                        }
                        else name += split_line[i] + " ";
                    }

                    String birthday = "";
                    for (int i = index; i < split_line.length; i++) {
                        birthday += split_line[i] + " ";
                    }
                    birthdayDate = simpleDateFormat.parse(birthday);

                    Person person = new Person(name, birthdayDate);
                    PEOPLE.add(person);
                }
            }
        }
    }
}
