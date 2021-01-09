package com.javarush.task.task23.task2305;

/* 
Inner
*/

public class Solution_java {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution_java[] getTwoSolutions() {
        Solution_java[] result = new Solution_java[2];

        Solution_java solution = new Solution_java();
        solution.innerClasses[0] = solution.new InnerClass();
        solution.innerClasses[1] = solution.new InnerClass();

        Solution_java solution2 = new Solution_java();
        solution2.innerClasses[0] = solution.new InnerClass();
        solution2.innerClasses[1] = solution.new InnerClass();

        result[0] = solution;
        result[1] = solution2;

        return result;
    }

    public static void main(String[] args) {

    }
}
