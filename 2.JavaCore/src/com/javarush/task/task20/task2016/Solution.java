package com.javarush.task.task20.task2016;

import java.io.Serializable;

/* 
Минимум изменений
*/

public class Solution {
    public class A implements Serializable{
        String name = "A";

        public A(String name) {
            this.name += name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public class B extends A {
        String name = "B";

        public B(String name) {
            super(name);
            this.name += name;
        }
    }

    public class C extends B {
        String name = "C";

        public C(String name) {
            super(name);
            this.name = name;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new C("_name_C"));
//        System.out.println(new B("_name_B"));
//        System.out.println(new A("_name_A"));

    }
}
