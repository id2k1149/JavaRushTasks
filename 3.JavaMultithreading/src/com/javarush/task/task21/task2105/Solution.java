package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/

public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Solution))
            return false;
        if (o == null || o.getClass() != this.getClass())
            return false;
        if (o == this) return true;
        Solution n = (Solution) o;
        return n.first==first && n.last==last;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.first == null) ? 0 : this.first.hashCode());
        result = prime * result + ((this.last == null) ? 0 : this.last.hashCode());
        return result;
    }

//    public int hashCode() {
//        return 31 * first.hashCode() + last.hashCode();
//    }



    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));

        System.out.println(s.size());
        for (Solution each: s) {
            System.out.println(each.hashCode());
        }
        Solution solution = new Solution("Mickey", "Mouse");
        s.add(solution);

        System.out.println(s.size());
        for (Solution each: s) {
            System.out.println(each.hashCode());
        }
        System.out.println(s.contains(solution));
    }
}
