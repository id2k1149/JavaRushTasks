package com.javarush.task.task23.task2305;

/* 
Inner
*/

public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {

        Solution solution_1 = new Solution();
        Solution.InnerClass innerClass_1a = solution_1.new InnerClass();
        solution_1.innerClasses[0] = innerClass_1a;
        Solution.InnerClass innerClass_1b = solution_1.new InnerClass();
        solution_1.innerClasses[1] = innerClass_1b;

        Solution solution_2 = new Solution();
        Solution.InnerClass innerClass_2a = solution_2.new InnerClass();
        solution_2.innerClasses[0] = innerClass_2a;
        Solution.InnerClass innerClass_2b = solution_2.new InnerClass();
        solution_2.innerClasses[1] = innerClass_2b;

        Solution[] result = new Solution[2];
        result[0] = solution_1;
        result[1] = solution_2;

        return result;
    }

    public static void main(String[] args) {

    }
}
