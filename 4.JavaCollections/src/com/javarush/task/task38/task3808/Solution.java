package com.javarush.task.task38.task3808;

import java.util.ArrayList;
import java.util.List;

/* 
Неверные аннотации
*/

public class Solution {
    // Аннотация @Main должна использоваться только для аннотирования методов.
    @Main
    public static void main(String[] args) {
        Solution solution = new Solution().new SubSolution();
        solution.overriddenMethod();
    }

    public void overriddenMethod() {
    }

    public class SubSolution extends Solution {
        @Override
        public void overriddenMethod() {
            System.out.println(uncheckedCall());
        }

        // В методе uncheckedCall
        // должны быть подавлены unchecked warnings с помощью аннотации.
        @SuppressWarnings("unchecked")
        List uncheckedCall() {
            List list = new ArrayList();
            list.add("hello");
            return list;
        }
    }
}
