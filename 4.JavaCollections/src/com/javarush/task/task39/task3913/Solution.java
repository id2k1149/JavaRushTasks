package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        String logsPath = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task39/task3913/logs";
        LogParser logParser = new LogParser(Paths.get(logsPath));

//        Date after = null;
//        Date before = new Date();
//        System.out.println(logParser.getNumberOfUniqueIPs(after, before));
//        System.out.println(logParser.getDateWhenUserSolvedTask("User", 15, null, null));
//
//        logParser.execute("get ip for date = \"30.08.2012 16:08:13\"").forEach(System.out::println);
//        logParser.execute("get ip for date = \"13.09.2013 5:04:50\"").forEach(System.out::println);

        logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"").forEach(System.out::println);

    }
}