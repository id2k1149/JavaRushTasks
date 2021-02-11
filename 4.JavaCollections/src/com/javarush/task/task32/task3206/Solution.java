package com.javarush.task.task32.task3206;

import java.lang.reflect.Proxy;

/* 
Дженерики для создания прокси-объекта
*/

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        test(solution.getProxy(Item.class));                        //true false false
        test(solution.getProxy(Item.class, Small.class));           //true false true
        test(solution.getProxy(Item.class, Big.class, Small.class));//true true true
        test(solution.getProxy(Big.class, Small.class));            //true true true т.к. Big наследуется от Item
        test(solution.getProxy(Big.class));                         //true true false т.к. Big наследуется от Item
    }


    private static void test(Object proxy) {
        boolean isItem = proxy instanceof Item;
        boolean isBig = proxy instanceof Big;
        boolean isSmall = proxy instanceof Small;

        System.out.format("%b %b %b\n", isItem, isBig, isSmall);
    }

    // getProxy должен иметь два параметра.
    // Первый - класс возвращаемого типа,
    // второй - классы дополнительных интерфейсов
    // (используй аргумент переменной длины ...).
    // Передача аргументов в метод:
    //>>>> public void(int a, int b, int c)
    //>>>> public void(int[] a)
    //>>>> public void(int...a)
    //Все 3 равносильны
    public Item getProxy(Class<?> itemClass, Class<?>... interfaceClass) {

        ClassLoader classLoader = this.getClass().getClassLoader();

        Class<?>[] interfaces = new Class[interfaceClass.length + 1];
        interfaces[0] = itemClass;
        for (int i = 1; i < interfaces.length; i++){
            interfaces[i] = interfaceClass[i-1];
        }

        ItemInvocationHandler itemInvocationHandler = new ItemInvocationHandler();

        Item item = (Item) Proxy.newProxyInstance(classLoader, interfaces, itemInvocationHandler);

        return item;
    }
}
