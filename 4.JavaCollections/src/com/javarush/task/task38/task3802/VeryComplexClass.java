package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
https://programming.guide/java/list-of-java-exceptions.html
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        //напишите тут ваш код
        File file = new File(" ");
        InputStream input = new FileInputStream(file);
    }

    public static void main(String[] args) {
    }
}
