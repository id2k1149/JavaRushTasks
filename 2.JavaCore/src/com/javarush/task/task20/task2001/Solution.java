package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/

public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            String my_path = "/Users/mikepol/IdeaProjects/JavaRushTasks/2.JavaCore/src/com/javarush/task/task20/task2001/";

            File your_file_name = File.createTempFile("data_",".txt", new File(my_path));

            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));

            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println(ivanov.equals(somePerson));

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
           if (assets.isEmpty()){
               outputStream.write((name + " ").getBytes());
           }
           else {
               outputStream.write((name + " ").getBytes());
               for (Asset each: assets) {
                   outputStream.write((each.getName() + " ").getBytes());
                   outputStream.write((each.getPrice() + " ").getBytes());
               }
           }
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            String string = "";

            while (inputStream.available() > 0) {
                char each = (char) inputStream.read();
                string += each;
            }

            String[] splited_lines = string.split(" ");
            name = splited_lines[0];

            if (splited_lines.length > 1){
                List<Asset> assets_list = new ArrayList<>();
                for (int i = 1; i < splited_lines.length; i += 2){
                    assets_list.add(new Asset(splited_lines[i], Double.parseDouble(splited_lines[i + 1])));
                }
                assets = assets_list;
            }
        }
    }
}
