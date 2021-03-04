package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            // Инициализировать path временным файлом.
            path = Files.createTempFile(null, null);
            // Если такой файл уже есть, то заменять его.
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        // Создавать новый файл, используя path.
        File file = path.toFile();
        // Обеспечивать удаление файла при выходе из программы.
        file.deleteOnExit();
    }

    // метод должен возвращать размер файла на который указывает path.
    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // метод должен сериализовывать переданный entry в файл.
    public void putEntry(Entry entry) {
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(path))){
            output.writeObject(entry);
            } catch (IOException e) {
            ExceptionHandler.log(e);
            }
    }

    // метод должен забирать entry из файла.
    public Entry getEntry() {
        Entry entry = null;

        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(path))) {
            if (getFileSize() > 0) {
                entry = (Entry) input.readObject();
            }
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return entry;
    }

    // метод должен удалять файл на который указывает path.
    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
