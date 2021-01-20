package com.javarush.task.task31.task3101;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/* 
Проход по дереву файлов
https://javarush.ru/groups/posts/2275-files-path
Убедись, что в файл allFilesContent.txt не записываются лишние данные.
*/

public class Solution_03 {
    public static void main(String[] args) throws IOException {

        String path = "/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3101";
        File resultFileAbsolutePath = new File("/Users/mikepol/IdeaProjects/JavaRushTasks/4.JavaCollections/src/com/javarush/task/task31/task3101/resultFileAbsolutePath.txt");

//        String path = args[0];
//        File resultFileAbsolutePath.txt = new File(args[1]) ;

        File directory = new File(path);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        try (FileOutputStream fileOutputStream = new FileOutputStream(allFilesContent)) {
            List<File> fileList = new ArrayList<>();
            getListFiles(directory, fileList);
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            for (File file : fileList) {
                byte[] data = Files.readAllBytes(Paths.get(file.getPath()));
                if (data.length > 0) {
                    fileOutputStream.write(Files.readAllBytes(Paths.get(file.getPath())));
                    fileOutputStream.write("\n".getBytes(StandardCharsets.UTF_8));
                }
            }

        } catch (IOException exception) {

        }
    }

    private static void getListFiles(File directory, List<File> listFile)
            throws IOException {

        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                Path path = Paths.get(file.getAbsolutePath());
                if (Files.readAllBytes(path).length <= 50) {
                    listFile.add(file);
                }
            } else {
                getListFiles(file, listFile);
            }
        }
    }
}


