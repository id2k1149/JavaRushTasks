package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/

import java.util.Objects;

public class Solution {
    public static class A implements Cloneable {
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            A a = (A) o;
            return i == a.i &&
                    j == a.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }
    }

    public static class C extends B implements Cloneable{

        public C(int i, int j, String name) {
            super(i, j, name);
        }

        @Override
        public Object clone() {
            C new_c = new C(super.getI(), super.getJ(), super.getName());
            return new_c;
        }
    }

    public static void main(String[] args) {
//        B b = new B(1,2, "name_b");
//        B clone_b = null;
//        try {
//            clone_b = (B) b.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(b);
//        System.out.println(clone_b);
//
//        C c = new C(3,4, "name_c");
//        C clone_c = null;
//        try {
//            clone_c = (C) c.clone();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(c);
//        System.out.println(clone_c);
    }
}
