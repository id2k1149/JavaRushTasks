package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/

public class Solution {
    public static void main(String[] args) {
        int numRings = 3;
        moveRing('A', 'B', 'C', numRings);
    }

    public static void moveRing(char a, char b, char c, int numRings) {
        //напишите тут ваш код
        if (numRings == 1)
        {
            System.out.println(String.format("from %c to %c",a,b));
            return;
        }
        else
        {
            moveRing(a,c,b,numRings-1);
            System.out.println(String.format("from %c to %c",a,c));
            moveRing(c,b,a,numRings-1);
            return;
        }
//        if (numRings == 1) {
//            System.out.println("from " + a + " to " + b);
//            return;
//        }
//        if (numRings == 2) {
//            System.out.println("from " + a + " to " + c);
//            System.out.println("from " + a + " to " + b);
//            System.out.println("from " + c + " to " + b);
//            return;
//        }
//        if (numRings == 0) return;
//        else {
//            System.out.println("from " + a + " to " + c);
//            System.out.println("from " + a + " to " + b);
//            numRings--;
//            moveRing('A', 'C', 'B', numRings);
//
//        }

//        if (numRings > 0) {
//            numRings--;
//            System.out.println(numRings);
//            moveRing('A', 'C', 'B', numRings--);
//            System.out.println("from " + a + " to " + c);
//            moveRing('C', 'B', 'A', numRings--);
//        }


    }
}