package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        String logsPath = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task39/task3913/logs";
        LogParser logParser = new LogParser(Paths.get(logsPath));
        Date after = null;
        Date before = new Date();
        System.out.println(logParser.getNumberOfUniqueIPs(after, before));

        System.out.println(logParser.getNumberOfUniqueIPs(after, before));
    }
}