package com.javarush.task.task11.task1123;

public class Solution {
    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 5, -2, -8, 0, 77, 5, 5};

        Pair<Integer, Integer> result = getMinimumAndMaximum(data);

        System.out.println("The minimum is " + result.x);
        System.out.println("The maximum is " + result.y);
    }

    public static Pair<Integer, Integer> getMinimumAndMaximum(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return new Pair<Integer, Integer>(null, null);
        }

        // напишите тут ваш код
//        for (int i = 0; i < inputArray.length; i++) {
//            for (int j = i + 1; j < inputArray.length; j++) {
//                if (inputArray[i] > inputArray[j]){
//                    int temp = inputArray[j];
//                    inputArray[j] = inputArray[i];
//                    inputArray[i] = temp;
//                }
//            }
//        }
        int min = inputArray[0];
        int max = inputArray[0];
        for (int each: inputArray) {
            if (each < min) min = each;
            if (each > max) max = each;
        }
        return new Pair<Integer, Integer>(min, max);
    }

    public static class Pair<X, Y> {
        public X x;
        public Y y;

        public Pair(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
}
