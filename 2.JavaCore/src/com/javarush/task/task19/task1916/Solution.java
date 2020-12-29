package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String fileName_1;
        String fileName_2;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)))
        {
            fileName_1 = bufferedReader.readLine();
            fileName_2 = bufferedReader.readLine();
        }

        ArrayList<String> file_1 = new ArrayList<>();
        ArrayList<String> file_2 = new ArrayList<>();
        String string;

        try (BufferedReader fileReader1 = new BufferedReader(new FileReader(fileName_1));
             BufferedReader fileReader2 = new BufferedReader(new FileReader(fileName_2))) {
            while (fileReader1.ready()) {
                string = fileReader1.readLine();
                file_1.add(string);
            }
            while (fileReader2.ready()) {
                string = fileReader2.readLine();
                file_2.add(string);
            }
        }

        while (true) {
            if (file_1.size() == 0 && file_2.size() == 0) {
                break;
            }
            if (file_1.size() != 0 && file_2.size() == 0) {
                lines.add(new LineItem(Type.REMOVED, file_1.get(0)));
                file_1.remove(0);
                continue;
            }
            if (file_1.size() == 0 && file_2.size() != 0) {
                lines.add(new LineItem(Type.ADDED, file_2.get(0)));
                file_2.remove(0);
                continue;
            }
            if (file_1.size() != 0 && file_2.size() != 0) {
                if (file_1.get(0).equals(file_2.get(0))) {
                    lines.add(new LineItem(Type.SAME, file_1.get(0)));
                    file_1.remove(0);
                    file_2.remove(0);
                    continue;
                }
                if (!(file_1.get(0).equals(file_2.get(0)))
                    && (file_1.get(0).equals(file_2.get(1)))) {
                    lines.add(new LineItem(Type.ADDED, file_2.get(0)));
                    file_2.remove(0);
                    continue;
                }
                if (!(file_1.get(0).equals(file_2.get(0)))) {
                    lines.add(new LineItem(Type.REMOVED, file_1.get(0)));
                    file_1.remove(0);
                    continue;
                }
            }
        }
    }

    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
