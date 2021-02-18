package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
http://java-online.ru/java-classloader.xhtml
*/

public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(
                Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                + Solution.class.getPackage().getName().replaceAll("[.]", "/")
                + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
//        System.out.println(pathToAnimals);
        Set<Animal> resultSet = new HashSet<>();

        try {
            // когда в пути есть пробелы или кириллица.
            String decodePathToAnimals = URLDecoder.decode(pathToAnimals, "UTF-8");
            File[] files = new File(decodePathToAnimals).listFiles();

            for (File file : files) {
//                System.out.println(file.toString());
                String packageName = Solution.class.getPackage().getName() + ".data";
                Class clazz = new AnimalClassLoader().animalLoadClass(file.toPath(), packageName);
                if (Animal.class.isAssignableFrom(clazz)) {

                    Constructor constructor = clazz.getConstructor();
                    System.out.println(constructor.toString());
                    Animal animal = (Animal) constructor.newInstance();
                    System.out.println(animal);
                    resultSet.add(animal);
                }
            }

        } catch (Exception e) {
            //ignore
        }
        return resultSet;
    }

    // какие либо внешние классы системным лоадером не получится.
    // Поэтому создаем свой и наследуемся от стандартного ClassLoader.
    // (Кстати, обратите внимание поскольку все это происходит в рантайме,
    // наша прога пытается грузить скомпиленные файлы с расширением .class).
    private static class AnimalClassLoader extends ClassLoader {
        public Class<?> animalLoadClass(Path path, String packageName) throws ClassNotFoundException {
            try {
                String className = packageName + "." + path.getFileName().toString().replace(".class", "");
                byte[] buffer = Files.readAllBytes(path);
                return defineClass(className, buffer, 0, buffer.length);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
