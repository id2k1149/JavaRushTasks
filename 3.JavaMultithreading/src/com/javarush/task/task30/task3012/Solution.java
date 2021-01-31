package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
//        solution.createExpression(1);
//        solution.createExpression(2);
//        solution.createExpression(3);
//        solution.createExpression(9);
//        solution.createExpression(32);
//        solution.createExpression(74);
//        solution.createExpression(258);
//        solution.createExpression(951);
//        solution.createExpression(1234);
//        solution.createExpression(3000);
    }

    public void createExpression(int number) {
        //напишите тут ваш код
        BigInteger a = new BigInteger(String.valueOf(number));
        String s = a.toString(3);
        String reverse = new StringBuilder(s).reverse().toString();
        char[] chars = reverse.toCharArray();
        List<Integer> integers = new ArrayList<>();
        for (char each : chars) {
            integers.add(Integer.parseInt(String.valueOf(each)));
        }

        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) > 1) {
                integers.set(i, integers.get(i)-3);
                if (i == integers.size() - 1) {
                    integers.add(1);
                    break;
                }
                else {
                    integers.set(i+1, integers.get(i+1)+1);
                }
            }
        }

        ArrayList<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) == 0) continue;
            resultList.add((int) (integers.get(i) * Math.pow(3, i)));
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(number + " =");
        for (Integer each: resultList) {
            if (each < 0) stringBuilder.append(" - " + Math.abs(each));
            else stringBuilder.append(" + " + each);
        }

        System.out.println(stringBuilder.toString());
    }
}