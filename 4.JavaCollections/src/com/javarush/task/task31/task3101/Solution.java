package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;


/* 
Проход по дереву файлов
https://javarush.ru/groups/posts/2275-files-path
Убедись, что в файл allFilesContent.txt не записываются лишние данные.
*/

public class Solution {
    public static void main(String[] args) throws IOException {

//        String path = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3101";
//        File resultFileAbsolutePath = new File("/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3101/resultFileAbsolutePath.txt");

        String path = args[0];
        File resultFileAbsolutePath = new File(args[1]) ;

        File allFileContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath,allFileContent);

        try(FileOutputStream fos = new FileOutputStream(allFileContent)) {
            Map<String, File> map = new TreeMap<>();
            find_size(map, path);
            for (Map.Entry<String, File> entry : map.entrySet()) {
                byte[] bytes = Files.readAllBytes(Paths.get(entry.getValue().getPath()));
                fos.write(bytes);
                if (bytes.length > 0) {
                    fos.write("\n".getBytes());
                }
            }
        }
    }

    public static void find_size(Map<String, File>map, String path){
        File files = new File(path);
        File[] file = files.listFiles();
        for(int i = 0; i < file.length; i++){
            if(file[i].isFile() && file[i].length() <= 50 &&  file[i].length() > 0){
                map.put(file[i].getName(), file[i]);
            }
            if(file[i].isDirectory()){
                find_size(map, file[i].getPath());
            }
        }
    }
}


