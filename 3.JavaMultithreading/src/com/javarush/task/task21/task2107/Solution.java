package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* 
Глубокое клонирование карты
https://javarush.ru/groups/posts/1340-peregruzka-metodov-equals-i-hashcode-v-java
*/

public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }


    protected Map<String, User> users = new LinkedHashMap();


    public Object clone() {
        Solution new_solution = new Solution();
        new_solution.users = new LinkedHashMap<>();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            String key = entry.getKey();
            User value = (User) entry.getValue().clone();
            new_solution.users.put(key, value);
        }
        return new_solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(users, solution.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    public static class User implements Cloneable{
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public Object clone() {
            User new_user = new User(age, name);
            new_user.age = this.age;
            new_user.name = this.name;
            return new_user;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return age == user.age &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }
    }
}
