package com.javarush.task.task28.task2809;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

/* 
Plants vs Zombies
https://habr.com/ru/post/277669/

в конструкторе Phaser указывается что сторон будет 6шт.
У нас всего 5 персонажей (5 нитей) и когда они все собираются в одном месте
( упершись в метод phaser.*) они сидят и безуспешно ждут шестого, которого нет.
У меня на этом месте программа зависала.
Затем поток main завершив последний виток в цикле наступает на следующий
метод phazer.*, который уменьшает количество сторон на единицу
Все те персонажи, что сидели ждали шестого, вдруг понимают что число сторон
уже не 6, как было, а 5, а их ровно пять штук и собралось там, значит они могут
бежать дальше.

int arriveAndAwaitAdvance() — указывает что поток завершил выполнение фазы.
Поток приостанавливается до момента, пока все остальные стороны не закончат
выполнять данную фазу. Возвращает номер текущей фазы;

int arriveAndDeregister() — сообщает о завершении всех фаз стороной и
снимает ее с регистрации. Возвращает номер текущей фазы;
*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        List<Character> characters = new ArrayList<>();
        characters.add(new Plant());
        characters.add(new Plant());
        characters.add(new Zombie());
        characters.add(new Zombie());
        characters.add(new Zombie());

        start(characters);
    }

    private static boolean isEveryoneReady = false;

    private static void start(List<Character> characters) throws InterruptedException {
        final Phaser phaser = new Phaser(1 + characters.size());

        for (final Character character : characters) {

            final String member = character.toString();
            System.out.println(member + " присоединился к игре");

            new Thread() {
                @Override
                public void run() {
                    System.out.println(member + " готовится играть");
                    phaser.arriveAndAwaitAdvance();
                    if (!isEveryoneReady) {
                        isEveryoneReady = true;
                        System.out.println("Игра началась!");
                    }
                    character.run();
                }
            }.start();
        }
        phaser.arriveAndDeregister();
    }
}
