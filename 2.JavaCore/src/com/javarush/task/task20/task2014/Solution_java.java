package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution_java {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(new Solution_java(4));

        String fileName = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2014/file.txt";

        OutputStream outputStream = new FileOutputStream(fileName);
        InputStream inputStream = new FileInputStream(fileName);

        Solution_java savedObject = new Solution_java(5);
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println(savedObject.currentDate.getTime());
        printWriter.println(savedObject.temperature);
        printWriter.println(savedObject.string);
        printWriter.close();

        Solution_java newObject = new Solution_java(6);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        newObject.currentDate = new Date(Long.parseLong(bufferedReader.readLine()));
        newObject.temperature = Integer.parseInt(bufferedReader.readLine());
        newObject.string = bufferedReader.readLine();
        bufferedReader.close();

        System.out.println(savedObject.string.equals(newObject.string));

    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution_java(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }

}
