package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket_java {
    private Path path;

    public FileBucket_java() {
        try {
            // Инициализировать path временным файлом.
            path = Files.createTempFile(Integer.toHexString(hashCode()), ".tmp");
            // Создавать новый файл, используя path.
            // Обеспечивать удаление файла при выходе из программы.
            path.toFile().deleteOnExit();

            // Если такой файл уже есть, то заменять его.
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {

        }
    }

    // метод должен возвращать размер файла на который указывает path.
    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {

        }
        return 0;
    }

    // метод должен сериализовывать переданный entry в файл.
    public void putEntry(Entry entry) {
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(path))){
            output.writeObject(entry);
            } catch (Exception e) {

            }
    }

    // метод должен забирать entry из файла.
    public Entry getEntry() {
        if (getFileSize() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
                return (Entry) ois.readObject();
            } catch (Exception e) {

            }
        }
        return null;
    }

    // метод должен удалять файл на который указывает path.
    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {

        }
    }
}
