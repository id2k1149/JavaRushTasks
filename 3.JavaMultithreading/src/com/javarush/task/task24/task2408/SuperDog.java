package com.javarush.task.task24.task2408;

import java.text.SimpleDateFormat;
import java.util.Locale;

public abstract class SuperDog {
    protected String getSuperQuotes() {
        //some logic here
        return " *** ";
    }

    Locale locale = new Locale("ru");
    protected SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy EEE", locale);
}
