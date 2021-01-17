package com.javarush.task.task31.task3102;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    public ArrayList<String> foundFiles = new ArrayList<>();

    public ArrayList<String> getFoundFiles() {
        return foundFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException, IOException {

        if(attrs.isRegularFile()) {
            foundFiles.add(file.toAbsolutePath().toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
