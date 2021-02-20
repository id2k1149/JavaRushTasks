package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] classes = Collections.class.getDeclaredClasses();

        for (Class<?> aClass : classes) {
            if (isList(aClass) && isPrivateStaticInnerClass(aClass) && isThrowIndexOutOfBoundsException(aClass)) {
                return aClass;
            }
        }
        return null;
    }

    private static boolean isList(Class<?> aClass) {

        List<Class<?>> interfaces = new ArrayList<>();
        interfaces.addAll(Arrays.asList(aClass.getInterfaces()));

        Class<?>[] parentInterfaces = aClass.getSuperclass().getInterfaces();
        interfaces.addAll(Arrays.asList(parentInterfaces));

        for (Class<?> anInterface : interfaces) {
            if (anInterface == List.class) {
                return true;
            }
        }

        return false;
    }

    private static boolean isPrivateStaticInnerClass(Class<?> aClass) {
        return aClass.getModifiers() == (Modifier.PRIVATE + Modifier.STATIC);
    }

    private static boolean isThrowIndexOutOfBoundsException(Class<?> aClass) {
        try {
            Constructor constructor = aClass.getDeclaredConstructor();
            constructor.setAccessible(true);

            Method method = aClass.getDeclaredMethod("get", int.class);
            method.setAccessible(true);
            method.invoke(constructor.newInstance(), 0);

        } catch (InvocationTargetException e) {
            if (e.getCause().toString().contains("IndexOutOfBoundsException")) {
                return true;
            }
        } catch (Exception ignore) {}

        return false;
    }


}
