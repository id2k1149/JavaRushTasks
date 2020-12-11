package com.javarush.task.task06.task0604_my;

/* 
Ставим котов на счётчик
*/

public class Cat {
    public static int catCount = 0;

    //напишите тут ваш код
    public Cat(){
        this.catCount = catCount + 1;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.catCount = catCount - 1;
    }

    public static void main(String[] args) {

    }
}