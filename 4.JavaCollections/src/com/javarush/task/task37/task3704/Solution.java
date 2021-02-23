package com.javarush.task.task37.task3704;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

/* 
Фикс бага
https://habr.com/ru/post/164027/
В пустой TreeMap можно положить единственный ключ-null,
все остальные операции (кроме size() и clear(), кстати)
после этого не работают.
В непустой TreeMap положить null-ключ нельзя
из-за обязательного вызова compareTo().

HashMap оперирует с null-ключом без каких-либо проблем.
Его hash всегда равен 0
*/

public class Solution {
    public static void main(String[] args) {
        Map expectedMap = getExpectedMap();

        System.out.println("********* Add obj with NULL key *********");
        expectedMap.put(null, "text 1");
        System.out.println(expectedMap.size());
        System.out.println(expectedMap.get(null));

        System.out.println("*********  Add obj with 0 key *********");
        expectedMap.put(0, "text 2");
        System.out.println(expectedMap.size());
        System.out.println(expectedMap.get(null));
        System.out.println(expectedMap.get(0));

        System.out.println("********* Keys *********");
        for (Object o : expectedMap.keySet()) {
            System.out.println(o);
        }
        System.out.println("********* Values *********");
        for (Object o : expectedMap.values()) {
            System.out.println(o);
        }

        /* Expected output
********* Add obj with NULL key *********
1
text 1
*********  Add obj with 0 key *********
2
text 1
text 2
********* Keys *********
null
0
********* Values *********
text 1
text 2
         */
    }

    public static Map getExpectedMap() {
        return new HashMap();
    }
}
