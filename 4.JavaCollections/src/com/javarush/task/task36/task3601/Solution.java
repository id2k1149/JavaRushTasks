package com.javarush.task.task36.task3601;

/* 
MVC - простая версия
Solution->View->Controller->Model->Service
*/

public class Solution {
    private View view = new View();

    public static void main(String[] args) {
        new Solution().view.fireShowDataEvent();
    }
}
