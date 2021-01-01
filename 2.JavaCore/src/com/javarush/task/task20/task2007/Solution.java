package com.javarush.task.task20.task2007;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* 
Как сериализовать JavaRush?
*/

public class Solution {
    public static class JavaRush implements Serializable {
        public List<User> users = new ArrayList<>();
    }

    public static void main(String[] args) {

//        JavaRush jr = new JavaRush();
//        User u1 = new User();
//        jr.users.add(u1);
//        User u2 = new User();
//        jr.users.add(u2);


    }
}
