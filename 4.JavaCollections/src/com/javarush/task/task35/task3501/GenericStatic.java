package com.javarush.task.task35.task3501;

public class GenericStatic {

    // before
//    public static Object someStaticMethod(Object genericObject) {
//        System.out.println(genericObject);
//        return genericObject;
//    }

    // after
    public static <T> T someStaticMethod(T genericObject) {
        System.out.println(genericObject);
        return genericObject;
    }
}
