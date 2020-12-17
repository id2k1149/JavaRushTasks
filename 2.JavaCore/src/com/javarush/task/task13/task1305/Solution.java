package com.javarush.task.task13.task1305;

/* 
Четыре ошибки

чтобы обратиться к статичной переменной не нужно создавать экземпляр класса,
можно идти напрямую через класс(точка)переменная
*/

public class Solution {

    public static void main(String[] args) {

        // was
        // System.out.println(new Dream().HOBBY.toString());

        // now
        System.out.println(Dream.HOBBY.toString());



        System.out.println(new Hobby().toString());

    }

    interface Desire {
    }

    interface Dream {
        Hobby HOBBY = new Hobby();
    }

    public static class Hobby  implements Dream, Desire {
        static int INDEX = 1;

        @Override
        public String toString() {
            INDEX++;
            return "" + INDEX;
        }
    }

}
