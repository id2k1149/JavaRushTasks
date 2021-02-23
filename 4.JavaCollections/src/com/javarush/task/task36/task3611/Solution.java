package com.javarush.task.task36.task3611;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/* 
Сколько у человека потенциальных друзей?
У нас есть по условию таблица с 8 пользователями (начинаем с 0 до 7):
Диагональная линия, выделенная зеленым обозначает каждого конкретного пользователя (с 0 по 7). А его друзья - все сочетания индексов где стоит true, т.е. для пользователя 0 (синяя линия), его друзьями будут 1, 4, 5. Для пользователя 4 (фиолетовая линия) друзьями будут 0, 1, 3. Ну а для пользователя 7 (желтая линия) другом будет номер 3.
Получается что мы перебираем сочетания всех индексов кроме индекса самого пользователя, например для 4 пользователя это 4 0, 4 1, 4 2, 4 3, 4 5, 4 6, 4 7.
Глубина deep работает так:
- При глубине 1 например для пользователя 4 мы ищем его друзей (это 0, 1, 3) и все.
- При глубине 2 для пользователя 4 мы ищем его друзей (0, 1, 3), а потом у каждого из этих трех найденных друзей тоже ищем друзей, т.е. для 0 пользователя это будет (1, 4, 5) и т.д. для оставшихся 1 и 3 пользователей.
Индекс index - это пользователь у которого мы будем искать друзей (по условию задачи это 4 пользователь).
*/

public class Solution {
    private boolean[][] humanRelationships;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.humanRelationships = generateRelationships();
        Set<Integer> allFriendsAndPotentialFriends = solution.getAllFriendsAndPotentialFriends(4, 2);
        System.out.println(allFriendsAndPotentialFriends);                              // Expected: [0, 1, 2, 3, 5, 7]
        Set<Integer> potentialFriends = solution.removeFriendsFromSet(allFriendsAndPotentialFriends, 4);
        System.out.println(potentialFriends);                                           // Expected: [2, 5, 7]
    }

    public Set<Integer> getAllFriendsAndPotentialFriends(int index, int deep) {
        //напишите тут ваш код
        Set<Integer> friendSet = new HashSet<>();
        if (deep <= 0) {
            return friendSet;
        }

        for (int i = 0; i < index; i++) {
            if (humanRelationships[index][i]) {
                friendSet.add(i);
            }
        }
        for (int i = index + 1; i < humanRelationships.length; i++) {
            if (humanRelationships[i][index]) {
                friendSet.add(i);
            }
        }

        Set<Integer> deepFriendSet = new HashSet<>(friendSet);
        for (Integer each : friendSet) {
            deepFriendSet.addAll(getAllFriendsAndPotentialFriends(each, deep - 1));
        }
        deepFriendSet.remove(index);

        return deepFriendSet;
    }

    // Remove from the set the people with whom you already have a relationship
    public Set<Integer> removeFriendsFromSet(Set<Integer> set, int index) {
        for (int i = 0; i < humanRelationships.length; i++) {
            if ((i < index) && (index < humanRelationships.length) && humanRelationships[index][i]) {
                set.remove(i);
            } else if ((i > index) && humanRelationships[i][index]) {
                set.remove(i);
            }
        }
        return set;
    }

    // Return test data
    private static boolean[][] generateRelationships() {
        return new boolean[][]{
                {true},                                                                 //0
                {true, true},                                                           //1
                {false, true, true},                                                    //2
                {false, false, false, true},                                            //3
                {true, true, false, true, true},                                        //4
                {true, false, true, false, false, true},                                //5
                {false, false, false, false, false, true, true},                        //6
                {false, false, false, true, false, false, false, true}                  //7
        };
    }
}