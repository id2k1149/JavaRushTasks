package com.javarush.task.task06.task0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Родственные связи кошек
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String grandDadName = reader.readLine();
        Cat catGrandDad = new Cat(grandDadName);

        String grandMomName = reader.readLine();
        Cat catGrandMom = new Cat(grandMomName);

        String dadName = reader.readLine();
        Cat catDad = new Cat(dadName, catGrandDad, null);

        String motherName = reader.readLine();
        Cat catMother = new Cat(motherName, null, catGrandMom);

        String sonName = reader.readLine();
        Cat catSon = new Cat(sonName, catDad, catMother);

        String daughterName = reader.readLine();
        Cat catDaughter = new Cat(daughterName, catDad, catMother);


        System.out.println(catGrandDad);
        System.out.println(catGrandMom);
        System.out.println(catDad);
        System.out.println(catMother);
        System.out.println(catSon);
        System.out.println(catDaughter);
    }

    public static class Cat {
        private String name;
        private Cat father;
        private Cat mother;

        Cat(String name) {
            this.name = name;
        }

        Cat(String name, Cat father, Cat mother) {
            this.name = name;
            this.father = father;
            this.mother = mother;
        }

        @Override
        public String toString() {
            if (father == null && mother == null)
                return "The cat's name is " + name + ", no mother" + ", no father " ;
            else if (father == null)
                return "The cat's name is " + name + ", mother is " + mother.name + ", no father " ;
            else if (mother == null)
                return "The cat's name is " + name + ", no mother" + ", father is " + father.name ;
            else
                return "The cat's name is " + name + ", mother is "+ mother.name + ", father is " + father.name ;
        }
    }
}
