package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Знакомство с тегами
*/

public class Solution_05 {


    public static void main(String[] args) throws IOException {

        String s = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task19/task1918/file.properties";

        String teg = "span";

        String name = s;


        FileReader fr = new FileReader(name);
        BufferedReader input = new BufferedReader(fr);

        String text = "";
        while(input.ready()){
            text+=input.readLine();}
        input.close();

        String form = "<"+teg+"."+"+"+"?"+"/"+teg+">";
        Pattern pattern = Pattern.compile(form);
        Matcher mat = pattern.matcher(text);
        while(mat.find()){
            String result = text.substring(mat.start(),mat.end());
            System.out.println(result);}



    }
}
