package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException, ParseException {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);

            String s = fileScanner.nextLine();
            String[] array = s.split(" ", 4);
            String firstName = array[1];
            String middleName = array[2];
            String lastName = array[0];
            String birthday = array[3];
            Date newDate = simpleDateFormat.parse(birthday);
            Person person = new Person(firstName, middleName, lastName, newDate);
            return person;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
